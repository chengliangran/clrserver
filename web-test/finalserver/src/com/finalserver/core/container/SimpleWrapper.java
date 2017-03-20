package com.finalserver.core.container;

import com.finalserver.core.container.components.Loader;
import com.finalserver.core.container.components.Logger;
import com.finalserver.core.container.components.Mapper;
import com.finalserver.core.container.components.PipeLine;
import com.finalserver.core.request.Request;
import com.finalserver.core.response.Response;

/**
 * Created by Administrator on 2017-03-16.
 */
public class SimpleWrapper implements Container {
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

    }

    @Override
    public void setChild(Container container) {

    }

    @Override
    public Container getChild(String container) {
        return null;
    }

    @Override
    public Container[] getChildren() {
        return new Container[0];
    }
    private Servlet loadServlet(){
        return null;
    }
//    由valve调用，实现servlet获取
    public Servlet allocate(){
        return null;
    }

    @Override
    public void invoke(Request request, Response response) {
        System.out.println("开始执行程序");

    }
}
