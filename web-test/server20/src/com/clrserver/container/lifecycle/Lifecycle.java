package com.clrserver.container.lifecycle;

import jdk.nashorn.internal.objects.NativeUint8Array;

/**
 * Created by Administrator on 2017-04-19.
 */
public interface Lifecycle {
    public static String BEFORE_START_EVENT=null;
    public static String AFTER_START_EVENT= null;

    void start();
    void stop();

}
