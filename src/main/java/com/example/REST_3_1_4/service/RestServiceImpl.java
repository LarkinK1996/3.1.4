package com.example.REST_3_1_4.service;

import com.example.REST_3_1_4.model.Role;
import com.example.REST_3_1_4.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RestServiceImpl implements RestService {

    private AdminService adminService;
    private RoleService roleService;

    @Autowired
    public RestServiceImpl(AdminService adminService, RoleService roleService) {
        this.adminService = adminService;
        this.roleService = roleService;
    }

    @Override
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = adminService.getAllUsers();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();

        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Set<Role>> getRole(Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Integer[] array = new Integer[]{id};
        Set role = roleService.getRoleById(array);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUser(Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = adminService.getUser(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getActiveUser(String username) {
        User user = (User) adminService.loadUserByUsername(username);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> addUser(User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        adminService.add(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> updateUser(User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        adminService.updateUser(user.getId(), user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> deleteUser(Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        adminService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
