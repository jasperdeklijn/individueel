package com.jasper.pigrakker.config;

import com.jasper.pigrakker.model.Role;
import com.jasper.pigrakker.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class SecurityUser extends User {
    String userName;
    String password;
    Set<GrantedAuthority> authorities;

    public SecurityUser(String username, String password, Set<GrantedAuthority> authorities) {
        this.userName = username;
        this.password = password;
        this.authorities = authorities;
    }
}