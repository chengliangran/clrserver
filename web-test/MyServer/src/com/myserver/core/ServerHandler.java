package com.myserver.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017-02-08.
 */
public class ServerHandler {
    public static final String WEB_ROOT=System.getProperty("user.dir")+ File.separator+"webroot";
    final String SHUTDOWN="shutdown";
    int port=8080;
    public void await() {
        ServerSocket server=null;
        try {
            server=new ServerSocket(port,1, InetAddress.getByName("localhost"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        Socket socket=null;
        InputStream inputStream=null;
        OutputStream outputStream=null;
        boolean shutdown=false;
        while (!shutdown){
            try {
                socket=server.accept();
                inputStream=socket.getInputStream();
                outputStream=socket.getOutputStream();
                Request request=new Request(inputStream);
                Response response=new Response(outputStream,request);
                request.parse();
                System.out.println(request.getUri());
                if(request.getUri().startsWith("servlet/")){
                    new ServletProcessor().process(request,response);

                }else{
                    new StaticResProcessor().process(request,response);
                }
                 if (request.getUri().equals("shutdown")){
                    shutdown=true;

                }
                socket.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
