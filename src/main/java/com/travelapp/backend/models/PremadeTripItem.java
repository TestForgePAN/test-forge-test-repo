package com.travelapp.backend.models;

import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "premade_trip_item")
@Table(name = "premade_trip_item", schema = "public")
public class PremadeTripItem {
    
    @Id
    @Column(
        name = "trip_item_id"
    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tripItemId;

    @Column( 
        name = "trip_item_days",
        nullable = false
    )
    private Integer tripItemDays;

    @Column( 
        name = "trip_item_time",
        nullable = false
    )
    private LocalTime tripItemTime;

    @Column( 
        name = "trip_item_description"
    )
    private String tripItemDescription;

    // Relations
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name="trip_code", referencedColumnName = "trip_code")
    private PremadeTrip premadeTrip;

 

    public PremadeTripItem() {
    }

    public PremadeTripItem(Integer tripItemDays, LocalTime tripItemTime, String tripItemDescription) {
        this.tripItemDays = tripItemDays;
        this.tripItemTime = tripItemTime;
        this.tripItemDescription = tripItemDescription;
    }

    public Integer getTripItemId() {
        return tripItemId;
    }

    public void setTripItemId(Integer tripItemId) {
        this.tripItemId = tripItemId;
    }

    public Integer getTripItemDays() {
        return tripItemDays;
    }

    public void setTripItemDays(Integer tripItemDays) {
        this.tripItemDays = tripItemDays;
    }

    public LocalTime getTripItemTime() {
        return tripItemTime;
    }

    public void setTripItemTime(LocalTime tripItemTime) {
        this.tripItemTime = tripItemTime;
    }

    public String getTripItemDescription() {
        return tripItemDescription;
    }

    public void setTripItemDescription(String tripItemDescription) {
        this.tripItemDescription = tripItemDescription;
    }

    public PremadeTrip getPremadeTrip() {
        return premadeTrip;
    }

    public void setPremadeTrip(PremadeTrip premadeTrip) {
        this.premadeTrip = premadeTrip;
    }
    
    
    

}
