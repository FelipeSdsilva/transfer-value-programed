package com.felipesousa.transfer_programed_value.services;

import com.felipesousa.transfer_programed_value.repositories.TransferFeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransferFeeService {

    private final TransferFeeRepository feeRepository;


}
