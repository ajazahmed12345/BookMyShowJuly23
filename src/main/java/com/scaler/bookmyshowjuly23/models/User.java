package com.scaler.bookmyshowjuly23.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity(name = "users_table")
public class User extends BaseModel{
    private String number;
    private String email;
    @OneToMany
    private List<Booking> bookings;
}
