package com.travelapp.backend.repositories;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.travelapp.backend.models.CustomTripItem;

@Repository
public interface CustomTripItemRepository extends JpaRepository<CustomTripItem, Integer>{
    Optional<CustomTripItem> findByTripItemIdAndCustomTripTripId (Integer itemId, Integer tripId);
    List<CustomTripItem> findAllByCustomTripTripId (Integer tripId);
    List<CustomTripItem> findByTripItemPriceLessThan(BigDecimal itemPrice);
}
