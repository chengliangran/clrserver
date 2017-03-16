package com.finalserver.core.container.components;

import com.finalserver.core.container.Container;
import com.finalserver.core.request.Request;
import com.finalserver.core.response.Response;

/**
 * Created by Administrator on 2017-03-08.
 */
public interface PipeLine {
    public void addValve(Valve valve);
    public void invoke(Request request, Response response);
    public void setBasic(BasicValve basic);
}
