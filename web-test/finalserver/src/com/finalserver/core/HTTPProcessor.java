package com.finalserver.core;

import java.io.*;
import java.net.Socket;

/**
 * Created by Administrator on 2017-03-06.
 */
public class HTTPProcessor implements Runnable{
    private static int lastProcessorVer=0;
    private int curProcessorVer;
    public HTTPProcessor() {
        lastProcessorVer++;
        curProcessorVer=lastProcessorVer;
    }
    public int getProcessorVersion(){
      return curProcessorVer;
    }
    //    operating connector
    private Connector connector;

    public void setConnector(Connector connector) {
        this.connector=connector;
    }
    private Connector getConnector(){
        return connector;
    }
//    operating socket
    boolean available=false;
    private Socket socket;

    public Socket getSocket() {
        while (socket==null){
           synchronized (this) {
               try {
                   wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }

        }
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
        synchronized (this){
        notifyAll();
            System.out.println("already set the socket");
        }
    }

    //    starting multithread method
    public void start() {
        Thread t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        test();
    }

    private void test() {
        System.out.println(this.getProcessorVersion()+"starting processing socket");
        System.out.println(Constants.WEB_ROOT);
        socket=getSocket();
        OutputStream outputStream=null;
        try {
            outputStream=socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(getProcessorVersion()+"sleeping");
        }
        try {
            System.out.println("start writing");
//            String msg="HTTP/1.1 404 File Not Found\r\n" +
//                    "Content-Type: text/html\r\n" +
//                    "Content-Length: 23\r\n" +
//                    "\r\n" +
//                    "<h1>ceshijieguo</h1>";
//            outputStream.write(msg.getBytes());
            //read
            InputStream is=socket.getInputStream();
            int c=is.read();
            StringBuffer sb=new StringBuffer();
            for (int s=0;s<300;s++){
                sb.append((char)c);
                c=is.read();
            }
            System.out.println(sb.toString());

            //write
            File file=new File(Constants.WEB_ROOT,"a.txt");
            InputStream inputStream=new FileInputStream(file);
            byte[] buf=new byte[1024];
            int length= inputStream.read(buf);
            while (length!=-1){
                outputStream.write(buf,0,buf.length);
                length= inputStream.read(buf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
