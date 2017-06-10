package iq.ven.workflow.services;

import iq.ven.workflow.models.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationService {
    private static final String REGEX_FIRST_AND_LAST_NAME = "^[a-zA-ZА-Яа-я]{3,30}$";
    private static final String REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String REGEX_PASSWORD = "^[\\S]{5,30}$";

    private static boolean checkWithRegExp(String stringToCheck, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(stringToCheck);
        return m.matches();
    }

    public static boolean validate(User user) {
        return checkWithRegExp(user.getFirstName(), REGEX_FIRST_AND_LAST_NAME)
                && checkWithRegExp(user.getLastName(), REGEX_FIRST_AND_LAST_NAME)
                && checkWithRegExp(user.getEmail(), REGEX_EMAIL)
                && checkWithRegExp(user.getPassword(), REGEX_PASSWORD);
    }

}
