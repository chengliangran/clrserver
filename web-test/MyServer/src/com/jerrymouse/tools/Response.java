package com.jerrymouse.tools;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017-02-28.
 */
public class Response {
    //configure response
    OutputStream outputStream;

    Request request;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    //parse the needed parameters

}
