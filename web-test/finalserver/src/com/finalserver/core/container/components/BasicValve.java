package com.finalserver.core.container.components;

import com.finalserver.core.request.Request;
import com.finalserver.core.response.Response;

/**
 * Created by Administrator on 2017-03-09.
 */
public interface BasicValve extends Valve{
    public void invoke(Request request, Response response);
}
