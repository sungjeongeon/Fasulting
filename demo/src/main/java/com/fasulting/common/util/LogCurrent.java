package com.fasulting.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogCurrent {

    public static final String START = "Start";
    public static final String END = "End";

    /**
     * 실행중인 클래스 이름을 취득
     * @return 클래스명
     */
    public static String getClassName() {
        String classFullName = Thread.currentThread().getStackTrace()[2].getClassName();
        return classFullName.substring(classFullName.lastIndexOf(".") + 1);
    }

    /**
     * 실행중인 함수를 취득。
     * @return 함수명
     */
    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public static String logCurrent(String className, String methodName, String startOrEnd) {
        return String.format("[CALL] %s %s %s %s", className, methodName, startOrEnd, LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
    }
}
