package com.travelapp.backend.services;

import java.util.List;

import javax.transaction.Transactional;

import com.travelapp.backend.models.PremadeTrip;
import com.travelapp.backend.models.PremadeTripItem;
import com.travelapp.backend.repositories.PremadeTripItemRepository;
import com.travelapp.backend.repositories.PremadeTripRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PremadeTripService {
    
    private PremadeTripRepository premadeTripRepository;
    private PremadeTripItemRepository premadeTripItemRepository;

    @Autowired
    public PremadeTripService(PremadeTripRepository premadeTripRepository, PremadeTripItemRepository premadeTripItemRepository) {
        this.premadeTripRepository = premadeTripRepository;
        this.premadeTripItemRepository = premadeTripItemRepository;
    }

    public List<PremadeTrip> retrieveAllPremadeTrips(){
        return this.premadeTripRepository.findAll();
    }

    public PremadeTrip retrievePremadeTripByCode(String code){
        return this.premadeTripRepository.findById(code).orElseThrow(() -> new RuntimeException("tripCode does not exist"));
        
    }

    public PremadeTrip createNewPremadeTrip(PremadeTrip premadeTrip){

        if (this.premadeTripRepository.findById(premadeTrip.getTripCode()).isPresent()){
            throw new RuntimeException("PremadeTrip with tripCode already exists");
        }
        
        // necessary?
        PremadeTrip savedPremadeTrip = this.premadeTripRepository.save(premadeTrip);

        for (PremadeTripItem items: premadeTrip.getPremadeTripItems()){
            items.setPremadeTrip(savedPremadeTrip);
        }
        this.premadeTripItemRepository.saveAll(premadeTrip.getPremadeTripItems());
        return premadeTrip;
    }

    @Transactional
    public PremadeTrip updatePremadeTripByCode(
        String tripCode,
        PremadeTrip params
    ){
        PremadeTrip premadeTrip = this.premadeTripRepository.findById(tripCode).orElseThrow( () -> new RuntimeException() );
        
        // TODO: Change TripCode

        // TODO: validation 
        if (params.getTripCountry() != null){
            premadeTrip.setTripCountry(params.getTripCountry());
        }
        
        if (params.getTripName() != null){
            premadeTrip.setTripName(params.getTripName());
        }

        if (params.getTripDuration() != null){
            premadeTrip.setTripDuration(params.getTripDuration());
        }


        if (params.getPremadeTripItems() != null && !params.getPremadeTripItems().isEmpty()){

            for (PremadeTripItem item: params.getPremadeTripItems()){
                item.setPremadeTrip(premadeTrip);
            }

            if (premadeTrip.getPremadeTripItems() != null && !premadeTrip.getPremadeTripItems().isEmpty()){
                premadeTrip.getPremadeTripItems().addAll(params.getPremadeTripItems());
            }
            else {
                premadeTrip.setPremadeTripItems(params.getPremadeTripItems());
            }
            premadeTrip.setPremadeTripItems(this.premadeTripItemRepository.saveAll(params.getPremadeTripItems()));
        }
        
        this.premadeTripRepository.save(premadeTrip);
        return premadeTrip;
    }
    
    public void deletePremadeTripByCode(String code){
        this.premadeTripRepository.deleteById(code);
    }

    // Premade Trip items below -------------------------------------------


    public List<PremadeTripItem> retrieveAllPremadeTripItems(){
        return this.premadeTripItemRepository.findAll();
    }


    public List<PremadeTripItem> retrieveAllPremadeTripItemByTripCode(String tripCode){
        this.premadeTripRepository.findById(tripCode)
        .orElseThrow( () -> new RuntimeException("tripCode does not exist"));

        return this.premadeTripItemRepository.findAllByPremadeTripTripCode(tripCode);
    }

    
    public PremadeTripItem retrievePremadeTripItemByTripCodeAndItemId(String tripCode, Integer itemId) {
        this.premadeTripRepository.findById(tripCode)
        .orElseThrow( () -> new RuntimeException("tripCode does not exist"));

        PremadeTripItem premadeTripItem = this.premadeTripItemRepository.findByTripItemIdAndPremadeTripTripCode(itemId, tripCode)
        .orElseThrow(() -> new RuntimeException("itemId does not exist"));
        return premadeTripItem;
    }


    public PremadeTripItem createPremadeTripItem(String tripCode, PremadeTripItem premadeTripItem){
        PremadeTrip premadeTrip = this.premadeTripRepository.findById(tripCode)
        .orElseThrow( ()-> new RuntimeException("tripCode does not exist"));

        premadeTripItem.setPremadeTrip(premadeTrip);
        return this.premadeTripItemRepository.save(premadeTripItem);

    }

    public void deletePremadeTripItemByTripCodeAndItemId(String tripCode, Integer itemId){
        this.premadeTripRepository.findById(tripCode)
        .orElseThrow( ()-> new RuntimeException("tripCode does not exist"));
        this.premadeTripItemRepository.findByTripItemIdAndPremadeTripTripCode(itemId, tripCode)
        .orElseThrow( ()-> new RuntimeException("itemId does not exist"));

        this.premadeTripItemRepository.deleteById(itemId);

    }

    public PremadeTripItem updatePremadeTripItemByTripCodeAndItemId(String tripCode, Integer itemId, PremadeTripItem params){
        PremadeTrip premadeTrip = this.premadeTripRepository.findById(tripCode)
        .orElseThrow( ()-> new RuntimeException("tripCode does not exist"));
        
        PremadeTripItem premadeTripItem = this.premadeTripItemRepository.findByTripItemIdAndPremadeTripTripCode(itemId, tripCode)
        .orElseThrow( ()-> new RuntimeException("itemId does not exist"));

        premadeTripItem.setPremadeTrip(premadeTrip);

        if (params.getTripItemDays() != null){
            premadeTripItem.setTripItemDays(params.getTripItemDays());
        }   

        if (params.getTripItemDescription() != null){
            premadeTripItem.setTripItemDescription(params.getTripItemDescription());
        }

        if (params.getTripItemTime() != null){
            premadeTripItem.setTripItemTime(params.getTripItemTime());
        }

        return this.premadeTripItemRepository.save(premadeTripItem);

    }
}
