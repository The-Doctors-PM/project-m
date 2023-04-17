package com.example.feelingfinder.Database;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class DateToStringConverter {
    public static String transform(LocalDate date){
        return date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.US) + " " +
                date.getDayOfMonth() + "/" + date.getMonthValue() + "/" + date.getYear();
    }

    public static int dateToInt(LocalDate date){
        int y,m,d;
        String ys,ms,ds;
        y = date.getYear();
        m = date.getMonthValue();
        d = date.getDayOfMonth();
        ys = String.valueOf(y);
        ms = String.valueOf(m);
        ds = String.valueOf(d);
        if (y < 1000){
            ys = "0"+y;
        }
        if (m < 10){
            ms = "0"+m;
        }
        if(d < 10){
            ds = "0"+d;
        }
        String supp = ys + ms + ds;
        int res = Integer.parseInt(supp);
        int a = 1;
        return res;
    }
}
