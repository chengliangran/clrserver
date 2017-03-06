package com.finalserver.core;

import com.finalserver.core.container.Container;
import com.finalserver.core.container.SimpleContext;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017-03-06.
 */
public class Connector {

    // operating container
    Container container=new SimpleContext();

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }


//    a core pool to allow connector using it creating core
    ArrayList<HTTPProcessor> processors=new ArrayList<>();
    int minProcessros=5;
    int maxProcessors=20;
    int curProcessors=minProcessros;
//    initializing core pool
    public void start(){
        while (processors.size()<minProcessros){
            processors.add(new HTTPProcessor());
        }
    }
//    put the processor back into the pool
    public void recycle(HTTPProcessor processor){
        processors.add(processor);
    }
//    get processor
    public HTTPProcessor getProcessor(){
        if(processors.size()>0){
            HTTPProcessor processor=processors.remove(processors.size()-1);
            processor.setConnector(this);
            return processor;
        }else{
            if (curProcessors<maxProcessors){
                curProcessors++;
                processors.add(new HTTPProcessor());
                HTTPProcessor processor=processors.remove(processors.size()-1);
                processor.setConnector(this);
                return processor;

            }else {
                return null;
            }
        }
    }



//    main entrance of the server,used to start the ServerSocket and get socket to pass to the processor
    public void connect() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                Socket socket = serverSocket.accept();
                HTTPProcessor processor = getProcessor();
                if (processor!=null){
                processor.start();
                    Thread.currentThread().sleep(10);
                processor.setSocket(socket);
                }else {
                    socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

}
