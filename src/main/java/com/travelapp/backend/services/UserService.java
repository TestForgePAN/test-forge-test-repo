package com.travelapp.backend.services;

import java.util.List;
import java.util.Optional;

import com.travelapp.backend.models.User;
import com.travelapp.backend.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User getUserById(Integer userId){
        Optional<User> user = this.userRepository.findById(userId);
        if (user.isEmpty()){
            throw new RuntimeException("Unable to find user with ID " + userId);
        }
        
        return user.get();
    }

    public User addNewUser(User user){

        // check user validity 
        // TODO: add more validations
        if(this.userRepository.findUserByUserEmail(user.getUserEmail()).isPresent() ){
            throw new RuntimeException("Email is already present");
        }
        
        if(user.getUserFirstName() == null || user.getUserFirstName().length() == 0 ||user.getUserLastName() == null || user.getUserLastName().length() == 0){
            throw new RuntimeException("Invalid First/Last Names");
        }
        

        this.userRepository.save(user);
        return user;
    }

 
    public User updateUser(Integer paramsId, User params){
        User user = this.userRepository.findById(paramsId).orElseThrow(() -> new RuntimeException("User with id does not exist"));
        
        // TODO: overwrite id?
        // if (params.getUserId() != null){
           
        // }

        if (params.getUserFirstName() != null){
            user.setUserFirstName(params.getUserFirstName() );
        }

        if (params.getUserDob() != null){
            user.setUserDob(params.getUserDob());
        }

        if (params.getUserLastName() != null){
            user.setUserLastName(params.getUserLastName());
        }

        if (params.getUserMobile() != null){
            user.setUserMobile(params.getUserMobile());
        }

        return user;
    }


    public User updateUserById(Integer userId, String userEmail, String userFirstName, String userLastName, String userMobile){
        //TODO: add more validations
        //TODO: test
        User user = this.userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User with id does not exist"));
        
        // email checks
        if(userEmail != null && this.userRepository.findUserByUserEmail(userEmail).isPresent()){
            throw new RuntimeException("The email address is already used");
        }
        else if (userEmail != null){
            user.setUserEmail(userEmail);
        }


        if (userFirstName != null){
            user.setUserFirstName(userFirstName);
        }

        if (userLastName != null){
            user.setUserLastName(userLastName);
        }

        if (userMobile != null){
            user.setUserMobile(userMobile);
        }

        this.userRepository.save(user);
        return user;

    }

    
    public void deleteUserById(Integer id){

        // TODO: add more validations
        if (this.userRepository.findById(id).isEmpty()){
            
            throw new RuntimeException("User with id of " + id + " does not exist");
        }

        this.userRepository.deleteById(id);
    }

}
