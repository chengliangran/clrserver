package com.jerrymouse.core.container;

import com.jerrymouse.core.container.component.*;
import com.jerrymouse.core.container.component.pipeline.PipeLine;
import com.jerrymouse.tools.Request;
import com.myserver.core.Response;

/**
 * Created by Administrator on 2017-02-28.
 */
public interface Container {

    public Loader getLoader();
    public void setLoader(Loader loader);

    public void setLogger(Logger logger);
    public Logger getLogger();

    public void setManager(Manager manager);
    public Manager getManget();

    public void setName(String name);
    public String getName();

    public void setRealm(Realm realm);
    public Realm getRealm();

    public void addChild(Container child);
    public void addMapper(Mapper mapper);
    public Container findChild(String name);
    public Container[] findChilden();

    public void invoke(Request request, Response response);





}
