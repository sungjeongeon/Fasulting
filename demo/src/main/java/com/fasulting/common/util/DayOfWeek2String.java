package com.fasulting.common.util;

public class DayOfWeek2String {

    private static String[] dayOfWeek2Kor = {"", "일", "월", "화", "수", "목", "금", "토"};

    public static String getStringDayOfWeek(int dayOfWeek){
        return dayOfWeek2Kor[dayOfWeek];
    }
}
