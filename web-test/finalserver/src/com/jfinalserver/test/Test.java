package com.jfinalserver.test;

import com.finalserver.core.Constants;

import java.io.*;

/**
 * Created by Administrator on 2017-03-07.
 */
public class Test {
    public static void main(String[] args) {
        File file=new File(Constants.WEB_ROOT,"a.txt");
        try {
            FileInputStream fileInputStream=new FileInputStream(file);
            byte[] buf=new byte[4];
            fileInputStream.read(buf);
            System.out.println(new String(buf));
          } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
