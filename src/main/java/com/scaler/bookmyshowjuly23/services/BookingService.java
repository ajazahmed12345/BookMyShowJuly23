package com.scaler.bookmyshowjuly23.services;

import com.scaler.bookmyshowjuly23.models.*;
import com.scaler.bookmyshowjuly23.repositories.BookingRepository;
import com.scaler.bookmyshowjuly23.repositories.ShowRepository;
import com.scaler.bookmyshowjuly23.repositories.ShowSeatRepository;
import com.scaler.bookmyshowjuly23.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private BookingRepository bookingRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private UserRepository userRepository;
    private PriceCalculator priceCalculator;

    public BookingService(BookingRepository bookingRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, UserRepository userRepository, PriceCalculator priceCalculator) {
        this.bookingRepository = bookingRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.userRepository = userRepository;
        this.priceCalculator = priceCalculator;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, List<Long> seatIds, Long showId){
        // 1. Get user with userId
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException();
        }

        User bookedBy = userOptional.get();

        // 2. get Show with showId
        Optional<Show> showOptional = showRepository.findById(showId);
        if(showOptional.isEmpty()){
            throw new RuntimeException();
        }

        Show bookedShow = showOptional.get();

        // 3. Get showSeats from seatIds
        List<ShowSeat> showSeats = showSeatRepository.findAllById(seatIds);

        // 4. check if all show seats are AVAILABLE
//        boolean allAvailable = true;

        for(ShowSeat showSeat : showSeats){
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE)) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getBlockedAt().toInstant(), new Date().toInstant()).toMinutes() <= 15)){
//                allAvailable = false;
                //5. if no, throw exception
                throw new RuntimeException();
            }
        }

        // 6. Mark the status to BLOCKED
        List<ShowSeat> savedShowSeats = new ArrayList<>();
        for(ShowSeat showSeat : showSeats) {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);

            // 7. Save the updated showSeats to DB
            ShowSeat savedShowSeat = showSeatRepository.save(showSeat);
            savedShowSeats.add(savedShowSeat);
        }

        // 8. Create corresponding Booking object

        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setShowSeats(savedShowSeats);
        booking.setUser(bookedBy);
        booking.setBookedAt(new Date());
        booking.setShow(bookedShow);
        booking.setAmount(priceCalculator.calculatePrice(savedShowSeats, bookedShow));
        booking.setPayments(new ArrayList<>());

        Booking savedBooking = bookingRepository.save(booking);

        // 9. Return Booking object

        return savedBooking;
    }
}
