package com.felipesousa.transfer_programed_value.controllers;

import com.felipesousa.transfer_programed_value.controllers.docs.BankTransferDoc;
import com.felipesousa.transfer_programed_value.dto.bodies.TransferBodyDTO;
import com.felipesousa.transfer_programed_value.dto.response.BankTransferDTO;
import com.felipesousa.transfer_programed_value.services.BankTransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequiredArgsConstructor
public class BankTransferController implements BankTransferDoc {

    private final BankTransferService bankTransferService;

    @Override
    public ResponseEntity<Page<BankTransferDTO>> getAllBankTransferPaginated(Pageable pageable) {
        return ResponseEntity.ok(bankTransferService.listAllBankTransfersPaginated(pageable));
    }

    @Override
    public ResponseEntity<BankTransferDTO> getBankTransferPerId(Long id) {
        return ResponseEntity.ok(bankTransferService.retrieverBankPerId(id));
    }

    @Override
    public ResponseEntity<BankTransferDTO> postNewBankTrans(TransferBodyDTO dto) {
        BankTransferDTO transfer = bankTransferService.insertNewBankTransfer(dto);
        var uri = fromCurrentRequest().path("/{id}").buildAndExpand(transfer.getId()).toUri();
        return ResponseEntity.created(uri).body(transfer);
    }

    @Override
    public ResponseEntity<BankTransferDTO> putBankTransferPerId(Long id, TransferBodyDTO dto) {
        return ResponseEntity.ok(bankTransferService.updateBankTransferPerId(id, dto));
    }

    @Override
    public ResponseEntity<Void> deleteBankTransferPerId(Long id) {
        bankTransferService.deleteBankTransferPerId(id);
        return ResponseEntity.noContent().build();
    }
}
