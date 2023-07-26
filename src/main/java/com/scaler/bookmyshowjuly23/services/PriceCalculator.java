package com.scaler.bookmyshowjuly23.services;

import com.scaler.bookmyshowjuly23.models.Show;
import com.scaler.bookmyshowjuly23.models.ShowSeat;
import com.scaler.bookmyshowjuly23.models.ShowSeatType;
import com.scaler.bookmyshowjuly23.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceCalculator {
    private ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculator(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int calculatePrice(List<ShowSeat> showSeats, Show show){

        // 1. Get showSeatTypes for that show
        List<ShowSeatType> showSeatTypes = showSeatTypeRepository.findAllByShow(show);
        // 2. get seatTypes for all showSeats
        int sum = 0;

        for(ShowSeat showSeat : showSeats){
            for(ShowSeatType type : showSeatTypes){
                if(showSeat.getSeat().getSeatType().equals(type)){
                    // 3. add to sum
                    sum += type.getPrice();
                    break;
                }
            }
        }

        return sum;
    }
}
