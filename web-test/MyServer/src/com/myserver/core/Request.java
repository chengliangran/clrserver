package com.myserver.core;

import java.io.InputStream;

/**
 * Created by Administrator on 2017-02-09.
 */
public class Request {
    String uri=null;
    InputStream inputStream=null;

    Request(InputStream inputStream){
        this.inputStream=inputStream;
    }

    public void parse() {

    }

    public String getUri() {
        return uri;
    }
}
