package com.yash.yotaapi.util;

import java.util.Date;

public class ValidationUtility {

    private static final String ALPHABETIC_PATTERN = "^[a-zA-Z]+$";

    public static boolean isAlphabetic(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        return input.matches(ALPHABETIC_PATTERN);
    }

    public static boolean isNumerical(String input) {
        if (input == null || input.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isEndDateGreater(Date startDate, Date endDate) {
        if(startDate.after(endDate)){
            return false;
        }
        return true;
    }
}
