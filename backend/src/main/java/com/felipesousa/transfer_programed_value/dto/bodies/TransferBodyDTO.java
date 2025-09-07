package com.felipesousa.transfer_programed_value.dto.bodies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferBodyDTO {

    private String accountOrigin;
    private String accountDestiny;
    private BigDecimal valueTransfer;
    private LocalDateTime scheduleTransferDate;

}
