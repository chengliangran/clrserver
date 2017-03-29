package com.server20.core;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017-03-29.
 */
public class Response {
    private OutputStream outputStream=null;
    private Request request=null;
    //设置字段
    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    Response(InputStream inputStream){
        this.outputStream=outputStream;
    }
}
