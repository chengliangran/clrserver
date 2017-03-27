package com.jfinalserver.test;

import com.finalserver.core.Constants;
import com.sun.corba.se.spi.activation.Repository;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import jdk.nashorn.internal.runtime.linker.Bootstrap;
import org.xml.sax.InputSource;

import java.io.*;
import java.net.*;
import java.security.Timestamp;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.SimpleFormatter;

/**
 * Created by Administrator on 2017-03-07.
 */
public class Test {
    public static void main(String[] args) {
        File file=new File(Constants.WEB_ROOT);
        try {
            FileOutputStream fis=new FileOutputStream(file+file.separator+"ceshi.txt",true);
            fis.write("asdkasdjkasd".getBytes());
            System.out.println(ClassLoader.getSystemClassLoader());
            System.out.println(Constants.WEB_ROOT);
         } catch (IOException e) {
            e.printStackTrace();
        }

        InputSource is=new InputSource();
     }
}
