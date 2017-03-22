package com.finalserver.core.container;

import com.finalserver.core.container.components.*;
import com.finalserver.core.request.Request;
import com.finalserver.core.response.Response;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-03-06.
 */
public class SimpleContext implements Container {
    private PipeLine pipeLine = null;
    private Loader loader = null;
    private Manager manager = null;
    private Realm realm = null;
    private ArrayList<Mapper> mappers=null;
//    加载mapper并且加入到mapper数组总
    public void addMapper(Mapper mapper){
        mappers.add(mapper);

    }
    public void addDefaultMapper(String mapperClass){
        if (!(mappers.size()>1)){
            return;
        }
        try {
            Class clazz=Class.forName(mapperClass);
            Mapper mapper=(Mapper)(clazz.newInstance());
            addMapper(mapper);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public com.finalserver.core.container.components.Logger getLogger() {
        return null;
    }

    @Override
    public void setLogger(com.finalserver.core.container.components.Logger logger) {

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
        this.pipeLine = pipeLine;
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


    //主方法唤醒容器
    @Override
    public void invoke(Request request, Response response) {
        System.out.println("开始使用容器");
        pipeLine.invoke(request, response);
    }


}
