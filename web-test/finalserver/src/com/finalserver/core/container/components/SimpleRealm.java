package com.finalserver.core.container.components;

/**
 * Created by Administrator on 2017-03-20.
 */
public class SimpleRealm {
    public boolean hasRole(){

        return false;
    }
    public Principal authentic(){
        return null;
    }
}
