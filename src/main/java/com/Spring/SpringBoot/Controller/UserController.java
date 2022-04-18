package com.Spring.SpringBoot.Controller;

import com.Spring.SpringBoot.entity.User;
import com.Spring.SpringBoot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<User> getUsers()
    {

        return this.userService.getUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user)
    {
        User saveUser=userService.createUser(user);
        return new ResponseEntity<User>(saveUser, HttpStatus.CREATED);
    }
}
