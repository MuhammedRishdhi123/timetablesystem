package com.example.timetablesystem.repository;

import com.example.timetablesystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    @Query("SELECT user from User user where user.userId= ?1")
    User findByUserId(int userId);

    @Query("SELECT user from User user where user.email= ?1")
    User findByUserEmail(String email);

    @Query("SELECT user from User user where user.name= ?1")
    User findByUserName(String name);




}
