package com.hartmanmark.formula1.service;

public class Validator {

    public static String checkAbbreviation(String str) {
        if (str.matches("[A-Z]+")) {
            return str;
        } else {
            throw new IllegalArgumentException(
                    "The input abbreviations must be letters, 3 characters long, uppercase. For example: [ABC]");
        }
    }

    public static String checkDate(String data) {
        String datePattern = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
        if (data.matches(datePattern)) {
            return data;
        } else {
            throw new IllegalArgumentException(
                    "The format of input date must be [yyyy-MM-dd]. For example: [2018-05-24]");
        }
    }

    public static String checkTime(String time) {
        String datePattern = "(?:[0-9]{2}:[0-9]{2}:[0-9]{2})?(?:[.][0-9]{3})?";
        if (time.matches(datePattern)) {
            return time;
        } else {
            throw new IllegalArgumentException(
                    "The format of input time must be [HH:MM:SS.mmm]. For example: [12:02:58.917]");
        }
    }
}
