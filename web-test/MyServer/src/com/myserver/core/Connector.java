package com.myserver.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017-02-13.
 */
public class Connector {
    public static final String WEB_ROOT=System.getProperty("user.dir")+ File.separator+"webroot";
    final String SHUTDOWN="shutdown";
    int port=8080;
    public void await() {

        ServerSocket server=null;
        try {
            server=new ServerSocket(port,1, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Socket socket=null;
        InputStream inputStream=null;
        OutputStream outputStream=null;
        boolean shutdown=false;
        while (!shutdown){
            try {
                socket=server.accept();
                new ServletHandler1().process(socket);
             } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
