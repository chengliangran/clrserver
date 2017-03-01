package com.myserver.servlet;

import com.myserver.core.Request;
import com.myserver.core.Response;

/**
 * Created by Administrator on 2017-02-13.
 */
public interface Servlet {
    public abstract void init();
    public abstract void service(Request request, Response response);
    public abstract void destroy();

}
