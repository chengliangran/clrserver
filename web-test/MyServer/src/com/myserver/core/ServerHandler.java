package com.myserver.core;

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
                Response response=new Response(outputStream);
                request.parse();
                response.sendResource();
                if (request.getUri().equals("shutdown")){
                    shutdown=true;

                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

}
