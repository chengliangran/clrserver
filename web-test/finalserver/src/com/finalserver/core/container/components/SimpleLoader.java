package com.finalserver.core.container.components;

/**
 * Created by Administrator on 2017-03-07.
 */
public class SimpleLoader implements Loader,Runnable {

    @Override
    public void run() {
        checkTimestape();
    }

    private void checkTimestape() {
    }

    public void start(){
        Thread thread=new Thread(this);
        thread.start();
    }
}
