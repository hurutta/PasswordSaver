package com.example.PasswordSaver.service;

import java.util.ArrayList;

import com.example.PasswordSaver.model.User;
import com.example.PasswordSaver.model.UserData;

public interface UserService
{
    public ArrayList<UserData> getAllCredentials(User user);
    public String addCredential(User user);
    public String removeCredential(User user);
    public String updateCredential(User user);
    public Boolean loginAuthentication(User user);
}