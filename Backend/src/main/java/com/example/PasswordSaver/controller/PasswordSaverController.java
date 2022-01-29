package com.example.PasswordSaver.controller;

import java.util.ArrayList;
import java.util.List;

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

@CrossOrigin(origins = "http://localhost:8080")

@RestController
public class PasswordSaverController 
{

    @Autowired
    public UserService restaurentService;


    @GetMapping(value="/all")
    public ArrayList<UserData> getAllOrder(@RequestBody User user)
    {
        return restaurentService.getAllOrder(user);
    }
    

    @PostMapping(value = "/create")
    public String createOrder(@RequestBody User restaurent)
    {
        return restaurentService.createOrder(restaurent);
    }
    
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping(value = "/remove")
    public String delete(@RequestBody User restaurent) 
    {
        return restaurentService.delete(restaurent);
    }

    @PutMapping(value="/update")
    public String update(@RequestBody User restaurent)
    {
        return restaurentService.update(restaurent);
    }

}
