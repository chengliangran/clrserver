package com.finalserver.core;

import com.finalserver.core.container.*;


/**
 * Created by Administrator on 2017-03-06.
 */
public class BootStrap {
    public static void main(String[] args) {
        Connector connector=new Connector();
        Container container=new SimpleContext();
        connector.setContainer(container);
        connector.start();
        connector.connect();

    }
}
