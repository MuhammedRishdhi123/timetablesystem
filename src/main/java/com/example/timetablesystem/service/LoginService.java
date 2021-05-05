package com.example.timetablesystem.service;


import com.example.timetablesystem.dto.RestLogin;
import com.example.timetablesystem.entities.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface LoginService {
    public void loginSession(int userId);
    public String login(RestLogin restLogin);
    Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles);
}
