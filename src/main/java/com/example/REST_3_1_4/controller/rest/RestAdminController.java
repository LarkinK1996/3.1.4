package com.example.REST_3_1_4.controller.rest;

import com.example.REST_3_1_4.model.Role;
import com.example.REST_3_1_4.model.User;
import com.example.REST_3_1_4.service.AdminService;
import com.example.REST_3_1_4.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/")
public class RestAdminController {

    private final AdminService adminService;
    private final RoleService roleService;


    @Autowired
    public RestAdminController(AdminService adminService, RoleService roleService) {
        this.adminService = adminService;
        this.roleService = roleService;
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = adminService.getAllUsers();

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("roles/all")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("roles/{id}")
    public ResponseEntity<Set<Role>> getRole(@PathVariable Integer id) {
        Integer[] array = new Integer[]{id};
        Set role = roleService.getRoleById(array);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        User user = adminService.getUser(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("users/me")
    public ResponseEntity<User> getActiveUser(Principal principal) {
        User user = (User) adminService.loadUserByUsername(principal.getName());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        adminService.add(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        adminService.updateUser(user.getId(), user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        adminService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

