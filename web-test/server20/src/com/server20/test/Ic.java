package com.server20.test;

/**
 * Created by Administrator on 2017-04-10.
 */
public interface Ic {

}


//杂项

interface Contained{
    void setContainer(Container container);
    Container getContainer();
}

interface Lifecycle{
    public static final String START_EVENT = "start";
    public static final String BEFORE_START_EVENT = "before_start";
    public static final String AFTER_START_EVENT = "after_start";
    public static final String STOP_EVENT = "stop";
    public static final String BEFORE_STOP_EVENT = "before_stop";
    public static final String AFTER_STOP_EVENT = "after_stop";
    void start();
    void stop();
    void addLifecycleListener(LifecycleListener listener);
    LifecycleListener[] findListeners();
    LifecycleListener removeListener(LifecycleListener listener);

}

interface LifecycleSupport{
    void fireLifecycleEvent(String type,Object data);

}
interface LifecycleEvent{

}
interface LifecycleListener{
}
//容器

interface Container{
    void invoke(HttpRequest request,HttpResponse response);
    void addChild(Container container);
    Container removeChild(Container container);
    Wrapper findChild(String name);
    Container[] findChildren();

}
interface Loader{

}
interface Logger{

}
interface Context{
    Container map(HttpRequest request);
}
interface Mapper{
    Container getContainer();
    void setContainer(Container container);
    Wrapper mapper(HttpRequest request);
    void setProtocal(String protocal);
    String getProtocal();


}

//管道流系列
interface PipeLine{
    BasicValve getBasicValve();
    void setBasicValve(BasicValve basicValve);
    void addValve(Valve valve);
    Valve removeValve(Valve valve);
    Valve[] getValves();
    public void invoke(HttpRequest request,HttpResponse response);


}

 interface ValveContext{
    void invokeNext(HttpRequest request,HttpResponse response);

}
interface Valve {
    String getInfo();
    void invoke(HttpRequest request,HttpResponse response,ValveContext valveContext);
}
interface BasicValve{
    void invoke(HttpRequest request,HttpResponse response);
}
