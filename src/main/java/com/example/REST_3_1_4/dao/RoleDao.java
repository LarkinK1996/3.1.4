package com.example.REST_3_1_4.dao;

import com.example.REST_3_1_4.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleDao {
     List<Role> getAllRoles();

    Set<Role> getRoleById(Integer[] role_id);
    void saveRole(Role role);
    Role getRole(String name);
}
