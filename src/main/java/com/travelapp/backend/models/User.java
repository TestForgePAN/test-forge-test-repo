package com.travelapp.backend.models;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity(name = "tuser")
@Table(
    name = "tuser",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "user_email_unique",
            columnNames = "user_email"
        )
    }, 
    schema = "public"

)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(
        name = "user_id"
    )
    private Integer userId;

    @Column(
        name = "user_email",
        nullable = false
    )
    private String userEmail;
    
    @Column(
        name = "user_first_name",
        nullable = false
    )
    private String userFirstName;

    @Column(
        name = "user_last_name",    
        nullable = false
    )
    private String userLastName;

    @Column(
        name = "user_dob",
        nullable = false
    )
    private LocalDate userDob;

    @Column(
        
        name = "user_mobile",
        nullable = false
    )
    private String userMobile;

    // Relations
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<CustomTrip> customTripList;

    public User() {
    }

    

    public User(Integer userId, String userEmail, String userFirstName, String userLastName, LocalDate userDob, String userMobile) {
        this.userId = userId;
        this.userEmail = userEmail;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userDob = userDob;
        this.userMobile = userMobile;
    }



    public Integer getUserId() {
        return userId;
    }



    public void setUserId(Integer userId) {
        this.userId = userId;
    }



    public String getUserEmail() {
        return userEmail;
    }



    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }



    public String getUserFirstName() {
        return userFirstName;
    }



    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }



    public String getUserLastName() {
        return userLastName;
    }



    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }



    public LocalDate getUserDob() {
        return userDob;
    }



    public void setUserDob(LocalDate userDob) {
        this.userDob = userDob;
    }



    public String getUserMobile() {
        return userMobile;
    }



    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }



    public List<CustomTrip> getCustomTripList() {
        return customTripList;
    }



    public void setCustomTripList(List<CustomTrip> customTripList) {
        this.customTripList = customTripList;
    }

    
    
}
