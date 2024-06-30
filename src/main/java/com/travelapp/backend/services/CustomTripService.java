package com.travelapp.backend.services;

import java.math.BigDecimal;
import java.util.List;

import com.travelapp.backend.models.CustomTrip;
import com.travelapp.backend.models.CustomTripItem;
import com.travelapp.backend.repositories.CustomTripItemRepository;
import com.travelapp.backend.repositories.CustomTripRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomTripService {

    private CustomTripRepository customTripRepository;
    private CustomTripItemRepository customTripItemRepository;

    @Autowired
    public CustomTripService(
            CustomTripRepository customTripRepository,
            CustomTripItemRepository customTripItemRepository) {
        this.customTripRepository = customTripRepository;
        this.customTripItemRepository = customTripItemRepository;
        
    }

    

    public CustomTrip updateCustomTripByTripId(Integer tripId, CustomTrip params) {
        CustomTrip customTrip = this.customTripRepository.findById(params.getTripId())
                .orElseThrow(() -> new RuntimeException("tripId does not exist"));

        if (params.getTripCode() != null) {
            customTrip.setTripCode(params.getTripCode());
        }

        if (params.getTripCountry() != null) {
            customTrip.setTripCountry(params.getTripCountry());
        }

        if (params.getTripDuration() != null) {
            customTrip.setTripDuration(params.getTripDuration());
        }

        if (params.getTripName() != null) {
            customTrip.setTripName(params.getTripName());
        }

        if (params.getCustomTripItems() != null && !params.getCustomTripItems().isEmpty()) {
            // has CustomTripItems

            List<CustomTripItem> customTripItemList = this.customTripItemRepository.findAllByCustomTripTripId(tripId);

            if (customTripItemList.isEmpty()) {
                customTrip.setCustomTripItems(params.getCustomTripItems());
            } else {
                customTripItemList.addAll(params.getCustomTripItems());
            }
            // TODO: sort out update vs post
            customTrip.setCustomTripItems(customTripItemList);

            for (CustomTripItem item : params.getCustomTripItems()) {
                item.setCustomTrip(customTrip);
            }

            this.customTripItemRepository.saveAll(customTripItemList);
        }

        this.customTripRepository.save(customTrip);
        return customTrip;

    }

    

}
