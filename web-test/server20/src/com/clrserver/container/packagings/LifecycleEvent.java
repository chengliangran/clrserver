package com.clrserver.container.packagings;

import com.clrserver.container.lifecycle.Lifecycle;

/**
 * Created by Administrator on 2017-04-19.
 */
public class LifecycleEvent extends EventObject {
    String type=null;
    Object data=null;
    Lifecycle lifecycle=null;

    public LifecycleEvent(String type, Object data, Lifecycle lifecycle) {
        this.type = type;
        this.data = data;
        this.lifecycle = lifecycle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Lifecycle getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }
}
