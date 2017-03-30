package com.serever20.test;

import com.sun.org.apache.bcel.internal.util.ClassLoader;

import java.io.*;

import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.NotYetBoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Properties;
import java.util.Random;

/**
 * Created by Administrator on 2017-03-27.
 */
public class Test {
    public static void main(String[] args) {
        try {
            Properties properties=new Properties();
             Class clazz=Class.forName("java.util.Random");
            Random o= (Random) clazz.newInstance();
            System.out.println(o);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        ClassLoader classLoader=new ClassLoader();
        ClassLoader classLoader1=new ClassLoader();

    }
}
