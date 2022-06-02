package com.example.REST_3_1_4.service;

import com.example.REST_3_1_4.dao.RoleDaoImpl;
import com.example.REST_3_1_4.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDaoImpl roleDao;

    @Autowired
    public RoleServiceImpl(RoleDaoImpl roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleDao.getAllRoles();
        return roles;
    }


    @Transactional(readOnly = true)
    @Override
    public Set<Role> getRoleById(Integer[] role_id) {
        return roleDao.getRoleById(role_id);
    }

    @Transactional
    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRole(String name) {
        return roleDao.getRole(name);
    }
}
