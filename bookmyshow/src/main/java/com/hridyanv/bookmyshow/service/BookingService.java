package com.hridyanv.bookmyshow.service;

import com.hridyanv.bookmyshow.models.*;
import com.hridyanv.bookmyshow.repositories.ShowRepository;
import com.hridyanv.bookmyshow.repositories.ShowSeatRepository;
import com.hridyanv.bookmyshow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking bookMovie(Long userId, Long showId, List<Long> showSeatIds)
    {
        /*
        * 1. Get the User from userId
        * 2. Get the show from showId
        * 3. Get the showSeat from showSeatIds
        * 4. Check if seats are available
        * 5. If yes, make the status as blocked or BIP
        * 6. Save Updated showSeats in DB and end the lock
        * */
        Optional<User> userOptional=userRepository.findById(userId);
        if(userOptional.isEmpty())
        {
            throw new RuntimeException();
        }
        User user = userOptional.get();
        Optional<Show> showOptional=showRepository.findById(showId);
        if(showOptional.isEmpty())
        {
            throw new RuntimeException();
        }
        Show show = showOptional.get();
        List<ShowSeat> showSeats=showSeatRepository.findAllById(showSeatIds);
        for(ShowSeat showSeat:showSeats)
        {
            if(!(showSeat.getShowSeatStatus().equals(ShowSeatStatus.AVAILABLE) ||
                    (showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED) &&
                            Duration.between(showSeat.getBlockedAt().toInstant(), Instant.now()).toMinutes()>15)))
            {
                throw new RuntimeException();
            }
        }
        List<ShowSeat> savedShowSeat = new ArrayList<>();
        for (ShowSeat showSeat:showSeats)
        {
            showSeat.setShowSeatStatus(ShowSeatStatus.BLOCKED);
            showSeat.setBlockedAt(new Date());
            savedShowSeat.add(showSeatRepository.save(showSeat));
        }
        return null;
    }
}
