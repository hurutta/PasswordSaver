package com.example.PasswordSaver.service;

import java.util.ArrayList;

import com.example.PasswordSaver.model.User;
import com.example.PasswordSaver.model.UserData;

public interface UserService
{
    public ArrayList<UserData> getAllOrder(User user);
    public String createOrder(User user);
    public String delete(User user);
    public String update(User user);
    public Boolean loginAuthentication(User user);
}