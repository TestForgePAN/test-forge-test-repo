package com.travelapp.backend.repositories;

import java.util.Optional;

import com.travelapp.backend.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

    Optional<User> findUserByUserEmail(String userEmail);

    
}
