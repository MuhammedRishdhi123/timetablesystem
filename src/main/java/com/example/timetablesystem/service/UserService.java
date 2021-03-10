package com.example.timetablesystem.service;
import com.example.timetablesystem.model.Role;
import com.example.timetablesystem.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.Set;

public interface UserService extends UserDetailsService {

    public User addUser(User user);
    public User findUserById(int userId);
    public Set<User> getAllUsers();
    public Boolean updateUser(User user);
    public void deleteUser(int userId);
    public User findUserByEmail(String email);
    public User findUserByName(String name);
    Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles);
    public User loggedUser();

}
