package com.felipesousa.transfer_programed_value.controllers;


import com.felipesousa.transfer_programed_value.controllers.docs.FeeDoc;
import com.felipesousa.transfer_programed_value.dto.bodies.FeeBodyDTO;
import com.felipesousa.transfer_programed_value.dto.response.FeeDTO;
import com.felipesousa.transfer_programed_value.services.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
@RequiredArgsConstructor
public class FeeController implements FeeDoc {

    private final FeeService feeService;

    @Override
    public ResponseEntity<Page<FeeDTO>> getAllFeePaginated(Pageable pageable) {
        return ResponseEntity.ok(feeService.listAllFeesPaginated(pageable));
    }

    @Override
    public ResponseEntity<FeeDTO> getFeePerId(Long id) {
        return ResponseEntity.ok(feeService.retrieverFeePerId(id));
    }

    @Override
    public ResponseEntity<FeeDTO> postNewFee(FeeBodyDTO dto) {
        FeeDTO fee = feeService.insertNewFee(dto);
        var uri = fromCurrentRequest().path("/{id}").buildAndExpand(fee.getId()).toUri();
        return ResponseEntity.created(uri).body(fee);
    }

    @Override
    public ResponseEntity<FeeDTO> putFeePerId(Long id, FeeBodyDTO dto) {
        return ResponseEntity.ok(feeService.updateFeePerId(id, dto));
    }

    @Override
    public ResponseEntity<Void> deleteFeePerId(Long id) {
        feeService.deleteFeePerId(id);
        return ResponseEntity.noContent().build();
    }
}
