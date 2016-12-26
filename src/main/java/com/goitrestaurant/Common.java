package com.goitrestaurant;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Common {

    public static Date stringToDate(String dateInString) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return new Date(sdf1.parse(dateInString).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
