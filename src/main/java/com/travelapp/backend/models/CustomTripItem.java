package com.travelapp.backend.models;

import java.math.BigDecimal;
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
@Entity(name = "custom_trip_item")
@Table(name = "custom_trip_item", schema = "public")
public class CustomTripItem {

    @Id
    @Column(
        name = "trip_item_id"
    )
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer tripItemId;


    @Column(
        name = "trip_days",
        nullable = false
    )
    private Integer tripItemDays;

    @Column(
        name = "trip_time",
        nullable = false
    )
    private LocalTime tripItemTime;
    

    @Column(
        name = "trip_description",
        nullable = false
    )
    private String tripItemDescription;

    @Column(
        name = "trip_item_price",
        nullable = false
    )
    private BigDecimal tripItemPrice;

    // Relations
    @JsonIgnore
    @ManyToOne(
        cascade = CascadeType.ALL, 
        optional = false
    )
    @JoinColumn(
        name = "trip_id",
        nullable = false,
        referencedColumnName = "trip_id"
    )
    private CustomTrip customTrip;


    public CustomTripItem() {
      
    }

    public CustomTripItem(Integer tripItemDays, LocalTime tripItemTime, String tripItemDescription, BigDecimal tripItemPrice) {
        this.tripItemDays = tripItemDays;
        this.tripItemTime = tripItemTime;
        this.tripItemDescription = tripItemDescription;
        this.tripItemPrice = tripItemPrice;
    }

    public Integer getTripItemId() {
        return tripItemId;
    }

    public void setTripItemId(Integer tripItemId) {
        this.tripItemId = tripItemId;
    }

    public String getTripItemDescription() {
        return tripItemDescription;
    }

    public void setTripItemDescription(String tripItemDescription) {
        this.tripItemDescription = tripItemDescription;
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

    public CustomTrip getCustomTrip() {
        return customTrip;
    }

    public void setCustomTrip(CustomTrip customTrip) {
        this.customTrip = customTrip;
    }

    public BigDecimal getTripItemPrice(){
        return tripItemPrice;
    }

    public void setTripItemPrice(BigDecimal tripItemPrice){
        this.tripItemPrice = tripItemPrice;
    }

    
}
