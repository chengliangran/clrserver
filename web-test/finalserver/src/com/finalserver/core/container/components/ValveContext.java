package com.finalserver.core.container.components;

import com.finalserver.core.request.Request;
import com.finalserver.core.response.Response;

/**
 * Created by Administrator on 2017-03-10.
 */
public interface ValveContext {

    void involveNext(Request request, Response response);
}
