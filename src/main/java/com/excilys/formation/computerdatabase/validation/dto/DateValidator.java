package com.excilys.formation.computerdatabase.validation.dto;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.excilys.formation.computerdatabase.dto.ComputerDto;

public class DateValidator implements ConstraintValidator<ValidDate, ComputerDto> {

    @Override
    public void initialize(ValidDate date) {
    }

    @Override
    public boolean isValid(ComputerDto computer, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;
        String problem = "", attribute = "";
        LocalDate introduced = null, discontinued = null;
        if (computer == null) {
            return true;
        }
        String computerIntroduced = computer.getIntroduced(),
                computerDiscontinued = computer.getDiscontinued();
        try {
            if (!computerIntroduced.isEmpty()) {
                introduced = LocalDate.parse(computerIntroduced);   
            }
        } catch (Exception exception) {
            attribute="introduced";
            problem="the date is invalid";
            isValid = false;
        }
        try {
            if (!computerDiscontinued.isEmpty()) {
                discontinued = LocalDate.parse(computerDiscontinued);   
            }
        } catch (Exception exception) {
            attribute="discontinued";
            problem="the date is invalid";
            isValid = false;
        }

        if (introduced != null && discontinued != null) {
            if (!introduced.isBefore(discontinued)) {
                attribute="introduced";
                problem="the date of introduction must be interior of discontinued one";
                isValid = false;
            }
        }

        if (!isValid) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext
            .buildConstraintViolationWithTemplate( problem )
            .addPropertyNode( attribute ).addConstraintViolation();
        }
        return isValid;
    }


}