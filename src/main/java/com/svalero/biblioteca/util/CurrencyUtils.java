package com.svalero.biblioteca.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class CurrencyUtils {

    public static String format(float value) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("es", "es"));
        return numberFormat.format(value);
    }

    public static float parse(String value) throws ParseException {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("es", "es"));
        return numberFormat.parse(value).floatValue();
    }
}
