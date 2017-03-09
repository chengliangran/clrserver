package com.finalserver.core.request;

import java.io.InputStream;

/**
 * Created by Administrator on 2017-03-09.
 */
public class Request {
    InputStream inputStream=null;
    public Request(InputStream inputStream) {
        this.inputStream=inputStream;
    }
}
