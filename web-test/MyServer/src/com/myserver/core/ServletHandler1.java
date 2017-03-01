package com.myserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Administrator on 2017-02-13.
 */
public class ServletHandler1 {
    public void process(Socket socket){

        try {
            InputStream inputStream=socket.getInputStream();
            OutputStream outputStream=socket.getOutputStream();
            Request request=new Request(inputStream);
            Response response=new Response(outputStream,request);
            request.parse();
            System.out.println(request.getUri());
            if(request.getUri().startsWith("/servlet/")){
                new ServletProcessor().process(request,response);
            }else{
                new StaticResProcessor().process(request,response);
            }
         } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
