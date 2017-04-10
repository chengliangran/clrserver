package com.server20.test;

import sun.misc.Launcher;

/**
 * Created by Administrator on 2017-03-30.
 */
public class LoaderTest {
    public static void main(String[] args) {
        new WebappClassLoader().loadClass();
    }

}
class WebappClassLoader{
    public Class loadClass(){

         ClassLoader classLoader=Launcher.getLauncher().getClassLoader();
        try {
            Class clazz=classLoader.loadClass("LoaderTest");
            Object object=clazz.newInstance();
            System.out.println((LoaderTest)object);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(classLoader);
        return null;

    }



}
