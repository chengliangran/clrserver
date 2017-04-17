package com.server20.test;

import com.server20.core.HttpConnector;
import com.sun.org.apache.bcel.internal.util.ClassLoader;
import org.apache.commons.digester3.Digester;
import org.apache.commons.digester3.Rule;

import java.io.*;

import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.NotYetBoundException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by Administrator on 2017-03-27.
 */
public class Test {
    public static void main(String[] args) {
        File file=new File("d:/csdn.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("user.dir"));
        Digester digester=new Digester();
        Rule rule=new Rule() {
        };
    }
}
