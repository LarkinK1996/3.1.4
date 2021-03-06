package com.example.REST_3_1_4.service;

import com.example.REST_3_1_4.dao.AdminDao;
import com.example.REST_3_1_4.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminDao adminDao;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao, PasswordEncoder passwordEncoder) {
        this.adminDao = adminDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public void add(User user) {
        adminDao.add(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Transactional
    @Override
    public void delete(int id) {
        adminDao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(int id) {
        return adminDao.getUser(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        List<User> users = adminDao.getAllUsers();

        if (users.isEmpty()) {
            return (List<User>) new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
        }
        return adminDao.getAllUsers();
    }

    @Transactional
    @Override
    public void updateUser(int id, User newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        adminDao.updateUser(id, newUser);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminDao.findByUsername(username);
    }
}

