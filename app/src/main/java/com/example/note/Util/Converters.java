package com.example.note.Util;

import androidx.room.TypeConverter;

import java.util.Date;

public class Converters {
    @TypeConverter
    public static Long  dateToTimestamp(Date date){
        return date.getTime();
    }

    @TypeConverter
    public static Date TimestampToDate(Long dateLong){
        return new Date(dateLong);
    }
}
