package com.jasper.pigrakker.config;

import com.jasper.pigrakker.model.User;
import org.springframework.security.core.GrantedAuthority;
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