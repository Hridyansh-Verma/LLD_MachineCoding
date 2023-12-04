package com.hridyanv.bookmyshow.repositories;

import com.hridyanv.bookmyshow.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show,Long> {
    @Override
    Optional<Show> findById(Long aLong);

    @Override
    Show save(Show entity);
}
