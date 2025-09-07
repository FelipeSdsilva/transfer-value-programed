package com.felipesousa.transfer_programed_value.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ValidationError extends StandardError {

    private List<FieldMessage> errors;

    public void addError(String fieldName, String message) {
        errors.removeIf(fieldMessage -> fieldMessage.getMessage().equals(fieldName));

        errors.add(new FieldMessage(fieldName, message));
    }
}
