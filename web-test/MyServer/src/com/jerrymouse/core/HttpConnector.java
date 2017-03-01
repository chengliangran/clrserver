package com.jerrymouse.core;

import com.newserver.core.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-02-28.
 */
public class HttpConnector {
    boolean stopped=false;



    //dealing with the processors
    int maxProcessors=20;
    int minProcessor=5;
    List<HttpProcessor> processors=new ArrayList<>();
    int curProcessors=processors.size();
    public void recycle(HttpProcessor processor){
        processors.add(processor);
    }

    public HttpProcessor getProcessor(){
        if (curProcessors>0){
            return processors.remove(curProcessors-1);
        }else {
            return null;
        }
    }

    public void start(){
        while(curProcessors<minProcessor){
            HttpProcessor processor=new HttpProcessor(this);
            processors.add(processor);

        }
    }
    //produce the socket and pass it to the processor to deal with
    public void connect(){
        try {
            ServerSocket serverSocket=new ServerSocket(8080);
            System.out.println("starting receiving socket");
            while (!stopped){
                Socket socket=serverSocket.accept();
                System.out.println("get socket");
                //operating and testing reading sockeet
//                InputStream is=socket.getInputStream();
//                int i=is.read();
//                System.out.println((char)i);
//                StringBuffer sb=new StringBuffer();
//                while (i!=-1){
//                    sb.append((char)i);
//                    i=is.read();
//                    if (i==10){
//                        System.out.println(sb.toString());
//                        System.out.println("打印获得结果是"+"------------"+i);
//                    }
//                }
//                System.out.println("循环结束");
//                System.out.println(sb.toString());

                //creating processor
                HttpProcessor processor=getProcessor();
                if (processor==null){
                    continue;
                }else {
                    processor.start();
                }
                processor.setSocket(socket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
