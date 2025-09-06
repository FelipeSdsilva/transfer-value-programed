package com.felipesousa.transfer_programed_value.repositories;

import com.felipesousa.transfer_programed_value.entities.BankTransfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankTransferRepository extends JpaRepository<BankTransfer, Long> {
}
