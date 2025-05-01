package model;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyFormatter {
    @SuppressWarnings("deprecation")
    private static final NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "PH"));

    public static String format(int amount) {
        return formatter.format(amount);
    }
}