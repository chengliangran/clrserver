package com.finalserver.core.container.components;

import com.finalserver.core.request.Request;
import com.finalserver.core.response.Response;

/**
 * Created by Administrator on 2017-03-10.
 */
public class TestValve implements Valve{

    @Override
    public void invoke(Request request, Response response, ValveContext valveContext) {
        valveContext.involveNext(request,response);
        System.out.println("this is a test valve just used to see whether it's work or not");
    }
}
