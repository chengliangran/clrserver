
package com.jerrymouse.tools;

import com.jerrymouse.tools.reqcomponents.Header;
import com.jerrymouse.tools.reqcomponents.RequestLine;

import java.io.InputStream;

/**
 * Created by Administrator on 2017-03-02.
 */
public class SocketInputStream {
    InputStream inputStream=null;

    public SocketInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }
    public void readRequestLine(RequestLine requestLine){

    }

    public void readHeader(Header header){

    }
}
