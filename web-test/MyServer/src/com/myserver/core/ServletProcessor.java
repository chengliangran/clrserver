package com.myserver.core;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

/**
 * Created by Administrator on 2017-02-10.
 */
public class ServletProcessor {
    public void init(){
        System.out.println("启动");
    }
    public void process(Request request, Response response) {
    }
    public void destroy(){
        System.out.println("销毁");
    }
 }
