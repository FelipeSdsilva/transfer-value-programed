package com.felipesousa.transfer_programed_value.services;

import com.felipesousa.transfer_programed_value.dto.bodies.TransferBodyDTO;
import com.felipesousa.transfer_programed_value.dto.response.BankTransferDTO;
import com.felipesousa.transfer_programed_value.entities.BankTransfer;
import com.felipesousa.transfer_programed_value.exceptions.costumExceptions.ResourceNotFoundException;
import com.felipesousa.transfer_programed_value.repositories.BankTransferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BankTransferService {

    private final BankTransferRepository bankTransferRepository;
    private final FeeService feeService;

    @Transactional(readOnly = true)
    public Page<BankTransferDTO> listAllBankTransfersPaginated(Pageable pageable) {
        return bankTransferRepository.findAll(pageable).map(BankTransferDTO::new);
    }

    @Transactional(readOnly = true)
    public BankTransferDTO retrieverBankPerId(Long id) {
        return new BankTransferDTO(bankTransferRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("")));
    }

    @Transactional
    public BankTransferDTO insertNewBankTransfer(TransferBodyDTO dto) {
        BankTransfer entity = new BankTransfer();
        convertDtoInEntity(entity, dto);
        entity = bankTransferRepository.save(entity);
        return new BankTransferDTO(entity);
    }


    @Transactional
    public BankTransferDTO updateBankTransferPerId(Long id, TransferBodyDTO dto) {
        try {
            BankTransfer entity = bankTransferRepository.getReferenceById(id);
            convertDtoInEntity(entity, dto);
            entity = bankTransferRepository.save(entity);
            return new BankTransferDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Bank Transfer not found per id " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteBankTransferPerId(Long id) {

    }

    private void convertDtoInEntity(BankTransfer entity, TransferBodyDTO dto) {
        entity.setTransferDate(Instant.now());
        entity.setValueTransfer(dto.getValueTransfer());
        entity.setAccountDestiny(dto.getAccountDestiny());
        entity.setAccountOrigin(dto.getAccountOrigin());

        Integer days = Math.toIntExact(ChronoUnit.DAYS.between(
                entity.getTransferDate().atZone(ZoneOffset.UTC).toLocalDate(),
                dto.getScheduleTransferDate().atZone(ZoneOffset.UTC).toLocalDate()));

        entity.setPayTax(feeService.retrieverFeePerDayValue(days, dto.getValueTransfer()));

        Instant scheduleInstant = dto.getScheduleTransferDate()
                .atZone(ZoneId.systemDefault())
                .toInstant();

        entity.setScheduleTransferDate(scheduleInstant);
    }
}
