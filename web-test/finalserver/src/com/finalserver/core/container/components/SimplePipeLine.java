package com.finalserver.core.container.components;

import com.finalserver.core.container.Container;
import com.finalserver.core.request.Request;
import com.finalserver.core.response.Response;

/**
 * Created by Administrator on 2017-03-08.
 */
public class SimplePipeLine implements PipeLine,LifecycleListener{
    Valve basic=null;
    Valve[] valves=new Valve[0];
    Container container=null;

    //监听器
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
    //操作基础阀门

    public Valve getBasic() {
        return basic;
    }

    public void setBasic(Valve basic) {
        this.basic = basic;
    }
    //操作阀门

    public Valve[] getValves() {
        return valves;
    }

    @Override
    public void addValve(Valve valve) {
        if (valve instanceof Contained){
            ((Contained) valve).setContainer(container);
        }
        System.out.println(valves.length);
        Valve[] results=new Valve[valves.length+1];
        System.arraycopy(valves,0,results,0,valves.length);
        results[results.length-1]=valve;
        valves=results;
    }

    //操作容器
    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }


    //主唤醒方法
    @Override
    public void invoke(Request request, Response response) {
        new SimpleContextValveContext().involveNext(request,response);
    }


    //内部类
    class SimpleContextValveContext implements ValveContext{
        int next=0;

        @Override
        public void involveNext(Request request, Response response) {
            int current=next;
            next++;
            if (current<valves.length){
                valves[current].invoke(request,response,this);
            }else if (current==valves.length||basic!=null){
                basic.invoke(request,response,this);
            }else{
             }

//            if (valves.length==0||basic!=null){
//                basic.invoke(request,response,this);
//                return;
//            }
//            if (current==valves.length) {
//                basic.invoke(request, response, this);
//            }else {
//                valves[current].invoke(request,response,this);
//            }
        }
    }
}
