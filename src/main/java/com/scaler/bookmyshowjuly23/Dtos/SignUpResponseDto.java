package com.scaler.bookmyshowjuly23.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpResponseDto {
    private ResponseStatus responseStatus;
    private Long userId;
}
