package com.finalserver.core.container.components;

import com.finalserver.core.container.Container;

/**
 * Created by Administrator on 2017-03-22.
 */
public class SimpleContextMapper implements Mapper {
    String protocal;

    public String getProtocal() {
        return protocal;
    }

    @Override
    public void setProtocal(String protocal) {
        this.protocal=protocal;
    }

    @Override
    public void setContainer(Container container) {

    }
}
