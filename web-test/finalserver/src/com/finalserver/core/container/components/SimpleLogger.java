package com.finalserver.core.container.components;

import com.finalserver.core.Constants;
import com.finalserver.core.container.*;

import java.io.*;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-03-07.
 */
public class SimpleLogger implements Logger {
    /**
     * Created by Administrator on 2017-03-17.
     */
         Writer writer=null;
        String data=null;
        String prefix="FileLogger";
        String suffix=".txt";
        File dir=new File(Constants.WEB_ROOT,"logFiles");
        private boolean timestamp;

        SimpleLogger(){
            if (!dir.exists()){
                dir.mkdir();
            }
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public String getPrefix() {
            return prefix;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public String getSuffix() {
            return suffix;
        }

        public void setSuffix(String suffix) {
            this.suffix = suffix;
        }

        public boolean isTimestamp() {
            return timestamp;
        }

        public void setTimestamp(boolean timestamp) {
            this.timestamp = timestamp;
        }

        @Override
        public Container getContainer() {
            return null;
        }

        @Override
        public void setContainer() {

        }

        @Override
        public String getInfo() {
            return null;
        }

        @Override
        public int getVerbosity() {
            return 0;
        }

        @Override
        public int setVerbosity() {
            return 0;
        }

        @Override
        public void log(String msg) {
            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
            String tsDate=timestamp.toString().substring(0,10);

            if (!tsDate.equals(data)){
                this.data=tsDate;
                close();
                open();
            }
            if (writer!=null){
                try {
                    if (this.timestamp){
                        writer.write(timestamp+msg);

                    } else {
                        writer.write(msg);
                    }               ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void log(Exception e, String msg) {

        }

        @Override
        public void log(String msg, Throwable throwable) {

        }

        @Override
        public void log(String msg, int verbosity) {
            if (this.getVerbosity()>=verbosity){
                log(msg);
            }
        }

        @Override
        public void log(String msg, Throwable throwable, int verbosity) {
            if (this.getVerbosity()>=verbosity){
                log(msg,throwable);
            }
        }

        public void open(){
            File dir=new File(Constants.WEB_ROOT,"logFile");
            dir.mkdir();
            File file=new File(dir,prefix+data+suffix);
            try {
                writer=new PrintWriter(new FileWriter(file,true),true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void close(){
            if (writer!=null){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            data="";

        }
    }

