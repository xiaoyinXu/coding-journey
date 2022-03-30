package com.xxywebsite.spring.basics.classloader;


import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author xuxiaoyin
 * @since 2022/2/25
 **/
public class ChildFirstClassLoader extends URLClassLoader {
    public ChildFirstClassLoader(URL[] urls) {
        super(urls, Thread.currentThread().getContextClassLoader());
    }


    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c = findLoadedClass(name);
        if (c != null) {
            return c;
        }
        try {
            c = findClass(name);
        } catch (ClassNotFoundException e) {
            c = super.loadClass(name, resolve);
        }

        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
}
