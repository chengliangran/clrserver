package com.finalserver.core;

import java.awt.*;

/**
 * Created by Administrator on 2017-03-06.
 */
public class BootStrap {
    public static void main(String[] args) {
        Connector connector=new Connector();
        connector.start();
        connector.connect();

    }
}
