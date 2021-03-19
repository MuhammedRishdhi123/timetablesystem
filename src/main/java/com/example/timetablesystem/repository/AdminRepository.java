package com.example.timetablesystem.repository;

import com.example.timetablesystem.entities.Admin;
import com.example.timetablesystem.entities.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {
    @Query("SELECT admin from Admin admin where admin.adminId= ?1")
    Admin findByUserId(int userId);
}
