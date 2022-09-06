package com.xxywebsite.bytecode.javassist;

import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.Proxy;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Method;

/**
 * @author xuxiaoyin
 * @since 2022/9/6
 **/
public class DynamicProxyUtil {
    public static <T> T getProxyInstance(Class<T> clazz, MethodHandler methodHandler) {
        ProxyFactory f = new ProxyFactory();
        f.setSuperclass(clazz);
        f.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method m) {
                // ignore finalize()
                return !m.getName().equals("finalize");
            }
        });
        f.writeDirectory = ".";
        Class c = f.createClass();
        try {
            T instance = (T)c.newInstance();
            ((Proxy)instance).setHandler(methodHandler);
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static class BaseClass {
        public String sayHello(String name) {
            return String.format("Hello %s", name);
        }

        public String sayHi() {
            return "Hi";
        }

    }

    public static void main(String[] args) {
        BaseClass baseClass = DynamicProxyUtil.getProxyInstance(BaseClass.class, new MethodHandler() {
            @Override
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) throws Throwable {
                System.out.println("invoke start");
                Object retVal = proceed.invoke(self, args);
                System.out.println("invoke end");
                return retVal;
            }
        });

        System.out.println(baseClass.sayHello("Cookie"));
        System.out.println(baseClass.sayHi());
    }
}
