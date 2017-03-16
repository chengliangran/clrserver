package com.finalserver.core.request;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.SplittableRandom;

/**
 * Created by Administrator on 2017-03-09.
 */
public class Request {
    InputStream inputStream=null;
    //    字段
    String method=null;
    String uri=null;
    String httpVer=null;
    Map<String,String> headers=new HashMap<>();

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHttpVer() {
        return httpVer;
    }

    public void setHttpVer(String httpVer) {
        this.httpVer = httpVer;
    }

    public Request(InputStream inputStream) {
        this.inputStream=inputStream;
    }
}
