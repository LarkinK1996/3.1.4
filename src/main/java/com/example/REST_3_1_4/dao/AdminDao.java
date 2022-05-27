package com.example.REST_3_1_4.dao;


import com.example.REST_3_1_4.model.User;

import java.util.List;

public interface AdminDao {
    void add(User user);

    void delete(int id);

    User getUser(int id);

    List<User> getAllUsers();

    void updateUser(int id, User newUser);

    User findByUsername(String username);
}
