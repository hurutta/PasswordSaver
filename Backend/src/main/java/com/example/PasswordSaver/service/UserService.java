package com.example.PasswordSaver.service;

import java.util.ArrayList;
import java.util.List;

import com.example.PasswordSaver.model.User;
import com.example.PasswordSaver.model.UserData;

public interface UserService
{
    public ArrayList<UserData> getAllOrder(User user);
    public String createOrder(User restaurent);
    public String delete(User restaurent);
    public String update(User restaurent);
}