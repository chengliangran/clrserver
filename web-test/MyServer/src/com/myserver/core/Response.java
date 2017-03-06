package com.myserver.core;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.URL;

/**
 * Created by Administrator on 2017-02-09.
 */
public class Response {

    Request request=null;
    OutputStream outputStream=null;
    public Response(OutputStream outputStream, Request request){
        this.outputStream=outputStream;
        this.request=request;
    }

    public Response(OutputStream outputStream) {
    }

    public void sendResource()   {
        FileInputStream fileInputStream=null;
        File file=new File(ServerHandler.WEB_ROOT,request.getUri());
        try {
            System.out.println(ServerHandler.WEB_ROOT+request.getUri());
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
    public OutputStream getOutputStream(){
        return outputStream;
    }
}
