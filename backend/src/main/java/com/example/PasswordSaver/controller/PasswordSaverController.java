package com.example.PasswordSaver.controller;

import java.util.ArrayList;

import com.example.PasswordSaver.model.User;
import com.example.PasswordSaver.model.UserData;
import com.example.PasswordSaver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


//@CrossOrigin(origins = "https://pass-word-saver.herokuapp.com")
@CrossOrigin(origins = "http://localhost:8080/")
@RestController
public class PasswordSaverController 
{

    @Autowired
    public UserService userService;


    @GetMapping(value="/all/{username}/{password}")
    public ArrayList<UserData> getAllCredentials(@PathVariable("username") String username, @PathVariable("password") String password)
    {
        User user = new User(username,password,null);
        return userService.getAllCredentials(user);
    }
    

    @PostMapping(value = "/create")
    public String addCredential(@RequestBody User user)
    {
        return userService.addCredential(user);
    }
    

    //@CrossOrigin(origins = "https://pass-word-saver.herokuapp.com")
    @DeleteMapping(value = "/remove")
    public String removeCredential(@RequestBody User user) 
    {
        return userService.removeCredential(user);
    }


    @PutMapping(value="/update")
    public String updateCredential(@RequestBody User user)
    {
        return userService.updateCredential(user);
    }

    
    @PostMapping(value="/login")
    public Boolean loginAuthentication(@RequestBody User user)
    {
        return userService.loginAuthentication(user);
    }

}
