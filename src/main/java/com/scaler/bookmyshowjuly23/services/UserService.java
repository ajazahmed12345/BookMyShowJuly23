package com.scaler.bookmyshowjuly23.services;

import com.scaler.bookmyshowjuly23.models.User;
import com.scaler.bookmyshowjuly23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User login(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()){
            throw new RuntimeException();
        }

        User user = userOptional.get();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(encoder.matches(password, user.getPassword())){
            return user;
        }

        throw new RuntimeException();
    }

    public User signUp(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isPresent()){
            throw new RuntimeException();
        }

        User user = new User();
        user.setEmail(email);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password));
        user.setBookings(new ArrayList<>());

        User savedUser = userRepository.save(user);
        return savedUser;
    }
}
