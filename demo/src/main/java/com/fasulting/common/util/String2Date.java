package com.fasulting.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class String2Date {

    public static LocalDateTime string2Date(String dateStr){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm");
        return LocalDateTime.parse(dateStr, formatter);
    }
}
