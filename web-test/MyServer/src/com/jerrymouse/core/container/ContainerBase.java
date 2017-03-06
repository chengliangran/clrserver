package com.jerrymouse.core.container;

import com.jerrymouse.core.container.component.*;
import com.jerrymouse.core.container.component.pipeline.PipeLine;
import com.jerrymouse.core.container.component.pipeline.SimpleContextPipeLine;
import com.jerrymouse.tools.Request;
import com.myserver.core.Response;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017-03-01.
 */
public class ContainerBase implements Runnable,Container {
    protected LifeCycleSupport lifeCycleSupport=new LifeCycleSupport(this);
    protected ArrayList listeners=new ArrayList();
    protected Loader loader=null;
    protected Logger logger=null;
    protected Manager manager=null;
    protected Realm realm=null;

    protected PipeLine pipeLine=new SimpleContextPipeLine();

    protected Mapper mapper=null;
    protected HashMap mappers=new HashMap();

    protected HashMap children=new HashMap();








    @Override
    public Loader getLoader() {
        return null;
    }

    @Override
    public void setLoader(Loader loader) {

    }

    @Override
    public void setLogger(Logger logger) {

    }

    @Override
    public Logger getLogger() {
        return null;
    }

    @Override
    public void setManager(Manager manager) {

    }

    @Override
    public Manager getManget() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setRealm(Realm realm) {

    }

    @Override
    public Realm getRealm() {
        return null;
    }

    @Override
    public void addChild(Container child) {

    }

    @Override
    public void addMapper(Mapper mapper) {

    }

    @Override
    public Container findChild(String name) {
        return null;
    }

    @Override
    public Container[] findChilden() {
        return new Container[0];
    }

    @Override
    public void invoke(Request request, com.jerrymouse.tools.Response response) {

    }

    public void invoke(Request request, Response response) {
        System.out.println("处理");
    }

    @Override
    public void run() {

    }
}
