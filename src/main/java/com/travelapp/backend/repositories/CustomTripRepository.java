package com.travelapp.backend.repositories;

import com.travelapp.backend.models.CustomTrip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomTripRepository extends JpaRepository<CustomTrip, Integer >{
    
}
