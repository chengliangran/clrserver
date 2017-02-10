package com.myserver.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017-02-09.
 */
public class Request {
    String uri=null;
    InputStream inputStream=null;

    Request(InputStream inputStream){
        this.inputStream=inputStream;
    }

    public void parse() {
        StringBuffer sb=new StringBuffer();
        byte[] buf=new byte[2048];
        try {
            inputStream.read(buf);
            for (int i=0;i<buf.length;i++){
                sb.append((char) buf[i]);
            }
            String msg=sb.toString();
            uri=msg.substring(msg.indexOf(" ")+1,msg.indexOf(" ",msg.indexOf(" ")+1));
          } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUri() {
        return uri;
    }
}
