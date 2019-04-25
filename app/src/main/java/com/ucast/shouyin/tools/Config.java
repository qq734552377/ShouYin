package com.ucast.shouyin.tools;

import com.ucast.shouyin.exception.CrashHandler;

/**
 * Created by pj on 2019/1/28.
 */
public class Config {
    public static int PORTBAUDRATE = 115200;
    public static String PORTPATHE = "";

    public static boolean isHiddenBottom = false;
    public static String DBNAME = "ucast_shouyin";
    public static int ONELINEMONEYSHOWNUMBER = 20;
    public static String CHARSET = "GB18030";
    public static String LOGPATH = CrashHandler.ALBUM_PATH + "/simpleLog.txt";


    public static long TOKENTIMEOUT = 58 * 60 * 1000;
}
