package com.xxywebsite.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author xuxiaoyin
 * @since 2022/2/25
 **/
public class ChildFirstClassLoader extends ClassLoader {
    private InternalUrlClassLoader internalUrlClassLoader;

    public ChildFirstClassLoader(URL[] urls) {
        super(Thread.currentThread().getContextClassLoader());
        this.internalUrlClassLoader = new InternalUrlClassLoader(urls);
    }

    private class InternalUrlClassLoader extends URLClassLoader {
        public InternalUrlClassLoader(URL[] urls) {
            super(urls, null);
        }
    }

    @Override
    protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> c;
        try {
            c = internalUrlClassLoader.loadClass(name);
        } catch (ClassNotFoundException e) {
            c = super.loadClass(name, resolve);
        }

        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
}
