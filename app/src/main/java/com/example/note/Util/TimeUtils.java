package com.example.note.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class TimeUtils {

    public static String getCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 a h : mm");
        Calendar calendar =Calendar.getInstance();
        String time = formatter.format(calendar.getTime());
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        String amOrPm = hourOfDay > 12 ? "晚上" : "早上" ;
        time = time.replace("a",amOrPm);
        return  time;
    }

    public static Date getCurrentDate(){
        long timestamp = System.currentTimeMillis();
        Date date = new Date(timestamp);
        return  date;
    }
}
