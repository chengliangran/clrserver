package com.server20.test;

import com.server20.core.*;
import com.sun.jmx.snmp.tasks.ThreadService;
import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017-04-07.
 */
//***根类
public class UltraTest {
    public static void main(String[] args) {
        Connector connector=new Connector();
        SimpleContext context=new SimpleContext();
        context.addValve(new TestValve());
        StandardBasicValve standardBasicValve=new StandardBasicValve();
        standardBasicValve.setContainer(context);
        context.addServletMapping("/ceshi","ceshi");
        Wrapper wrapper=new SimpleWrapper();
        wrapper.setName("ceshi");
        wrapper.addValve(new SimpleWrapperValve());
        wrapper.setBasicValve(new StandardBasicWrapperValve());
        context.addChild(wrapper);
        context.setBasicValve(standardBasicValve);
        connector.setContainer(context);
        connector.connect();

    }
}
class Constants{
    public static final String root=System.getProperty("user.dir");
    public static final String WEB_ROOT=root+"/server20/WebContent";
 }


//***连接器
class Connector{
    Container container=null;

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }


    //    processors
    ArrayList<Processor> processors=new ArrayList<>();
    int minProcessors=10;
    int nInstances=0;
    int maxProcessors=30;
//    dealwith processors
    public void initProcessors(){
     while (nInstances<minProcessors){
         processors.add(new Processor(this));
         nInstances++;
     }
    }
    public Processor getProcessor(){
        if (processors.size()>0){
            return processors.remove(processors.size()-1);
        }else if (nInstances<maxProcessors){
            processors.add(new Processor(this));
            nInstances++;
            return processors.remove(processors.size()-1);
        }else {
            return null;
        }
    }

    boolean shutdown=false;
    public void connect(){
        Socket socket=null;
        ServerSocket ss= null;
        try {
            ss = new ServerSocket(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (!shutdown){
                try {
                    socket=ss.accept();
                    Processor processor=getProcessor();
                    if (processor!=null){
                    processor.start();
                        processor.asign(socket);

                    }else{
                    }
                 } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //***处理器
    class Processor implements Runnable{
        private Object threadSync = new Object();
    //    Connector
        Connector connector=null;
        Processor(Connector connector){
            this.connector=connector;
        }
        //    Socket
        boolean avaliable=false;
        Socket socket=null;

        synchronized void asign(Socket socket){
            while (avaliable){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.socket=socket;
            avaliable=true;
            notifyAll();

        }

        synchronized Socket getSocket(){
            while (!avaliable){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            avaliable=false;
            notifyAll();
            return socket;

         }

        public void process(){
            Socket socket=getSocket();
            InputStream inputStream=null;
            OutputStream outputStream=null;
            try {
                inputStream=socket.getInputStream();
                outputStream=socket.getOutputStream();
                HttpRequest request=new HttpRequest(inputStream);
                HttpResponse response=new HttpResponse(outputStream,request);
                request.parse();
                connector.getContainer().invoke(request,response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            process();
        }
        public void start(){
            Thread thread=new Thread(this);
            thread.start();
        }
    }

    class HttpRequest{
        String requestString=null;
        InputStream inputStream=null;
        String uri=null;

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public HttpRequest(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        public void parse() {
            byte[] buf=new byte[2048];
            StringBuffer sb=new StringBuffer();
            try {
                int i=inputStream.read(buf);
                while (i!=-1){
                    sb=sb.append(new String(buf));
                    i=inputStream.read(buf);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            requestString=sb.toString();
            String[] requestRows=requestString.split("\r\n");
            String reqLine=  requestRows[0];
            int spac1Index=reqLine.indexOf(" ");
            int spac2Index=reqLine.indexOf(" ",spac1Index+1);
            uri=reqLine.substring(spac1Index+1,spac2Index);

        }
    }
    class HttpResponse{
        Context context=null;

        public Context getContext() {
            return context;
        }

        public void setContext(Context context) {
            this.context = context;
        }

        OutputStream outputStream=null;
        HttpRequest request=null;

        public OutputStream getOutputStream() {
            return outputStream;
        }

        public void setOutputStream(OutputStream outputStream) {
            this.outputStream = outputStream;
        }

        public HttpRequest getRequest() {
            return request;
        }

        public void setRequest(HttpRequest request) {
            this.request = request;
        }

        public HttpResponse(OutputStream outputStream, HttpRequest request) {
            this.outputStream = outputStream;
            this.request = request;
        }
    }
    //***容器
    class ContainerBase implements Container, Context, PipeLine{
        protected String name=null;
        private Container parent=null;
        private HashMap<String,Wrapper> children=new HashMap();
        private Loader loader=null;
        private Logger logger=null;
        private PipeLine pipeLine=new StandardPipeline(this);
        private HashMap servletMapping=null;
        private Mapper mapper=null;
        private boolean started=false;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public BasicValve getBasicValve() {
            return pipeLine.getBasicValve();
        }

        @Override
        public void setBasicValve(BasicValve basicValve) {
            pipeLine.setBasicValve(basicValve);
        }

        @Override
        public void addValve(Valve valve) {
            pipeLine.addValve(valve);
        }

        @Override
        public Valve removeValve(Valve valve) {
            return null;
        }

        @Override
        public Valve[] getValves() {
            return new Valve[0];
        }

        @Override
        public void invoke(HttpRequest request, HttpResponse response) {
            byte[] buf= new byte[0];
            try {
                InputStream inputStream= new FileInputStream(new File(Constants.WEB_ROOT,"ceshi.txt"));
                buf = new byte[2048];
                inputStream.read(buf);
            } catch (IOException e) {
                e.printStackTrace();
            }

            pipeLine.invoke(request,response);
        }

        @Override
        public void addChild(Container container) {
            children.put(((Wrapper)container).getName() ,(Wrapper)container);
        }

        @Override
        public Container removeChild(Container container) {
            return null;
        }

        @Override
        public Wrapper findChild(String name) {
            System.out.println(name);
           return children.get(name);
        }

        @Override
        public Container[] findChildren() {
            return null;
        }

        @Override
        public Container map(HttpRequest request) {
            return null;
        }
    }

    class WebappLoader implements Loader{

    }

    class SimpleContext extends ContainerBase{
        HashMap<String,String> servletMapping=new HashMap<>();
        HashMap<String,Wrapper> children=new HashMap<>();
        Mapper mapper=new StandardContextMapper(this);

        public Mapper getMapper() {
            return mapper;
        }

        public void setMapper(Mapper mapper) {
            this.mapper = mapper;
        }

        public void addServletMapping(String pattern, String name){
            servletMapping.put(pattern,name);
        }
        public String getServletMapping(String pattern){
            System.out.println("name");
            return servletMapping.get(pattern);
        }

        @Override
        public Container map(HttpRequest request){
           return null;
        }
    }
    class StandardContextMapper implements Mapper{

        Context context=null;
        StandardContextMapper(Context context){
            this.context=context;
        }



        @Override
        public Container getContainer() {
            return null;
        }

        @Override
        public void setContainer(Container container) {

        }

        @Override
        public Wrapper mapper(HttpRequest request) {
            String pattern=request.getUri();
            SimpleContext simpleContext= (SimpleContext)context;
            System.out.println("using name");
            String name=simpleContext.getServletMapping(pattern);
            return simpleContext.findChild(name);

         }

        @Override
        public void setProtocal(String protocal) {

        }

        @Override
        public String getProtocal() {
            return null;
        }
    }
    class Wrapper extends ContainerBase{
        Container parent=null;
        Servlet instance=null;
        HashMap<String,String> parameters=new HashMap<>();
        List<Servlet> servlets=new ArrayList<>();
        int nInstances=0;
        int maxInstances=30;
        int minInstances=10;
        int countAllocated=0;
        String servletClass=null;
        String jspFile=null;
        boolean singleThreadMode=false;

        public Container getParent() {
            return parent;
        }

        public void setParent(Container parent) {
            this.parent = parent;
        }


        public Servlet allocate(){
            if (!singleThreadMode){
                synchronized (this){
                    if (instance==null){
                        instance=loadServlet();
                    }
                }
            }
            countAllocated++;
            return instance;
        }

        public Servlet loadServlet(){
            if (instance!=null&&!singleThreadMode){
                return instance;
            }
            Servlet servlet=null;
            Loader loader=new WebappLoader();

            return servlet;
        }
        public void deallocate(Servlet servlet){
            countAllocated++;
            servlets.add(servlet);
        }
    }
    class SimpleWrapper extends Wrapper{
        @Override
        public void invoke(HttpRequest request, HttpResponse response) {
            System.out.println("already invoke the wraper");
            super.invoke(request,response);
        }
    }

    class SimpleWrapperValve implements Valve,Contained{

        @Override
        public String getInfo() {
            return null;
        }

        @Override
        public void invoke(HttpRequest request, HttpResponse response, ValveContext valveContext) {
            System.out.println("已经达到基本阀门");
            valveContext.invokeNext(request,response);
        }

        @Override
        public void setContainer(Container container) {

        }

        @Override
        public Container getContainer() {
            return null;
        }
    }
    class StandardBasicWrapperValve implements BasicValve{


        @Override
        public void invoke(HttpRequest request, HttpResponse response) {
            System.out.println("已经达到最终的阀门");
        }
    }

    class SimpleServlet implements Servlet{

        @Override
        public void init() {

        }

        @Override
        public void service(HttpRequest request, HttpResponse response) {
            System.out.println("处理servlet结果");
        }

        @Override
        public void stop() {

        }

        @Override
        public void start() {

        }
    }
    class StandardPipeline implements PipeLine {
        Container parent=null;

        protected Valve[] valves=new Valve[0];
        protected BasicValve basicValve=null;

        public BasicValve getBasicValve() {
            return basicValve;
        }

        public void setBasicValve(BasicValve basicValve) {
            this.basicValve = basicValve;
        }

        public StandardPipeline(Container container){
            if (container!=null&&container instanceof Context){
                parent=container;
            }
        }

        @Override
        public void addValve(Valve valve) {
            Valve[] newValves=new Valve[valves.length+1];
            for (int i=0;i<valves.length;i++){
                newValves[i]=valves[i];
            }
            newValves[newValves.length-1]=valve;
            valves=newValves;
        }

        @Override
        public Valve removeValve(Valve valve) {
            return null;
        }

        @Override
        public Valve[] getValves() {
            return new Valve[0];
        }

        @Override
        public void invoke(HttpRequest request, HttpResponse response) {
            new StandardValveContext().invokeNext(request,response);
        }

        class StandardValveContext implements ValveContext{

            int pointer=0;
            @Override
            public void invokeNext(HttpRequest request,HttpResponse response){
            int currentPointer=pointer;
            pointer++;
             if (currentPointer<valves.length){
                valves[currentPointer].invoke(request,response,this);
            }else if (currentPointer==valves.length&&basicValve!=null){
                 basicValve.invoke(request,response);
            }else{
             }

        }
    }
}
class ContextBasicValve implements BasicValve{

    @Override
    public void invoke(HttpRequest request, HttpResponse response) {
    }
}

class TestValve implements Valve{

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void invoke(HttpRequest request, HttpResponse response, ValveContext valveContext) {
        valveContext.invokeNext(request,response);
        System.out.println("now we start testing socket");
    }
}

class StandardBasicValve implements BasicValve,Contained{
    Container container=null;
    @Override
    public void invoke(HttpRequest request, HttpResponse response) {
        if (request.getUri().equals("/META-INF")||request.getUri().equals("/WEB-INF")){
            System.out.println("无法进入该区域");
            return;
        }
        System.out.println("the valve is over");
        Context context=(Context) getContainer();
        response.setContext(context);
        Mapper mapper=((SimpleContext)container).getMapper();
        Wrapper wrapper=mapper.mapper(request);
         if (wrapper!=null) {
            wrapper.invoke(request, response);
        }else{
             System.out.println(request.getUri());
             System.out.println("finding no wrapper");
            String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1>Wrapper Not Found</h1>";
            try {
                System.out.println("print errormsg");
                response.getOutputStream().write(errorMessage.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void setContainer(Container container) {
        this.container=container;
    }

    @Override
    public Container getContainer() {
        return container;
    }

}