package com.server20.core;

import java.io.InputStream;

/**
 * Created by Administrator on 2017-03-29.
 */
public class Request {
    Context context=null;
    Wrapper wrapper=null;



    private InputStream inputStream=null;
    Request(InputStream inputStream){
            this.inputStream=inputStream;
    }
}
