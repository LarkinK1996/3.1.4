package com.example.REST_3_1_4.config;

import com.example.REST_3_1_4.service.AdminService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SuccessUserHandler successUserHandler;
    private final AdminService adminService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, AdminService adminService) {
        this.successUserHandler = successUserHandler;
        this.adminService = adminService;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("1234").password("{noop}1234")
//                .roles("ADMIN","USER");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .httpBasic()
//                .and()
                .authorizeRequests()
                .antMatchers("/user").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/admin").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .disable();
                .successHandler(successUserHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(new BCryptPasswordEncoder(8));
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        return adminService::loadUserByUsername;
    }
}