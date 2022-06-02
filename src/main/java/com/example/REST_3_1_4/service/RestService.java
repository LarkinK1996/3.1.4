package com.example.REST_3_1_4.service;

import com.example.REST_3_1_4.model.Role;
import com.example.REST_3_1_4.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface RestService {
    ResponseEntity<List<User>> showAllUsers();
     ResponseEntity<List<Role>> getAllRoles();
    ResponseEntity<Set<Role>> getRole(@PathVariable Integer id);
    ResponseEntity<User> getUser(@PathVariable Integer id);
    ResponseEntity<User> getActiveUser(String username);
    ResponseEntity<User> addUser(@RequestBody User user);
    ResponseEntity<User> updateUser(@RequestBody User user);
    ResponseEntity<User> deleteUser(@PathVariable Integer id);

}
