package com.jerrymouse;

import sun.java2d.pipe.SpanIterator;

import javax.print.DocFlavor;
import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-03-02.
 */
public class JerryTest {
    public static void main(String[] args) {
       new JerryTest().test();
    }

    public void test(){
        String path=System.getProperty("user.dir");
        File file=new File(path);
        System.out.println(file);
        System.out.println(file.getAbsoluteFile());
        new URLClassLoader(new URL[2]);
        try {
            System.out.println(file.getCanonicalPath().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
class book{
    @Override
    public String toString() {
        return "book";
    }
}

class novel{
    @Override
    public String toString() {
        return "novel";
    }
}

