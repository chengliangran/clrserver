package com.myserver.core;

import java.io.OutputStream;

/**
 * Created by Administrator on 2017-02-09.
 */
public class Response {
    OutputStream outputStream=null;
    Response(OutputStream outputStream){
        this.outputStream=outputStream;

    }

    public void sendResource() {
    }
}
