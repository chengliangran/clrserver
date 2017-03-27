package com.serever20.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Administrator on 2017-03-27.
 */
public class SocketTest {
    public static void main(String[] args) {
        Socket socket=null;
        try {
            ServerSocket ss = new ServerSocket(8080);
            while (true) {
                socket = ss.accept();
                int i=0;
                StringBuilder sb=new StringBuilder();
                BufferedReader reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                while (i!=-1){
                    i=reader.read();
                    sb.append((char)i);
                }
                System.out.println(sb.toString());
                System.out.println("finish one socket");
                byte[] paper=new byte[2048];
                InputStream inputStream=new FileInputStream(new File(Constants.ceshi));
                OutputStream outputStream=socket.getOutputStream();
                int index= inputStream.read(paper);
                while (index!=-1){
                    System.out.println(new String(paper));
                    String s="HTTP/1.1 404 File Not Found\r\n" +
                            "Content-Type: text/html\r\n" +
                            "Content-Length: 23\r\n" +
                            "\r\n" +
                            "<h1>File Not Found</h1>";
                    outputStream.write(s.getBytes());
                    index= inputStream.read(paper);
                    System.out.println(i);
                }
                reader.close();
                inputStream.close();
                outputStream.close();

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
