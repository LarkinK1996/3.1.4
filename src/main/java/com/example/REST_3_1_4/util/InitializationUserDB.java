package com.example.REST_3_1_4.util;

import com.example.REST_3_1_4.model.Role;
import com.example.REST_3_1_4.model.User;
import com.example.REST_3_1_4.service.AdminService;
import com.example.REST_3_1_4.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class InitializationUserDB {

    private final AdminService adminService;
    private final RoleService roleService;

    @Autowired
    public InitializationUserDB(AdminService adminService, RoleService roleService) {
        this.adminService = adminService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void createUsersWithRoles() {

        Role role1 = new Role("ADMIN");
        Role role2 = new Role("USER");

        roleService.saveRole(role1);
        roleService.saveRole(role2);

        Set<Role> userRole = new HashSet<>();
        userRole.add(role2);

        Set<Role> adminRole = new HashSet<>();
        adminRole.add(role1);
        adminRole.add(role2);

        User admin = new User("Костя", "Ларькин", "123", new BCryptPasswordEncoder(8).encode("123"), userRole);
        User user = new User("Артём", "Соколов", "1234", new BCryptPasswordEncoder(8).encode("1234"), adminRole);


        adminService.add(admin);
        adminService.add(user);
    }
}
