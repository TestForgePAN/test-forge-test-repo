package com.travelapp.backend.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "premade_trip")
@Table(name = "premade_trip", schema = "public")
public class PremadeTrip {

    @Id
    @Column(
        name = "trip_code"
    )
    private String tripCode;

    @Column(
        name = "trip_name",
        nullable = false
    )
    private String tripName;

        
    @Column(
        name = "trip_country",
        nullable = false
    )
    private String tripCountry;
            
    @Column(
        name = "trip_duration",
        nullable = false
    )
    private Integer tripDuration;


    // Relations
    
    @OneToMany(mappedBy = "premadeTrip")
    private List<PremadeTripItem> tripItemsList;

    public PremadeTrip() {

    }


    public PremadeTrip(String tripCode, String tripName, String tripCountry, Integer tripDuration) {
        this.tripCode = tripCode;
        this.tripName = tripName;
        this.tripCountry = tripCountry;
        this.tripDuration = tripDuration;
    }


    public String getTripCode() {
        return tripCode;
    }


    public void setTripCode(String tripCode) {
        this.tripCode = tripCode;
    }


    public String getTripName() {
        return tripName;
    }


    public void setTripName(String tripName) {
        this.tripName = tripName;
    }


    public String getTripCountry() {
        return tripCountry;
    }


    public void setTripCountry(String tripCountry) {
        this.tripCountry = tripCountry;
    }


    public Integer getTripDuration() {
        return tripDuration;
    }


    public void setTripDuration(Integer tripDuration) {
        this.tripDuration = tripDuration;
    }


    public List<PremadeTripItem> getPremadeTripItems() {
        return tripItemsList;
    }
    
    public void setPremadeTripItems(List<PremadeTripItem> tripItemsList){
        this.tripItemsList = tripItemsList;
    }
    
    
}
