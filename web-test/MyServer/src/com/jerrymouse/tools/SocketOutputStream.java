package com.jerrymouse.tools;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017-03-02.
 */
public class SocketOutputStream {
    OutputStream outputStream=null;

    public SocketOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
