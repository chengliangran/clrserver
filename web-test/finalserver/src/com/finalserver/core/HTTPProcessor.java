package com.finalserver.core;

import com.finalserver.core.container.Container;
import com.finalserver.core.request.Request;
import com.finalserver.core.response.Response;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

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
//            取出http协议
            byte[] bytes=new byte[1024];
            int i=inputStream.read(bytes);
            StringBuffer sb=new StringBuffer();
            while (i!=-1){
                sb.append(new String(bytes,0,bytes.length));
                i=inputStream.read(bytes);
            }
            String reqString=sb.toString();
//            拆解request
            Request request=new Request(inputStream);
            Response response=new Response(outputStream,request);

            String[] strings=reqString.split("\r\n");

            String requestLine=strings[0];
            System.out.println(requestLine);
            String[] reqLineConponents=requestLine.split(" ");
            System.out.println(reqLineConponents.length);
            for (String reqLineConponent : reqLineConponents) {
                System.out.println(reqLineConponent);
            }
            System.out.println(reqLineConponents[0]);
            request.setMethod(reqLineConponents[0]);
            request.setUri(reqLineConponents[1]);
            request.setHttpVer(reqLineConponents[2]);
            ArrayList<String> headersStr=new ArrayList();
             String content=null;
            System.out.println(request.getMethod()+"--"+request.getUri()+"--"+request.getHttpVer());
            for (int s=1;s<strings.length-1;s++){
                if (request.getMethod().equals("POST")&&s==strings.length-1)
                    content=strings[s];
                headersStr.add(strings[s]);
            };

            HashMap<String,String> headers=new HashMap<>();
            for (String s : headersStr) {
                String[] keyValue=new String[2];
                if (s.indexOf(":")!=-1){
                    System.out.println(s);
                    System.out.println(s.indexOf(":"));
                    keyValue[0]=s.substring(0,s.indexOf(":"));

                    keyValue[1]=s.substring(s.indexOf(":")+1);
                    System.out.println(s.substring(s.indexOf(":")+1));
                    headers.put(keyValue[0],keyValue[1]);
                }
            }

            request.setHeaders(headers);
            for (String key:headers.keySet()){
                System.out.println(key+"-----"+headers.get(key));
            }
//            int space=reqString.indexOf(' ');
//            int space2=reqString.indexOf(' ',space+1);
//            String method=requestLine.substring(0,space);
//            String uri=requestLine.substring(space+1,space2);
//            String protocal=requestLine.substring(space2);

//            唤醒容器
            Container container=connector.getContainer();
            container.invoke(request,response);
            socket.close();
         } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
