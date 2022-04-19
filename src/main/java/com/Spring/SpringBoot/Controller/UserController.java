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

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> insert(@Valid @RequestBody User user)
    {
        User saveUser=userService.signUp(user);
        return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
    }

}
