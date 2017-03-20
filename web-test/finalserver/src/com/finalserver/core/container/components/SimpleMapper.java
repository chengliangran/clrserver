package com.finalserver.core.container.components;

import com.finalserver.core.container.Container;

/**
 * Created by Administrator on 2017-03-08.
 */
public class SimpleMapper {

    /**
     * Created by Administrator on 2017-03-17.
     */
    public static interface Logger {
        public int FATAL=0;
        public int ERROR=1;
        public int WARNING=2;
        public int INFORMATION=3;
        public int DEBUG=4;
        public Container getContainer();
        public void setContainer();
        public String getInfo();
        public int getVerbosity();
        public int setVerbosity();
        public void log(String msg);
        public void log(Exception e, String msg);
        public void log(String msg, Throwable throwable);
        public void log(String msg, int verbosity);
        public void log(String msg, Throwable throwable, int verbosity);


    }
}
