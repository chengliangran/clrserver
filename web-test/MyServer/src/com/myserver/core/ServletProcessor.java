package com.myserver.core;

import com.myserver.servlet.Servlet;
import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by Administrator on 2017-02-10.
 */
public class ServletProcessor {

     public void process(Request request, Response response) {
        String servletString=request.getUri().substring(request.getUri().lastIndexOf("/")+1);
        System.out.println(servletString);

        URLClassLoader loader=null;
//        设置classloader
        try {
            URL[] urls=new URL[1];
            URLStreamHandler streamHandler=null;
            File classPath=new File(ServerHandler.WEB_ROOT);
            String repo=(new URL("file",null,classPath.getCanonicalPath()+File.separator).toString());
            urls[0]=new URL(null,repo,streamHandler);
            loader=new URLClassLoader(urls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取servlet类
        Class servletClass=null;
        try {
            servletClass=loader.loadClass(servletString);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        生成类
        Servlet servlet=null;
        try {
            servlet = (Servlet) servletClass.newInstance();
            servlet.service(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  }
