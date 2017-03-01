package com.jerrymouse.core;

import com.myserver.core.Connector;

import java.net.Socket;

/**
 * Created by Administrator on 2017-02-28.
 */
public class HttpProcessor implements Runnable{
    HttpConnector httpConnector;

    public HttpProcessor(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    Socket socket=null;
    public void setSocket(Socket socket){

    }
    public Socket getSocket(){
        return null;
    }


    @Override
    public void run() {

    }

    public void start(){

        Thread t=new Thread(this);
        t.start();
    }
}
