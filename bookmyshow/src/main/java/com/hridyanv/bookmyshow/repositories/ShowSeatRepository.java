package com.hridyanv.bookmyshow.repositories;

import com.hridyanv.bookmyshow.models.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat,Long> {
    @Override
    List<ShowSeat> findAllById(Iterable<Long> longs);

    @Override
    <S extends ShowSeat> S save(S entity);
}
