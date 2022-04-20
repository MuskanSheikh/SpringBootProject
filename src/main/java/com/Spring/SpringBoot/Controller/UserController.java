package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.entity.User;
import com.Spring.SpringBoot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/users")
    public ResponseEntity<Map<String,Object>> insert(@Valid @RequestBody User user)throws RuntimeException
    {
        Map<String,Object> response=new HashMap<String,Object>();

         try{
             User saveUser = userService.signUp(user);
             response.put("status","Successfully SignUp");
             return new ResponseEntity<>(response, HttpStatus.OK);
         }catch (Exception e)
         {
             response.put("status","User is already exists with same username");
         }
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            //return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
    }

}
