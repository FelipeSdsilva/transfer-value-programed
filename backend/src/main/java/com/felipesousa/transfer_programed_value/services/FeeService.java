package com.felipesousa.transfer_programed_value.services;

import com.felipesousa.transfer_programed_value.dto.bodies.FeeBodyDTO;
import com.felipesousa.transfer_programed_value.dto.response.FeeDTO;
import com.felipesousa.transfer_programed_value.entities.Fee;
import com.felipesousa.transfer_programed_value.exceptions.costumExceptions.DateRageInvalidException;
import com.felipesousa.transfer_programed_value.exceptions.costumExceptions.ResourceNotFoundException;
import com.felipesousa.transfer_programed_value.repositories.FeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
@RequiredArgsConstructor
public class FeeService {

    private final FeeRepository feeRepository;

    @Transactional(readOnly = true)
    public Page<FeeDTO> listAllFeesPaginated(Pageable pageable) {
        return feeRepository.findAll(pageable).map(FeeDTO::new);
    }

    @Transactional(readOnly = true)
    public FeeDTO retrieverFeePerId(Long id) {
        return new FeeDTO(feeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fee not found per id " + id)));
    }

    @Transactional(readOnly = true)
    protected BigDecimal retrieverFeePerDayValue(Integer days, BigDecimal value) {

        FeeDTO fee = new FeeDTO(feeRepository.findFeeByDays(days)
                .orElseThrow(() -> new DateRageInvalidException("Date not register in table!")));

        BigDecimal fixedFee = fee.getFixedFee();
        BigDecimal percentage = fee.getPercentage()
                .divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);

        return fixedFee.add(value.multiply(percentage));
    }

    @Transactional
    public FeeDTO insertNewFee(FeeBodyDTO dto) {
        Fee fee = new Fee();
        convertDtoInEntity(fee, dto);
        fee = feeRepository.save(fee);
        return new FeeDTO(fee);
    }


    @Transactional
    public FeeDTO updateFeePerId(Long id, FeeBodyDTO dto) {
        try {
            Fee fee = feeRepository.getReferenceById(id);
            convertDtoInEntity(fee, dto);
            fee = feeRepository.save(fee);
            return new FeeDTO(fee);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Fee not found per id " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteFeePerId(Long id) {

    }

    private void convertDtoInEntity(Fee fee, FeeBodyDTO dto) {
        fee.setMinDays(dto.getMinDays());
        fee.setMaxDays(dto.getMaxDays());
        fee.setPercentage(dto.getPercentage());
        fee.setFixedFee(dto.getFixedFee());
    }
}
