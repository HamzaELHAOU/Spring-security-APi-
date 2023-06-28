package com.binga.binga.auth;


import com.binga.binga.entities.User;
import com.binga.binga.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class controller {
    UserService userService ;


    @GetMapping("/get")
    public List<User> getAllUsers(){
      return  userService.getAllUsers();
    }

    @GetMapping("/test")
    public List<User> getAllUsers1(){
        return  userService.getAllUsers();}
}
