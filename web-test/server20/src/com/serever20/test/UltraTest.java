package com.serever20.test;

import com.server20.core.HttpProccessor;
import com.server20.core.Request;
import com.server20.core.Response;
import com.sun.deploy.trace.SocketTraceListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017-04-07.
 */
//***根类
public class UltraTest {
    public static void main(String[] args) {
        Connector connector=new Connector();
        connector.setContainer(new Context());
        connector.connect();

    }
}
//***连接器
class Connector{
//    container
    Context container=null;

    public Context getContainer() {
        return container;
    }

    public void setContainer(Context container) {
        this.container = container;
    }

    //    processors
    ArrayList<Processor> processors=new ArrayList<>();
    int minProcessors=10;
    int nInstances=0;
    int maxProcessors=30;
//    dealwith processors
    public void initProcessors(){
     while (nInstances<minProcessors){
         processors.add(new Processor(this));
         nInstances++;
     }
    }
    public Processor getProcessor(){
        if (processors.size()>0){
            return processors.remove(processors.size()-1);
        }else if (nInstances<maxProcessors){
            processors.add(new Processor(this));
            nInstances++;
            return processors.remove(processors.size()-1);
        }else {
            return null;
        }
    }

    boolean shutdown=false;
    public void connect(){
        Socket socket=null;
        ServerSocket ss= null;
        try {
            ss = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!shutdown){
            try {
                socket=ss.accept();
                Processor processor=getProcessor();
                if (processor!=null){
                processor.start();
                    processor.asign(socket);

                }else{
                }
             } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


//***处理器
class Processor implements Runnable{
    private Object threadSync = new Object();
//    Connector
    Connector connector=null;
    Processor(Connector connector){
        this.connector=connector;
    }
    //    Socket
    boolean avaliable=false;
    Socket socket=null;

    synchronized void asign(Socket socket){
        while (avaliable){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.socket=socket;
        avaliable=true;
        notifyAll();

    }

    synchronized Socket getSocket(){
        while (!avaliable){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        avaliable=false;
        notifyAll();
        return socket;

     }

    public void process(){
        Socket socket=getSocket();
        InputStream inputStream=null;
        OutputStream outputStream=null;
        try {
            inputStream=socket.getInputStream();
            outputStream=socket.getOutputStream();
            HttpRequest request=new HttpRequest(inputStream);
            HttpResponse response=new HttpResponse(outputStream,request);
            request.parse();
            connector.getContainer().invoke();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        process();
    }
    public void start(){
        Thread thread=new Thread(this);
        thread.start();
    }
}

class HttpRequest{
    String requestString=null;
    InputStream inputStream=null;
    String uri=null;
    public HttpRequest(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse() {
        byte[] buf=new byte[2048];
        StringBuffer sb=new StringBuffer();
        try {
            int i=inputStream.read(buf);
            while (i!=-1){
                sb=sb.append(new String(buf));
                i=inputStream.read(buf);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        requestString=sb.toString();
        String[] requestRows=requestString.split("\r\n");
        String reqLine=  requestRows[0];
        int spac1Index=reqLine.indexOf(" ");
        int spac2Index=reqLine.indexOf(" ",spac1Index+1);
        uri=reqLine.substring(spac1Index+1,spac2Index);
        System.out.println(uri);

    }
}
class HttpResponse{
    OutputStream outputStream=null;
    HttpRequest request=null;

    public HttpResponse(OutputStream outputStream, HttpRequest request) {
        this.outputStream = outputStream;
        this.request = request;
    }
}
//***容器
class Context{
    public void invoke(){
        System.out.println("starting proccessing socket");
    }
}