package com.jfinalserver.test;

import com.finalserver.core.Constants;
import com.sun.corba.se.spi.activation.Repository;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import jdk.nashorn.internal.runtime.linker.Bootstrap;

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
//        String s="GET / HTTP/1.1\n" +
//                "Host: localhost:8080\n" +
//                "Connection: keep-alive\n" +
//                "Cache-Control: max-age=0\n" +
//                "Upgrade-Insecure-Requests: 1\n" +
//                "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.104 Safari/537.36 Core/1.53.2141.400 QQBrowser/9.5.10219.400\n" +
//                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n" +
//                "Accept-Encoding: gzip, deflate, sdch\n" +
//                "Accept-Language: zh-CN,zh;q=0.8\n" +
//                "Cookie: Idea-81111fc3=2ea38edc-155f-4already set the socket\n" +
//                "f9d-b9e6-f75c2c4609ec";
//        System.out.println(s.toCharArray().length);
//        URL[] urls=new URL[1];
//        File file=new File(Constants.WEB_ROOT);
//        URLStreamHandler urlStreamHandler=null;
//        String repository=null;
//        try {
//            repository=(new URL("file",null,file.getCanonicalPath()+File.separator)).toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            urls[0]=new URL(null,repository,urlStreamHandler);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        System.out.println(urls[0]);
//        System.out.println(file.getAbsolutePath());
//        System.out.println(file.getPath());
//        File current=new File("/");
//        System.out.println(current.getAbsolutePath());
//        System.out.println(current.getPath());
//        Date date=new Date();
//        java.sql.Timestamp timestamp=new java.sql.Timestamp(System.currentTimeMillis());
//
//        System.out.println(date);
//        System.out.println(timestamp);
//        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("");
//        System.out.println(System.getProperty("user.dir"));
//        java.sql.Timestamp timestamp1=new java.sql.Timestamp(System.currentTimeMillis());
//        System.out.println(timestamp.getClass());
//        new Integer(213).getClass().getClassLoader();
//        java.lang.ClassLoader classLoader= ArrayList.class.getClassLoader();
//         classLoader= Bootstrap.class.getClassLoader();
//         System.out.println(new File("/").getAbsolutePath());
         java.lang.ClassLoader classLoader=Test.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
    }
}
