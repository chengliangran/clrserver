package com.finalserver.core.container.components;

import com.finalserver.core.request.Request;
import com.finalserver.core.response.Response;

/**
 * Created by Administrator on 2017-03-16.
 */
public class BasicContextValve implements BasicValve {

    @Override
    public void invoke(Request request, Response response, ValveContext valveContext) {
        System.out.println("开始调用mapper");
    }

    @Override
    public void invoke(Request request, Response response) {

    }
}
