package com.finalserver.core;

import com.finalserver.core.container.*;
import com.finalserver.core.container.components.BasicContextValve;
import com.finalserver.core.container.components.PipeLine;
import com.finalserver.core.container.components.SimplePipeLine;
import com.finalserver.core.container.components.TestValve;


/**
 * Created by Administrator on 2017-03-06.
 */
public class BootStrap {
    public static void main(String[] args) {
        Connector connector=new Connector();
        Container container=new SimpleContext();
        PipeLine pipeLine=new SimplePipeLine();
        pipeLine.setBasic(new BasicContextValve());
        pipeLine.addValve(new TestValve());
        container.setPipeLine(pipeLine);
        connector.setContainer(container);

        connector.start();
        connector.connect();

    }
}
