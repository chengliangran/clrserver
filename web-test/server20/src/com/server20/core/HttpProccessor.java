package com.server20.core;

/**
 * Created by Administrator on 2017-03-29.
 */
public class HttpProccessor implements Runnable {




    public void start(){
        Thread thread=new Thread(this);
        thread.start();
    }
    @Override
    public void run() {
        process();
    }
    public void process(){

    }
}
