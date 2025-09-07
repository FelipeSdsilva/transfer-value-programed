package com.felipesousa.transfer_programed_value.dto.response;

import com.felipesousa.transfer_programed_value.entities.BankTransfer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankTransferDTO {

    private Long id;
    private String accountOrigin;
    private String accountDestiny;
    private BigDecimal valueTransfer;
    private BigDecimal payTax;
    private Instant scheduleTransferDate;
    private Instant transferDate;

    public BankTransferDTO(BankTransfer entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
