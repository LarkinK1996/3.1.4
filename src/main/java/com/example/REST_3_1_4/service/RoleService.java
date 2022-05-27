package com.example.REST_3_1_4.service;

import com.example.REST_3_1_4.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<Role> getAllRoles();

    Set getRoleById(Integer[] role_id);

    void saveRole(Role role);
    Role getRole(String name);
}
