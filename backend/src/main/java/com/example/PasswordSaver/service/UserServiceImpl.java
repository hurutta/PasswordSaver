package com.example.PasswordSaver.service;

import java.util.ArrayList;
import java.util.List;

import com.example.PasswordSaver.model.User;
import com.example.PasswordSaver.model.UserData;
import com.example.PasswordSaver.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserServiceImpl implements UserService
{
    @Autowired
    public UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public ArrayList<UserData> getAllCredentials(User user)
    {

        List<User>temporaryList = new ArrayList<User>();
        temporaryList = userRepository.findByUsername(user.getUsername());

        if(authentication(temporaryList.get(0),user))
        {
            return (ArrayList<UserData>) temporaryList.get(0).getUserData();
        }else
        {
            return null;
        }   
    }

    @Override
    public String addCredential(User user)
    {
        List<User>temporaryList = new ArrayList<User>();
        temporaryList = userRepository.findByUsername(user.getUsername());
        if(temporaryList.size() == 0)
        {
            userRepository.insert(user);
            return "new user added\n";
        }else
        {
            if(authentication(temporaryList.get(0),user))
            {
                userRepository.delete(user);
                User newUser = temporaryList.get(0);
                newUser.userData.add(user.userData.get(0));
                userRepository.insert(newUser);
                return "new website added for user : "+ newUser.getUsername() +"\n";
            }else
            {
                return "User already exist, try different username : "+user.getUsername()+"\n";
            }
        }
    }

    @Override
    public String removeCredential(User user) 
    {
        List<User>temporaryList = new ArrayList<User>();
        temporaryList = userRepository.findByUsername(user.getUsername());
        if(temporaryList.size() == 0)
        {
            return "user not found\n";
        }else
        {
            if(authentication(temporaryList.get(0),user))
            {
                userRepository.delete(user);
                User newUser = temporaryList.get(0);
                newUser.userData.remove(user.userData.get(0));
                
                for(int i=0;i<newUser.userData.size();i++)
                {
                    if(newUser.userData.get(i).getWebsiteUrl().equals(user.userData.get(0).getWebsiteUrl()) && newUser.userData.get(i).getWebsitePassword().equals(user.userData.get(0).getWebsitePassword()))
                    {
                        newUser.userData.remove(i);
                        break;
                    }
                }

                userRepository.insert(newUser);
                return "website removed for user : "+ newUser.getUsername() +"\n";
            }else
            {
                return "Wrong password for user : "+user.getUsername()+"\n";
            }
        }
    }

    @Override
    public String updateCredential(User user)
    {
        List<User>temporaryList = new ArrayList<User>();
        temporaryList = userRepository.findByUsername(user.getUsername());
        if(temporaryList.size() == 0)
        {
            return "user not found\n";
        }else
        {
            if(authentication(temporaryList.get(0),user))
            {
                userRepository.delete(user);
                User newUser = temporaryList.get(0);
                newUser.userData.remove(user.userData.get(0));
                
                for(int i=0;i<newUser.userData.size();i++)
                {
                    if(newUser.userData.get(i).getWebsiteUrl().equals(user.userData.get(0).getWebsiteUrl()))
                    {
                        newUser.userData.get(i).setWebsitePassword(user.userData.get(0).getWebsitePassword());
                        break;
                    }
                }
                userRepository.insert(newUser);
                return "website updated for user : "+ newUser.getUsername() +"\n";
            }else
            {
                return "Wrong password for user : "+user.getUsername()+"\n";
            }
        }
    }

    
    public Boolean authentication(User userA, User userB)
    {
        if(userA.getPassword().equals(userB.getPassword()))
        {
            return true;
        }
        return false;
    }

    public Boolean loginAuthentication(User user)
    {
        List<User>temporaryList = new ArrayList<User>();
        temporaryList = userRepository.findByUsername(user.getUsername());
        if(temporaryList.size() == 0)
        {
            return false;
        }else
        {
            return authentication(temporaryList.get(0),user);
        }
    }
}
