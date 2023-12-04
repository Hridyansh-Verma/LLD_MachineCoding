package com.hridyanv.bookmyshow.repositories;

import com.hridyanv.bookmyshow.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Override
    Optional<User> findById(Long aLong);
    @Override
    User save(User user);
}
