package com.example.PasswordSaver;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import com.example.PasswordSaver.model.User;
import com.example.PasswordSaver.model.UserData;
import com.example.PasswordSaver.repository.UserRepository;
import com.example.PasswordSaver.service.UserServiceImpl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest 
{
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();


    @Test
    public void getAllCredentials() 
    {
        UserData userDataA = new UserData("Netflix","123456");
        UserData userDataB = new UserData("Whatsapp","4rg45g");
        ArrayList<UserData> arrayList = new ArrayList<UserData>();
        arrayList.add(userDataA); 
        arrayList.add(userDataB); 
        User user = new User( "Abid","1234",arrayList);
        
        ArrayList<User> arr = new ArrayList<User>();
        arr.add(user);

        when(userRepository.findByUsername(user.getUsername())).thenReturn(arr);
        
        assertEquals(user.getUserData(), userService.getAllCredentials(user));
    }

    @Test
    public void addCredential() 
    {
        User user = new User( "Abid","1234",null);
        String response = userService.addCredential(user);
        assertEquals("new user added\n", response);
    }

    @Test
    public void authentication() 
    {
        userService = new UserServiceImpl(userRepository);
        User userA = new User( "Abid","1111",null);
        User userB = new User( "Abid","2222",null);
        Boolean response = userService.authentication(userA,userB);
        assertEquals(false, response);
        User userC = new User( "Abid","1111",null);
        response = userService.authentication(userA,userC);
        assertEquals(true, response);
    }

    @Test
    public void removeCredential() 
    {
        userService = new UserServiceImpl(userRepository);
        User user = new User( "Abid","1234",null);
        String response = userService.removeCredential(user);
        assertEquals("user not found\n", response);
    }

    
}
