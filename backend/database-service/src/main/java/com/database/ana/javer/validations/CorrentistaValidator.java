package com.database.ana.javer.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CorrentistaValidator implements ConstraintValidator<ValidCorrentista, Boolean> {

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        return value != null && (value.equals(Boolean.TRUE) || value.equals(Boolean.FALSE)); // aceitando só valores true, false para ter certeza!!
    }
}
