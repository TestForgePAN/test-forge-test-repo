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

    public CustomTrip retrieveCustomTripById(Integer tripId) {
        return this.customTripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("Could not find requested CustomTrip"));
    }

    

    public List<CustomTrip> retrieveAllCustomTrips() {
        return this.customTripRepository.findAll();
    }

    public CustomTrip createNewCustomTrip(CustomTrip customTrip) {
        return this.customTripRepository.save(customTrip);
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

    public void deleteCustomTripByTripId(Integer tripId) {
        this.customTripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("tripId does not exist"));

        this.customTripRepository.deleteById(tripId);
    }

    // CustomTripItems below

    public List<CustomTripItem> retrieveAllCustomTripItems() {
        return this.customTripItemRepository.findAll();
    }

    public List<CustomTripItem> retrieveAllCustomTripItemByTripId(Integer tripId) {
        this.customTripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("tripId does not exist"));
        return this.customTripItemRepository.findAllByCustomTripTripId(tripId);
    }

    public List<CustomTripItem> retrieveAllCustomTripItemByLessThanMaxPrice(String itemPrice) {
        BigDecimal maxItemPrice = new BigDecimal(itemPrice);

        if (maxItemPrice.compareTo(new BigDecimal(1)) >= 0) {
            return customTripItemRepository.findByTripItemPriceLessThan(maxItemPrice);
        } else {
            System.out.println("hello");
            throw new RuntimeException("Invalid Value");
        }
    }

    public CustomTripItem retrieveCustomTripItemByTripIdAndItemId(Integer tripId, Integer itemId) {
        this.customTripItemRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("tripId does not exist"));

        return this.customTripItemRepository.findByTripItemIdAndCustomTripTripId(itemId, tripId)
                .orElseThrow(() -> new RuntimeException("itemId does not exist"));
    }

    public CustomTripItem createCustomTripItemByTripId(Integer tripId, CustomTripItem customTripItem) {
        CustomTrip customTrip = this.customTripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("tripId does not exist"));

        // TODO: Validation

        customTripItem.setCustomTrip(customTrip);
        return this.customTripItemRepository.save(customTripItem);
    }

    public CustomTripItem updateCustomTripItemByTripIdAndItemId(Integer tripId, Integer itemId, CustomTripItem params) {

        CustomTrip customTrip = this.customTripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("tripId does not exist"));

        CustomTripItem customTripItem = this.customTripItemRepository
                .findByTripItemIdAndCustomTripTripId(itemId, tripId)
                .orElseThrow(() -> new RuntimeException("itemId does not exist"));

        customTripItem.setCustomTrip(customTrip);

        if (params.getTripItemDays() != null) {
            customTripItem.setTripItemDays(params.getTripItemDays());
        }

        if (params.getTripItemDescription() != null) {
            customTripItem.setTripItemDescription(params.getTripItemDescription());
        }

        if (params.getTripItemTime() != null) {
            customTripItem.setTripItemTime(params.getTripItemTime());
        }

        return this.customTripItemRepository.save(customTripItem);
    }

    public void deleteCustomTripItemBytripIdAndItemId(Integer itemId, Integer tripId) {
        this.customTripRepository.findById(tripId)
                .orElseThrow(() -> new RuntimeException("tripId does not exist"));

        this.customTripItemRepository.findByTripItemIdAndCustomTripTripId(tripId, itemId)
                .orElseThrow(() -> new RuntimeException("itemId does not exist"));

        this.customTripItemRepository.deleteById(itemId);
    }

}
