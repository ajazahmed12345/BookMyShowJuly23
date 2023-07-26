package com.scaler.bookmyshowjuly23.controllers;

import com.scaler.bookmyshowjuly23.Dtos.ResponseStatus;
import com.scaler.bookmyshowjuly23.Dtos.SignUpRequestDto;
import com.scaler.bookmyshowjuly23.Dtos.SignUpResponseDto;
import com.scaler.bookmyshowjuly23.models.User;
import com.scaler.bookmyshowjuly23.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDto signUp(SignUpRequestDto request){
        User user;
        SignUpResponseDto response = new SignUpResponseDto();
        try {
            user = userService.signUp(request.getEmail(), request.getPassword());

            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setUserId(user.getId());
        }catch(Exception e){
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }
}
