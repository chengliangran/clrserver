package com.jfinalserver.test;

import com.finalserver.core.Constants;

import java.io.*;
import java.security.Timestamp;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by Administrator on 2017-03-07.
 */
public class Test {
    public static void main(String[] args) {
        Date date=new Date();
        System.out.println(date);
        String format="yyyymmdd HHMMSS";
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(format);
        String dates= simpleDateFormat.format(date);
        System.out.println(dates);
        java.sql.Timestamp timestamp=new java.sql.Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);






    }
}
