package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.Dao.ConfirmationTokenDao;
import com.Spring.SpringBoot.Dao.UserDao;
import com.Spring.SpringBoot.entity.ConfirmationToken;
import com.Spring.SpringBoot.entity.User;
import com.Spring.SpringBoot.services.EmailSenderService;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.IOException;

@Controller
//@RequestMapping("/api/")
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private ConfirmationTokenDao confirmationTokenDao;

    @Autowired
    private EmailSenderService emailSenderService;
    private JavaMailSender javaMailSender;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView displayRegistration(ModelAndView modelAndView, User user)
    {
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, User user) throws MessagingException, TemplateException, IOException {

        User existingEmail = userDao.findByEmailIgnoreCase(user.getEmail());
        if(existingEmail != null)
        {
            modelAndView.addObject("message","This email is already exists!");
            modelAndView.setViewName("error");
        }
        else
        {
            PasswordEncoder passwordEncoder =new BCryptPasswordEncoder();
            String encodedPassword=passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userDao.save(user);
            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenDao.save(confirmationToken);

            /*SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
            */
           // emailSenderService.sendEmail(mimeMessage);
            emailSenderService.sendEmail(user,confirmationToken);
            //modelAndView.addObject("link","http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
            //modelAndView.addObject("username",user.getUsername());
            modelAndView.addObject("email", user.getEmail());
            modelAndView.setViewName("successfulRegistration");
        }

        return modelAndView;
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationToken token = confirmationTokenDao.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userDao.findByEmailIgnoreCase(token.getUser().getEmail());
            user.setEnabled(true);
            userDao.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
}
