package com.finalserver.core;

import com.finalserver.core.container.Container;
import com.finalserver.core.request.Request;
import com.finalserver.core.response.Response;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017-03-06.
 */
public class HTTPProcessor implements Runnable{

//    初始化处理类并且赋予编号
    private static int lastProcessorVer=0;
    private int curProcessorVer;
    public HTTPProcessor() {
        lastProcessorVer++;
        curProcessorVer=lastProcessorVer;
    }
    public int getProcessorVersion(){
      return curProcessorVer;
    }

//    管理连接器
    private Connector connector;

    public void setConnector(Connector connector) {

        this.connector=connector;
    }
    private Connector getConnector(){

        return connector;
    }

//  管理socket
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

//    开启多线程
    public void start() {
        Thread t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        test();
    }

    private void test() {
        try {
            socket=getSocket();
            InputStream inputStream=socket.getInputStream();
            OutputStream outputStream=socket.getOutputStream();
            byte[] bytes=new byte[1024];
            int i=inputStream.read(bytes);
            StringBuffer sb=new StringBuffer();
            while (i!=-1){
                sb.append(new String(bytes,0,bytes.length));
                i=inputStream.read(bytes);

            }
            String reqString=sb.toString();
            System.out.println(reqString);
            String[] strings=reqString.split("\r\n");
            String requestLine=strings[0];
            ArrayList headersStr=new ArrayList();
            for (int s=1;s<strings.length-1;s++){
                headersStr.add(strings[s]);
            };
            int space=reqString.indexOf(' ');
            int space2=reqString.indexOf(' ',space+1);
            System.out.println(requestLine);
            System.out.println("第一个空壳"+space);
            System.out.println("第二个空壳"+space2);
            String method=requestLine.substring(0,space);
            String uri=requestLine.substring(space+1,space2);
            String protocal=requestLine.substring(space2);
            System.out.println(method+"---"+uri+"---"+protocal);
            System.out.println(requestLine.substring(0,requestLine.indexOf(' ')));

            Request request=new Request(inputStream);
            Response response=new Response(outputStream,request);
            Container container=connector.getContainer();
            System.out.println("开始唤醒容器");
            container.invoke(request,response);
            socket.close();
         } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
