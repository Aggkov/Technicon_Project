package com.europeandynamics.utils.validator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.regex.Pattern;

import com.europeandynamics.exceptions.BadRequestException;
import com.europeandynamics.exceptions.InvalidEmailException;
import com.europeandynamics.model.enums.HttpStatus;
import com.europeandynamics.model.enums.RepairType;

public class InputValidator {

    public static boolean checkEmail(String email) throws InvalidEmailException {
        if (Pattern.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}" + "~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\."
                + "[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)" + "+[a-zA-Z]{2,}))$", email.trim())) {
            return true;
        } else {
            throw new InvalidEmailException("Email is not valid", HttpStatus.BAD_REQUEST);
        }
    }

    public static boolean checkCostOfRepair(BigDecimal costOfRepair) {

        if(costOfRepair.doubleValue() > new BigDecimal("3000").doubleValue()) {

        throw new BadRequestException("cost of repair must not exceed 3000 Euros ", HttpStatus.BAD_REQUEST);
        }


        return true;
    }

    public static void dateTimeOfRepairMustBeOnPresentOrFuture(LocalDateTime localDateTime) {

        if(localDateTime.isBefore(LocalDateTime.now()) || localDateTime.getYear() >= 2030) {
            throw new BadRequestException("Date must a present date or future date, up until year 2030", HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkRepairTypeEnum(RepairType repairType) {

        Arrays.stream(RepairType.values())
                .filter(type -> type.equals(repairType))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("repair type is not valid", HttpStatus.BAD_REQUEST));
    }

    public static boolean checkVatNumber(String vatNumber) {
        if (Pattern.matches("(?<!\\d)\\d{9}(?!\\d)", vatNumber)) {
            return true;
        } else {
            throw new BadRequestException("This is not a valid VatNumber with 9 digits",HttpStatus.BAD_REQUEST);
        }
    }

    public static boolean checkPropertyRepairId(String repairId) {
        if (Pattern.matches("(?<!\\d)\\d{3}(?!\\d)", repairId)) {
            return true;
        } else {
            throw new BadRequestException("This is not a valid PropertyRepairId with 3 digits",HttpStatus.BAD_REQUEST);
        }
    }
}
