package com.xxywebsite.classloader;

import com.xxywebsite.classloader.ChildFirstClassLoader;
import sun.text.resources.cldr.rw.FormatData_rw;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author xuxiaoyin
 * @since 2022/2/24
 **/
public class ClassloaderTest {
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InstantiationException {
        File jarDir = new File("./classloader-basics/target/original-classloader-basics-1.0-SNAPSHOT.jar");
        URL[] urls = new URL[]{jarDir.toURL()};

        ChildFirstClassLoader childFirstClassLoader = new ChildFirstClassLoader(urls);
        Class<?> clazz = childFirstClassLoader.loadClass("java.lang.String");
        Class<?> clazz2 = childFirstClassLoader.loadClass("com.xxywebsite.classloader.ClassloaderTest");
        Class<?> clazz3 = childFirstClassLoader.loadClass("sun.text.resources.cldr.rw.FormatData_rw");

        System.out.println(clazz.getClassLoader());
        System.out.println(clazz2.getClassLoader());
        System.out.println(clazz3.getClassLoader());
    }
}
