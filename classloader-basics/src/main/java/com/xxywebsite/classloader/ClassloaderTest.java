package com.xxywebsite.classloader;

import com.xxywebsite.classloader.ChildFirstClassLoader;

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

        System.out.println(clazz.getClassLoader());
        System.out.println(clazz2.getClassLoader());
    }
}
