package com.jerrymouse.core;

import com.jerrymouse.core.container.Container;
import com.jerrymouse.tools.Request;
import com.jerrymouse.tools.Response;
import com.jerrymouse.tools.SocketInputStream;
import com.jerrymouse.tools.SocketOutputStream;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Administrator on 2017-02-28.
 */
public class HttpProcessor implements Runnable{
    public static int version=0;
    public int curversion;
    HttpConnector httpConnector;
    //获取container
    Container container=null;
    HttpProcessor(){
        version++;
        curversion=version;
    }
    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return container;
    }

    public HttpProcessor(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }
    //the statue of the socket
    boolean avilable=false;
    Socket socket=null;
    public void setSocket(Socket socket){
        this.socket=socket;
        avilable=true;
        notifyAll();
    }
    public Socket getSocket(){


        while (!avilable){
            try {
              wait();
            }catch (Exception e){

            }
        }
        return socket;
    }


    @Override
    public void run() {
        System.out.println("当前第"+curversion+"版的processor 正在工作");
        Socket socket= getSocket();
        try {
            SocketInputStream socketInputStream=new SocketInputStream(socket.getInputStream());
            SocketOutputStream socketOutputStream=new SocketOutputStream(socket.getOutputStream());
            Request request=new Request(socket.getInputStream());
            Response response=new Response(socket.getOutputStream());
            httpConnector.getContext().invoke(request,response);
            httpConnector.recycle(this);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void start(){

        Thread t=new Thread(this);
        t.start();
    }

    private void process(Socket socket){

    }
}
