package com.travelapp.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.travelapp.backend.models.PremadeTripItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PremadeTripItemRepository extends JpaRepository<PremadeTripItem, Integer> {
    Optional<PremadeTripItem> findByTripItemIdAndPremadeTripTripCode (Integer tripItemId, String tripCode);
    List<PremadeTripItem> findAllByPremadeTripTripCode(String tripCode);
}
