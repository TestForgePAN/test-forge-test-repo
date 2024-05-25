package com.travelapp.backend.controllers;

import java.util.List;

import com.travelapp.backend.models.CustomTrip;
import com.travelapp.backend.models.CustomTripItem;
import com.travelapp.backend.services.CustomTripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/v1/customtrip")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomTripController {
    
    private CustomTripService customTripService;
    

    @Autowired
    public CustomTripController(CustomTripService customTripService) {
        this.customTripService = customTripService;
    }


    @GetMapping
    public List<CustomTrip> retrieveAllCustomTrip(){
        return this.customTripService.retrieveAllCustomTrips();
    }

    @GetMapping( path = "/{tripId}")
    public CustomTrip getCustomTripByTripId(
        @PathVariable( "tripId" ) Integer tripId
    ){
        return this.customTripService.retrieveCustomTripById(tripId);
    }


    @PostMapping
    public CustomTrip postCustomTrip (
        @RequestBody CustomTrip customTrip
    ){
        return this.customTripService.createNewCustomTrip(customTrip);
    }

    @PutMapping(path = "/{tripId}")
    public CustomTrip updateCustomTrip(
        @PathVariable("tripId") Integer tripId,
        @RequestBody CustomTrip customTrip
    ){
        return this.customTripService.updateCustomTripByTripId(tripId, customTrip);
    }

    @DeleteMapping( path = "/{tripId}")
    public void deleteCustomTrip(
        @PathVariable("tripId") Integer tripId
    ){
        this.customTripService.deleteCustomTripByTripId(tripId);
    }

    // CustomTripItem below ----------------

    @GetMapping(path = "/all/item")
    public List<CustomTripItem> getAllCustomTripItems(@RequestParam ("maxPrice") String maxPrice){
        if (maxPrice != null){
            return customTripService.retrieveAllCustomTripItemByLessThanMaxPrice(maxPrice);
        }

        return this.customTripService.retrieveAllCustomTripItems();
    }

    @GetMapping(path = "/{tripId}/item")
    public List<CustomTripItem> getAllCustomTripItemsByTripId(
        @PathVariable("tripId") Integer tripId
    ){
        return this.customTripService.retrieveAllCustomTripItemByTripId(tripId);
    }

    @GetMapping(path = "/{tripId}/item/{itemId}")
    public CustomTripItem getCustomTripItemByTripIdAndItemId (
        @PathVariable("tripId") Integer tripId,
        @PathVariable("itemId") Integer itemId
    ){
        return this.customTripService.retrieveCustomTripItemByTripIdAndItemId(tripId, itemId);
    }

    @PostMapping(path = "/{tripId}/item")
    public CustomTripItem postCustomTripItemByTripId(
        @PathVariable("tripId") Integer tripId,
        @RequestBody CustomTripItem customTripItem
    ){
        return this.customTripService.createCustomTripItemByTripId(tripId, customTripItem);
    }


    @PutMapping(path = "/{tripId}/item/{itemId}")
    public CustomTripItem putCustomTripItemByTripIdAndItemId(
        @PathVariable("tripId") Integer tripId,
        @PathVariable("itemId") Integer itemId,
        @RequestBody CustomTripItem params
    ){
        return this.customTripService.updateCustomTripItemByTripIdAndItemId(tripId, itemId, params);
    }

    @DeleteMapping(path = "/{tripId}/item/{itemId}")
    public void deleteCustomTripItemByTripIdAndItemId(
        @PathVariable("tripId") Integer tripId,
        @PathVariable("itemId") Integer itemId
    ){
        this.customTripService.deleteCustomTripItemBytripIdAndItemId(itemId, tripId);
    }

}
