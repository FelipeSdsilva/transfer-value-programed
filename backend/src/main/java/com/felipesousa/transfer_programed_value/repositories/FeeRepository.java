package com.felipesousa.transfer_programed_value.repositories;

import com.felipesousa.transfer_programed_value.entities.Fee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FeeRepository extends JpaRepository<Fee, Long> {

    @Query("SELECT f FROM Fee f WHERE :days BETWEEN f.minDays AND f.maxDays")
    Optional<Fee> findFeeByDays(Integer days);

}
