package com.server20.core;

import sun.java2d.pipe.SolidTextRenderer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017-03-29.
 */
public class HttpConnector {

    private boolean started=true;
    private ArrayList<HttpProccessor> httpProccessors=new ArrayList<HttpProccessor>();
    int maxProcessors=30;
    int minProcessors=10;

    public void start(){

    }
    public static void main(String[] args) {
        System.out.println("开始");
    }
    public void connect(){
        try {
            ServerSocket serverSocket=new ServerSocket(8080);
            Socket socket=null;
            while (started){
                serverSocket.accept();
                InputStream inputStream=socket.getInputStream();
                OutputStream outputStream= socket.getOutputStream();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
