package com.serever20.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Administrator on 2017-03-29.
 */
public class StringUtil {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s= scanner.nextLine();
        System.out.println(s+"总内容");
            String[] lines=s.split("\r\n");
            System.out.println("request的数量"+lines.length);
            for (String line:lines){
                System.out.println(line);

            }

    }
}
