package com.felipesousa.transfer_programed_value.dto.bodies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeBodyDTO {

    private Integer minDays;
    private Integer maxDays;
    private BigDecimal fixedFee;
    private BigDecimal percentage;

}
