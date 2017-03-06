package com.jerrymouse.core;

import com.jerrymouse.core.container.Container;
import com.jerrymouse.core.container.SimpleContext;
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
    int record=0;

    boolean stopped=false;
    //set container
    Container context=null;

    public Container getContext() {
        return context;
    }

    public void setContext(Container context) {
        this.context = context;
    }

    //dealing with the core
    int maxProcessors=20;
    int minProcessor=5;
    List<HttpProcessor> processors=new ArrayList<>();
    int curProcessors=minProcessor;
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
        while(processors.size()<minProcessor){
            System.out.println("starting creating core");
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
//                record++;
//                System.out.println("第"+record+"轮"+"循环开始");
                System.out.println("启动");
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
//                System.out.println(sb.toString());

                //creating processor
                HttpProcessor processor=getProcessor();
                System.out.println("拿到processor");
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
