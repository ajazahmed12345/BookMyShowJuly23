package com.scaler.bookmyshowjuly23.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookMovieResponseDto {
    private ResponseStatus responseStatus;
    private int amount;
    private Long bookingId;
}
