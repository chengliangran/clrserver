package com.myserver.core;

/**
 * Created by Administrator on 2017-02-08.
 */
public class BootStrap {
    public static void main(String[] args) {
        ServerHandler handler=new ServerHandler();
        handler.await();
     }
}
