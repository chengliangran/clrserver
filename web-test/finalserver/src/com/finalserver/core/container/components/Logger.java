package com.finalserver.core.container.components;

import com.finalserver.core.container.Container;

/**
 * Created by Administrator on 2017-03-07.
 */
public interface Logger {
    Container getContainer();

    void setContainer();

    String getInfo();

    int getVerbosity();

    int setVerbosity();

    void log(String msg);

    void log(Exception e, String msg);

    void log(String msg, Throwable throwable);

    void log(String msg, int verbosity);

    void log(String msg, Throwable throwable, int verbosity);
}
