package com.example.timetablesystem.service;

import com.example.timetablesystem.RestEntities.RestLogin;
import com.example.timetablesystem.entities.Role;
import com.example.timetablesystem.entities.User;
import com.example.timetablesystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public void loginSession(int userId) {
        User user=userService.findUserById(userId);
        Authentication auth = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @Override
    public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public String login(RestLogin restLogin) {
       try{
           User checkUser=userService.findUserByEmail(restLogin.getEmail());
           if(checkUser != null ){
               if(encoder.matches(restLogin.getPassword(),checkUser.getPassword())){
                   return ((checkUser.getRoles().stream().findFirst()).get()).getRoleName()+" "+checkUser.getUserId()+" "+checkUser.getName();
               }
               else{
                   return "Invalid";
               }
           }else{
               return "Invalid";
           }
       }
       catch (Exception e){
           e.printStackTrace();
       }
       return null;
    }
}
