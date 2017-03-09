package com.finalserver.core.response;

import com.finalserver.core.request.Request;

import java.io.OutputStream;

/**
 * Created by Administrator on 2017-03-09.
 */
public class Response {
    OutputStream outputStream=null;
    Request request=null;

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public Request getRequest() {
        return request;
    }

    public Response(OutputStream outputStream,Request request) {
        this.outputStream=outputStream;
    }
}
