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
    int nInstances=0;
    int maxProcessors=30;
    int minProcessors=10;



    public void start(){
        for (int i=0;i<minProcessors;i++){
            httpProccessors.add(new HttpProccessor());
            nInstances++;
        }
    }
    public HttpProccessor getProcessor(){
        if (httpProccessors.size()>0){
            System.out.println("current processors="+nInstances+"processors");
            return httpProccessors.remove(httpProccessors.size()-1);
        }else if (nInstances<maxProcessors){
            nInstances++;
            System.out.println("current processors="+nInstances+"processors");
            return new HttpProccessor();
        }else {
            System.out.println("current processors="+nInstances+"processors");
            return null;
        }
    }
    public static void main(String[] args) {
        new HttpConnector().connect();
    }
    public void connect(){
        try {
            ServerSocket serverSocket=new ServerSocket(8080);
            Socket socket=null;
            while (started){
                socket= serverSocket.accept();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
