package com.travelapp.backend.repositories;

import com.travelapp.backend.models.PremadeTrip;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremadeTripRepository extends JpaRepository<PremadeTrip,String> {
        
}
