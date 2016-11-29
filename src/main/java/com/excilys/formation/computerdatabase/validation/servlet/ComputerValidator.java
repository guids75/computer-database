package com.excilys.formation.computerdatabase.validation.servlet;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerdatabase.dto.ComputerDto;

public class ComputerValidator {

    public static List<String> validate(ComputerDto computer) {
        List<String> errors = new ArrayList<String>();
        errors.addAll(validateName(computer.getName()));
        errors.addAll(validateDate(computer.getIntroduced(), computer.getDiscontinued()));
        return errors;
    }
    
    public static List<String> validateName(String computerName) {
        List<String> errorsName = new ArrayList<>();
        if (computerName.trim().length() < 2) {
            errorsName.add("The name " + computerName + " must have at least 2 characters");
        }
        return errorsName;
    }

    public static List<String> validateDate(String computerIntroduced, String computerDiscontinued) {
        List<String> errorsDate = new ArrayList<>();
        LocalDate introduced = null, discontinued = null;
        try {
          if (computerIntroduced != null) {
              introduced = LocalDate.parse(computerIntroduced);   
          }
          if (computerDiscontinued != null) {
              discontinued = LocalDate.parse(computerDiscontinued);   
          }
          if (computerIntroduced != null && computerDiscontinued != null) {
              if (!introduced.isBefore(discontinued)) {
                  errorsDate.add("The date " + computerIntroduced + " is not before the date " + computerDiscontinued);
              }
          }
        } catch (Exception exception) {
            errorsDate.add("The date is not valid");
        }
        return errorsDate;
    }

}
