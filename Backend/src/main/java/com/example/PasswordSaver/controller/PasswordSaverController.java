package com.example.PasswordSaver.controller;

import java.util.ArrayList;

import com.example.PasswordSaver.model.User;
import com.example.PasswordSaver.model.UserData;
import com.example.PasswordSaver.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8080")

@RestController
public class PasswordSaverController 
{

    @Autowired
    public UserService userService;


    @PostMapping(value="/all")
    public ArrayList<UserData> getAllOrder(@RequestBody User user)
    {
        return userService.getAllOrder(user);
    }
    

    @PostMapping(value = "/create")
    public String createOrder(@RequestBody User user)
    {
        return userService.createOrder(user);
    }
    
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping(value = "/remove")
    public String delete(@RequestBody User user) 
    {
        return userService.delete(user);
    }

    @PutMapping(value="/update")
    public String update(@RequestBody User user)
    {
        return userService.update(user);
    }

    @PostMapping(value="/login")
    public Boolean loginAuthentication(@RequestBody User user)
    {
        return userService.loginAuthentication(user);
    }

}
