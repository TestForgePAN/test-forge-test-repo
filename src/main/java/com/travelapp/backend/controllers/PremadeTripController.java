package com.travelapp.backend.controllers;

import java.util.List;

import com.travelapp.backend.models.PremadeTrip;
import com.travelapp.backend.models.PremadeTripItem;
import com.travelapp.backend.services.PremadeTripService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping( path = "api/v1/premadetrip")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PremadeTripController {
    
    
    private PremadeTripService premadeTripService;

    @Autowired
    public PremadeTripController(PremadeTripService premadeTripService) {
        this.premadeTripService = premadeTripService;
    }

    @GetMapping
    public List<PremadeTrip> getAllPremadeTrips(){
        return this.premadeTripService.retrieveAllPremadeTrips();
    }


    @GetMapping(path = "{tripCode}")
    public PremadeTrip getPremadeTripByCode(
        @PathVariable("tripCode") String code
    ){
        
        return this.premadeTripService.retrievePremadeTripByCode(code);
    }
    
    @PostMapping
    public PremadeTrip postNewPremadeTrip(
        @RequestBody PremadeTrip premadeTrip){
        return this.premadeTripService.createNewPremadeTrip(premadeTrip);
    }


    @PutMapping(path = "{tripCode}")
    public PremadeTrip putPremadeTripByCode(
        @PathVariable("tripCode") String tripCode,
        @RequestBody PremadeTrip premadeTrip
    ){
       
        return this.premadeTripService.updatePremadeTripByCode(tripCode, premadeTrip);
    }



    @DeleteMapping(path = "{tripCode}")
    public void deletePremadeTripByCode(
        @PathVariable("tripCode") String code
    ){
        this.premadeTripService.deletePremadeTripByCode(code);
        
    }




    // Premade trip items below ----------------





    @GetMapping(path = "/all/item")
    public List<PremadeTripItem> getAllPremadeTripItems(){
        return this.premadeTripService.retrieveAllPremadeTripItems();
    }

    @GetMapping(path="/{tripCode}/item")
    public List<PremadeTripItem> getAllPremadeTripItemsByTripCode(
        @PathVariable("tripCode") String tripCode
    ){
        return this.premadeTripService.retrieveAllPremadeTripItemByTripCode(tripCode);
    }

    @GetMapping(path="/{tripCode}/item/{tripItemId}")
    public PremadeTripItem getPremadeTripItemByTripCodeAndItemId(
        @PathVariable("tripCode") String tripCode,
        @PathVariable("tripItemId") Integer itemId
    ){
        return this.premadeTripService.retrievePremadeTripItemByTripCodeAndItemId(tripCode,  itemId);
    }

    
    @PostMapping(path="/{tripCode}/item")
    public PremadeTripItem postPremadeTripItem (
        @PathVariable("tripCode") String tripCode,
        @RequestBody PremadeTripItem premadeTripItem
    ){
        return this.premadeTripService.createPremadeTripItem(tripCode, premadeTripItem);
    }
    

    @DeleteMapping(path="/{tripCode}/item/{tripItemId}")
    public void deletePremadeTripItemByTripCodeAndItemId(
        @PathVariable("tripCode") String tripCode,
        @PathVariable("tripItemId") Integer itemId
    ){
        this.premadeTripService.deletePremadeTripItemByTripCodeAndItemId(tripCode, itemId);
    }
    
    
    
}
