package com.felipesousa.transfer_programed_value.dto.response;

import com.felipesousa.transfer_programed_value.entities.Fee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeeDTO {

    private Long id;
    private BigDecimal fixedFee;
    private BigDecimal percentage;

    public FeeDTO(Fee entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
