package com.clrserver.container.lifecycle;

import com.clrserver.container.packagings.LifecycleEvent;

import javax.swing.plaf.ViewportUI;

/**
 * Created by Administrator on 2017-04-19.
 */
public final class LifecycleSupport {
    private LifecycleListener[] listeners=new LifecycleListener[0];


    public LifecycleSupport() {

    }

    public void addListener(LifecycleListener listener){
        LifecycleListener[] newListeners=new LifecycleListener[listeners.length+1];
        for (int i=0;i<listeners.length;i++){
            newListeners[i]=listeners[i];

        }
        newListeners[listeners.length-1]=listener;
        listeners=newListeners;
    }
    public LifecycleListener[] findListeners(String type,Object data){

        return null;
    }

    public void fireLifecycleEvent(LifecycleEvent event){

    }

}
