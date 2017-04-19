package com.clrserver.container;

import com.clrserver.container.components.*;
import com.clrserver.container.packagings.DirContext;
import com.clrserver.container.packagings.HttpRequest;
import com.clrserver.container.packagings.HttpResponse;

/**
 * Created by Administrator on 2017-04-19.
 */
public interface Container {

    void addContainerListener(Listener listener);
    Listener[] findContainerListeners();

    void setLoader(Loader loader);
    Loader getLoader();

    void setResouce(DirContext dirContext);
    DirContext getResource();

    void setLogger(Logger logger);
    Logger getLogger();

    void setManager(Manager manager);
    Manager getManager();

    void setCluster(Cluster cluster);
    Cluster getCluster();

    void setRealm(Realm realm);
    Realm getRealm();

    Pipeline getPipeline();

    Container findChild(String name);
    void addChild(Container child);

    void backgroundProcess();

    void invoke(HttpRequest request, HttpResponse response);
}
