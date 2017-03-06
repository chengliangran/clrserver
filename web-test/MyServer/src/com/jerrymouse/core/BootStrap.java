package com.jerrymouse.core;

import com.jerrymouse.core.container.ContainerBase;
import com.myserver.core.Connector;

/**
 * Created by Administrator on 2017-02-28.
 */
public class BootStrap {
    public static void main(String[] args) {
        System.out.println("starting httpconnector");
         HttpConnector connector=new HttpConnector();
        connector.setContext(new ContainerBase());
        connector.start();
        connector.connect();
    }
}
