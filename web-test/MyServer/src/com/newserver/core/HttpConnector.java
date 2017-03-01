package com.newserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-02-24.
 */
public class HttpConnector {
    boolean stoped=false;
    int port=8080;
    List<HttpProcessor> processors=new ArrayList<HttpProcessor>();
    int maxProcessors=20;
    int minProcessors=5;
    public void start(){
        while (processors.size()<minProcessors){
            processors.add(new HttpProcessor());
        }
    }

     public void await() {
         try {
             ServerSocket ss=new ServerSocket();
             while (!stoped){
                 Socket socket=ss.accept();
                 HttpProcessor processor=newProcessor();
                 if (processor!=null){
                     processor.start();
                 }

             }
         } catch (Exception e) {
             e.printStackTrace();
             System.exit(1);
         }



     }

    private HttpProcessor newProcessor() {
        if(processors.size()>0){
            return processors.remove(processors.size()-1);
        }else {
            return null;
        }
     }


}
