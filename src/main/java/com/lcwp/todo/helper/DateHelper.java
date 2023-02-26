package com.lcwp.todo.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Date;

public class DateHelper {

    public static Date parseDate(LocalDateTime localDateTime) throws ParseException {

        /*SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        Date parsedate = simpleDateFormat.parse(dateStr);*/

        System.out.println(localDateTime);
       // Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        // Date parsedate = new Date(localDateTime.ge)

        Date date = Date.from(instant);


        return date;

    }


}
