package com.svalero.biblioteca.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.svalero.biblioteca.util.Constants.DATE_PATTERN;

public class DateUtils {

    public static String format(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        return dateFormat.format(date);
    }

    public static Date parse(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
        return new Date(dateFormat.parse(date).getTime());
    }
}
