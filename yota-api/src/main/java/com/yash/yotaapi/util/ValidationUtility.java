package com.yash.yotaapi.util;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
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

    public boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        Pattern regexPattern = Pattern.compile("^[^@\\s]+@yash\\.com$");
        Matcher regMatcher = regexPattern.matcher(email);
        return regMatcher.matches();
    }
}
