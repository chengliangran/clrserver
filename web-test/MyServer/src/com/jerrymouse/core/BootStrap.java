package com.jerrymouse.core;

import com.myserver.core.Connector;

/**
 * Created by Administrator on 2017-02-28.
 */
public class BootStrap {
    public static void main(String[] args) {
        String sm=System.getProperty("user.dir");
        System.out.println(sm);
         HttpConnector connector=new HttpConnector();
        connector.connect();
    }
}
