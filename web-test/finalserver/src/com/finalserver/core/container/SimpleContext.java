package com.finalserver.core.container;

import com.finalserver.core.container.components.*;
import com.finalserver.core.request.Request;
import com.finalserver.core.response.Response;

/**
 * Created by Administrator on 2017-03-06.
 */
public class SimpleContext implements Container{
    private PipeLine pipeLine=null;



    @Override
    public Logger getLogger() {
        return null;
    }

    @Override
    public void setLogger(Logger logger) {

    }

    @Override
    public Loader getLoader() {
        return null;
    }

    @Override
    public void setLoader(Loader loader) {

    }

    @Override
    public Mapper getMapper() {
        return null;
    }

    @Override
    public void setMapper(Mapper mapper) {

    }

    @Override
    public PipeLine getPipeLine() {
        return null;
    }

    @Override
    public void setPipeLine(PipeLine pipeLine) {
        this.pipeLine=pipeLine;
    }
    //主方法唤醒容器
    @Override
    public void invoke(Request request, Response response) {
        System.out.println("helloword");
    }
}
