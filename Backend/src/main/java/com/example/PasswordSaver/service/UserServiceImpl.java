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
    public UserRepository restaurentRepository;

    public UserServiceImpl(UserRepository restaurentRepository)
    {
        this.restaurentRepository = restaurentRepository;
    }

    @Override
    public ArrayList<UserData> getAllOrder(User user)
    {

        List<User>temporaryList = new ArrayList<User>();
        temporaryList = restaurentRepository.findByUsername(user.getUsername());
        
        if(authentication(temporaryList.get(0),user))
        {
            return (ArrayList<UserData>) temporaryList.get(0).getUserData();
        }else
        {
            return null;
        }
    }

    @Override
    public String createOrder(User user)
    {
        List<User>temporaryList = new ArrayList<User>();
        temporaryList = restaurentRepository.findByUsername(user.getUsername());
        if(temporaryList.size() == 0)
        {
            restaurentRepository.insert(user);
            return "new user added\n";
        }else
        {
            if(authentication(temporaryList.get(0),user))
            {
                restaurentRepository.delete(user);
                User newUser = temporaryList.get(0);
                newUser.userData.add(user.userData.get(0));
                restaurentRepository.insert(newUser);
                return "new website added for user : "+ newUser.getUsername() +"\n";
            }else
            {
                return "Wrong password for user : "+user.getUsername()+"\n";
            }
        }
    }

    @Override
    public String delete(User user) 
    {
        List<User>temporaryList = new ArrayList<User>();
        temporaryList = restaurentRepository.findByUsername(user.getUsername());
        if(temporaryList.size() == 0)
        {
            return "user not found\n";
        }else
        {
            if(authentication(temporaryList.get(0),user))
            {
                restaurentRepository.delete(user);
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

                restaurentRepository.insert(newUser);
                return "website removed for user : "+ newUser.getUsername() +"\n";
            }else
            {
                return "Wrong password for user : "+user.getUsername()+"\n";
            }
        }
    }

    @Override
    public String update(User user)
    {
        List<User>temporaryList = new ArrayList<User>();
        temporaryList = restaurentRepository.findByUsername(user.getUsername());
        if(temporaryList.size() == 0)
        {
            return "user not found\n";
        }else
        {
            if(authentication(temporaryList.get(0),user))
            {
                restaurentRepository.delete(user);
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
                restaurentRepository.insert(newUser);
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


}
