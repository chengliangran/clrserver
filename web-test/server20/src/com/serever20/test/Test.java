package com.serever20.test;

import java.io.*;

import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

/**
 * Created by Administrator on 2017-03-27.
 */
public class Test {
    public static void main(String[] args) {
        Random random=new Random();
        for (int i=0;i<100;i++){
            System.out.println(random.nextInt()*1000);
        }
    }
}
