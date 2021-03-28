package com.example.timetablesystem.service;

import com.example.timetablesystem.entities.Role;
import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.entities.UserPrincipal;
import com.example.timetablesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserById(int userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public Set<User> getAllUsers() {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        User isExist=userRepository.findByUserEmail(user.getEmail());
        if(isExist != null) {
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findByUserName(name);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("The email and password did not match!");
        }

        return new UserPrincipal(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public User loggedUser() {
        String email;
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails)
        {
            email=((UserDetails) principal).getUsername();
        }
        else{
            email=principal.toString();
        }
        return userRepository.findByUserEmail(email);
    }

    @Override
    public boolean checkEmail(String email) {
        User user=userRepository.findByUserEmail(email);
        if(user==null){
            return false;
        }
        return true;

    }
}
