package com.myserver.core;

import java.io.*;

/**
 * Created by Administrator on 2017-02-10.
 */
public class StaticResProcessor {
    public void process(Request request, Response response) {
        OutputStream outputStream=response.getOutputStream();
        FileInputStream fileInputStream=null;
        File file=new File(ServerHandler.WEB_ROOT,request.getUri());
        try {
            System.out.println(file);
            if (file.exists()){
                fileInputStream=new FileInputStream(file);
                byte[] buf=new byte[1024];
                while (fileInputStream.read(buf,0,1024)!=-1){
                    outputStream.write(buf,0,buf.length);

                }
            }else{
                String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                        "Content-Type: text/html\r\n" +
                        "Content-Length: 23\r\n" +
                        "\r\n" +
                        "<h1>File Not Found</h1>";
                outputStream.write(errorMessage.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fileInputStream!=null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
