package com.felipesousa.transfer_programed_value.services;

import com.felipesousa.transfer_programed_value.repositories.BankTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BankTransferService {

    private final BankTransferRepository bankTransferRepository;



}
