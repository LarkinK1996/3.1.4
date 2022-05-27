package com.example.REST_3_1_4.service;


import com.example.REST_3_1_4.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface AdminService {

    void add(User user);

    void delete(int id);

    User getUser(int id);

    void updateUser(int id, User newUser);

    List<User> getAllUsers();

    UserDetails loadUserByUsername(String username);

}

