package com.scaler.bookmyshowjuly23;

import com.scaler.bookmyshowjuly23.Dtos.SignUpRequestDto;
import com.scaler.bookmyshowjuly23.Dtos.SignUpResponseDto;
import com.scaler.bookmyshowjuly23.controllers.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BookMyShowJuly23Application implements CommandLineRunner {

    @Autowired
    private UserController userController;
    @Override
    public void run(String... args) throws Exception {
        SignUpRequestDto signUpRequestDto = new SignUpRequestDto();
        signUpRequestDto.setEmail("ajaz@gmail.com");
        signUpRequestDto.setPassword("password");

        SignUpResponseDto response = userController.signUp(signUpRequestDto);
        System.out.println(response.getResponseStatus());
    }

    public static void main(String[] args) {
        SpringApplication.run(BookMyShowJuly23Application.class, args);
    }

}
