package com.example.PasswordSaver.repository;

import java.util.List;

import com.example.PasswordSaver.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> 
{

    List<User> findByUsername(String username);

}