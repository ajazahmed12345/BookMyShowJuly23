package com.scaler.bookmyshowjuly23.controllers;

import com.scaler.bookmyshowjuly23.Dtos.BookMovieRequestDto;
import com.scaler.bookmyshowjuly23.Dtos.BookMovieResponseDto;
import com.scaler.bookmyshowjuly23.Dtos.ResponseStatus;
import com.scaler.bookmyshowjuly23.models.Booking;
import com.scaler.bookmyshowjuly23.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    private BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    public BookMovieResponseDto bookMovie(BookMovieRequestDto request){

        BookMovieResponseDto response = new BookMovieResponseDto();

        Booking booking;

        try {
            booking = bookingService.bookMovie(request.getUserId(), request.getShowSeatIds(), request.getShowId());

            response.setBookingId(booking.getId());
            response.setResponseStatus(ResponseStatus.SUCCESS);
            response.setAmount(booking.getAmount());

        }catch(Exception e){
            response.setResponseStatus(ResponseStatus.FAILURE);
        }

        return response;
    }
}
