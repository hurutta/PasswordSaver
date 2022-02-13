package com.example.PasswordSaver.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "password")
public class User
{
    @Id
    private String username;
    private String password;
    public ArrayList<UserData> userData;

    public User()
    {
        super();
    }

   

    public User(String username, String password, ArrayList<UserData> userData)
    {
        super();
        this.username = username;
        this.password = password;
        this.userData = userData;
    }
    
    
   

    /**
     * @return String return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return UserData return the userData
     */
    public List<UserData> getUserData() {
        return userData;
    }

    /**
     * @param userData the userData to set
     */
    public void setUserData(ArrayList<UserData> userData) {
        this.userData = userData;
    }

}
