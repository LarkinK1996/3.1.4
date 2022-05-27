package com.example.REST_3_1_4.rest;

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
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/")
public class RestAdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @GetMapping("users")
    public ResponseEntity<List<User>> showAllUsers() {
        List<User> users = adminService.getAllUsers();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("roles/all")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();

        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("roles/{id}")
    public ResponseEntity<Set<Role>> getRole(@PathVariable Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Integer[] array = new Integer[]{id};

        Set role = roleService.getRoleById(array);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = adminService.getUser(id);

        if (user==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("users/me")
    public ResponseEntity<User> getActiveUser(Principal principal) {

        User user = (User) adminService.loadUserByUsername(principal.getName());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        adminService.add(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("users")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        adminService.updateUser(user.getId(),user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        adminService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
