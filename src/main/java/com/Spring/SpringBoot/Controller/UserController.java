package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.Dao.TokenDao;
import com.Spring.SpringBoot.Dao.UserDao;
import com.Spring.SpringBoot.entity.ConfirmationToken;
import com.Spring.SpringBoot.entity.User;
import com.Spring.SpringBoot.services.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@RestController
//@RequestMapping("/api/")
public class UserController {

    PasswordEncoder passwordEncoder;

    @Autowired
    private TokenDao tokenDao;
    @Autowired
    private EmailSenderService emailSenderService;
    @Autowired
    private UserDao userDao;

    @GetMapping(value = "/register")
    public ModelAndView displayResgistration(ModelAndView modelAndView , User user)
    {
        modelAndView.addObject("user",user);
        modelAndView.setViewName("register");
        return modelAndView;
    }
    @PostMapping("/register")
    public ModelAndView registerUser(@Valid ModelAndView modelAndView, User user) {
        User existingUser=userDao.findByEmailIgnoreCase(user.getEmail());
        if(existingUser!=null)
        {
            modelAndView.addObject("message", "This email already exists!!");
            modelAndView.setViewName("error");
        }
        else
        {
            String encodedPassword=this.passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userDao.save(user);
            ConfirmationToken confirmationToken=new ConfirmationToken(user);
            tokenDao.save(confirmationToken);

            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("muskansheikh.100.ms@gmai.com");
            mailMessage.setText("To confirm your account, please click here:"
            +"http://localhost:8080/confirm-accout?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

            modelAndView.addObject("email",user.getEmail());

            modelAndView.setViewName("successfulRegisteration");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/confirm-account", method={RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String token)
    {
        ConfirmationToken confirmationToken=tokenDao.findByConfirmationToken(token);
        if(token!=null)
        {
            User user=userDao.findByEmailIgnoreCase(confirmationToken.getUser().getEmail());
            user.setEnabled(true);
            userDao.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!!!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }
}
