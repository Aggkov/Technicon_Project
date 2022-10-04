package com.europeandynamics.utils.validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.exceptions.InvalidEmailException;
import com.europeandynamics.model.enums.HttpStatus;

public class InputValidator {

    public static boolean checkEmail(String email) throws InvalidEmailException {
        if (Pattern.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}" + "~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\."
                + "[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)" + "+[a-zA-Z]{2,}))$", email.trim())) {
            return true;
        } else {
            throw new InvalidEmailException("Email is not valid", HttpStatus.BAD_REQUEST);
        }
    }

    public static boolean checkCostOfRepair(String costOfRepair) {
        try {
            BigDecimal cost = new BigDecimal(costOfRepair);

        }catch (NumberFormatException ex) {
            throw new BadRequestException("Character a is neither a decimal digit number, decimal point" +
                    ", nor \"e\" notation exponential mark.", HttpStatus.BAD_REQUEST);
        }
        return true;
    }

    public static void dateTimeMustBeOnPresentOrFuture(LocalDateTime localDateTime) {

        if(localDateTime.isBefore(LocalDateTime.now()) || localDateTime.getYear() >= 2030) {
            throw new BadRequestException("Date must a present date or future date, up until year 2030", HttpStatus.BAD_REQUEST);
        }
    }
}
