package com.fasulting.demo.common.util;

public class Date2String {

    public static String Date2String(int year, int month, int day, int dayOfWeek, int hour){
        String date = year + "." +
                String.format("%02d",month) + "." +
                String.format("%02d",day) + " (" +
                DayOfWeek2String.getStringDayOfWeek(dayOfWeek) + ") " +
                String.format("%02d", hour) + "시";

        return date;
    }

    public static String Date2String(int year, int month, int day, int dayOfWeek, int hour, int min){
        String date = year + "." +
                String.format("%02d",month) + "." +
                String.format("%02d",day) + " (" +
                DayOfWeek2String.getStringDayOfWeek(dayOfWeek) + ") " +
                String.format("%02d", hour) + "시 " +
                String.format("%02d", min) + "분";

        return date;
    }
}
