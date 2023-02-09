package com.fasulting.common.util;

public class Date2String {

    public static String date2String(int year, int month, int day, int dayOfWeek, int hour, int min){
        String date = year + "." +
                String.format("%02d",month) + "." +
                String.format("%02d",day) + " (" +
                DayOfWeek2String.getStringDayOfWeek(dayOfWeek) + ") " +
                String.format("%02d", hour) + "시 " +
                String.format("%02d", min) + "분";

        return date;
    }

    public static String date2TString(int year, int month, int day, int hour, int min){
        String date = year + "-" +
                String.format("%02d",month) + "-" +
                String.format("%02d",day) + "T" +
                String.format("%02d", hour) + ":" +
                String.format("%02d", min);

        return date;
    }

    public static String date2ParseString(int year, int month, int day, int hour, int min){
        String date = year + "-" +
                String.format("%02d",month) + "-" +
                String.format("%02d",day) + " " +
                String.format("%02d", hour) + ":" +
                String.format("%02d", min);

        return date;
    }
}
