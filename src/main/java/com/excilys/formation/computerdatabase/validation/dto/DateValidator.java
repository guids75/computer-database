package com.excilys.formation.computerdatabase.validation.dto;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.excilys.formation.computerdatabase.dto.ComputerDto;

public class DateValidator implements ConstraintValidator<ValidDate, String> {

    @Override
    public void initialize(ValidDate date) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext ctx) {
        LocalDate localDate;
        if (date != null) {
            try {
                localDate = LocalDate.parse(date);   
                if (localDate.getYear() < 1970) {
                    return false;
                }
            } catch (Exception exception) {
                return false;
            }
        }
        return true;
    }
        

    }