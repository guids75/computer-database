package com.excilys.formation.computerdatabase.validation.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.context.i18n.LocaleContextHolder;

import com.excilys.formation.computerdatabase.dto.ComputerDto;

public class DateValidator implements ConstraintValidator<ValidDate, ComputerDto> {

    @Override
    public void initialize(ValidDate date) {
    }

    @Override
    public boolean isValid(ComputerDto computer, ConstraintValidatorContext constraintValidatorContext) {
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundle messages = ResourceBundle.getBundle("cdb", locale);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(messages.getString("label.dateFormatter"));
        LocalDate introduced = null, discontinued = null;
        
        if (computer == null) {
            return true;
        }
        String computerIntroduced = computer.getIntroduced(),
                computerDiscontinued = computer.getDiscontinued();
        try {
            if (!computerIntroduced.isEmpty()) {
                introduced = LocalDate.parse(computerIntroduced, dateTimeFormatter);
            }
        } catch (Exception exception) {
            return changeContext("the date is invalid", "introduced", constraintValidatorContext);
        }
        try {
            if (!computerDiscontinued.isEmpty()) {
                discontinued = LocalDate.parse(computerDiscontinued, dateTimeFormatter);   
            }
        } catch (Exception exception) {
            return changeContext("the date is invalid", "discontinued", constraintValidatorContext);
        }

        if (introduced != null && discontinued != null) {
            if (!introduced.isBefore(discontinued)) {
                return changeContext("the date of introduction must be interior of discontinued one", "introduced", constraintValidatorContext);
            }
        }
        return true;
    }

    private boolean changeContext(String problem, String attribute, ConstraintValidatorContext constraintValidatorContext) {
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext
        .buildConstraintViolationWithTemplate( problem )
        .addPropertyNode( attribute ).addConstraintViolation();
        return false;
    }


}