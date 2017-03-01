package com.newserver.core;

import java.net.Socket;

/**
 * Created by Administrator on 2017-02-24.
 */
public class HttpProcessor implements Runnable{
    @Override
    public void run() {
        Socket socket= await();


    }

    //    checking whether having a socket or not
    boolean available=false;

    //    get socket
    Socket socket=null;

    private Socket await() {
        while (!available){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return socket;
    }

    //    used to create a new thread
    public void start() {
        Thread t=new Thread(this);
        t.start();

    }
    public void process(Socket socket){
        this.socket=socket;
    }
}
