package com.example.REST_3_1_4.controller.rest;

import com.example.REST_3_1_4.model.Role;
import com.example.REST_3_1_4.model.User;
import com.example.REST_3_1_4.service.AdminService;
import com.example.REST_3_1_4.service.RestServiceImpl;
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
    private final RestServiceImpl restService;

    @Autowired
    public RestAdminController(RestServiceImpl restService, AdminService adminService, RoleService roleService) {
        this.adminService = adminService;
        this.roleService = roleService;
        this.restService = restService;
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> showAllUsers() {
        return restService.showAllUsers();
    }

    @GetMapping("roles/all")
    public ResponseEntity<List<Role>> getAllRoles() {
        return restService.getAllRoles();
    }

    @GetMapping("roles/{id}")
    public ResponseEntity<Set<Role>> getRole(@PathVariable Integer id) {
        return restService.getRole(id);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        return restService.getUser(id);
    }

    @GetMapping("users/me")
    public ResponseEntity<User> getActiveUser(Principal principal) {
        return restService.getActiveUser(principal.getName());
    }

    @PostMapping("users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return restService.addUser(user);
    }

    @PutMapping("users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
       return restService.updateUser(user);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        return restService.deleteUser(id);
    }
}

