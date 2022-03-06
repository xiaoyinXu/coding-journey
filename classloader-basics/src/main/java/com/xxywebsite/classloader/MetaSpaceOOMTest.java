package com.xxywebsite.classloader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * -XX:+PrintFlagsFinal -XX:MetaspaceSize=1m -XX:MaxMetaspaceSize=20m
 * @author xuxiaoyin
 * @since 2022/3/2
 **/
public class MetaSpaceOOMTest {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException {
        List<ClassLoader> classLoaderList = new ArrayList<>();
        File jarDir = new File("./classloader-basics/target/original-classloader-basics-1.0-SNAPSHOT.jar");
        URL[] urls = new URL[]{jarDir.toURL()};
        for (int i = 0; i < 1000000; i++) {
            System.out.println(i);
            ChildFirstClassLoader childFirstClassLoader = new ChildFirstClassLoader(urls);
            classLoaderList.add(childFirstClassLoader);
            childFirstClassLoader.loadClass("com.xxywebsite.classloader.ClassloaderTest");
        }
    }
}
