package com.database.ana.javer.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CorrentistaValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCorrentista {
    String message() default "O campo correntista deve ser true, false, 1 ou 0";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
