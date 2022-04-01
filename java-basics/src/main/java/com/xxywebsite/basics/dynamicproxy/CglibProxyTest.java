package com.xxywebsite.basics.dynamicproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xuxiaoyin
 * @since 2022/2/22
 **/
public class CglibProxyTest {
    public static class HelloUtil {
        public String sayHello(String name) {
            return String.format("Hello From %s", name);
        }
        public String sayHi(String name) {
            return String.format("Hi From %s", name);
        }
    }

    public static <T> T delegate(Class<T> clazz) {
        return (T)Enhancer.create(HelloUtil.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                long startTimestamp = System.currentTimeMillis();
                Object retValue = methodProxy.invokeSuper(o, objects);
//                Object retValue = method.invoke(o, objects);
                long endTimestamp = System.currentTimeMillis();
                System.out.println(String.format("%s 耗时 %dms", method.getName(), endTimestamp - startTimestamp));
                return retValue;
            }
        });
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InterruptedException {
        HelloUtil helloUtil = delegate(HelloUtil.class);
        System.out.println(helloUtil.sayHello("Cookie"));
    }
}
