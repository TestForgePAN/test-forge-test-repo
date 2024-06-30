package com.travelapp.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.travelapp.backend.models.User;
import com.travelapp.backend.services.CustomTripService;
import com.travelapp.backend.services.PremadeTripService;
import com.travelapp.backend.services.UserService;

@Configuration
public class MockDataConfig {

    
    
    // some comment
    // private UserService userService;
    // private PremadeTripService premadeTripService;
    // private CustomTripService customTripService;

    // @Autowired
    // public MockDataConfig(UserService userService, PremadeTripService premadeTripService, CustomTripService customTripService ){
    //     this.userService = userService;
    //     this.premadeTripService = premadeTripService;
    //     this.customTripService = customTripService;
    // }

    // public void loadMockData(){
    //     User user = new User();
    //     user.setUserEmail("soonann.tan@outlook.com");
    //     user.setUserFirstName("Tan");
    //     user.setUserLastName("Soon Ann");
    //     user.setUserMobile("91237777");
    //     userService.addNewUser(user);
    // }
}
