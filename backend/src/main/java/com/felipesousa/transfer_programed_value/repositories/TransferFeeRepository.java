package com.felipesousa.transfer_programed_value.repositories;

import com.felipesousa.transfer_programed_value.entities.TransferFee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferFeeRepository extends JpaRepository<TransferFee, Long> {
}
