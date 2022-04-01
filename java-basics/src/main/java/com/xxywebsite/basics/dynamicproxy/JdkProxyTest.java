package com.xxywebsite.basics.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xuxiaoyin
 * @since 2022/4/1
 **/
public class JdkProxyTest {
    interface HelloService {
        String sayHello();
    }
    public static class HelloServiceImpl implements HelloService {
        @Override
        public String sayHello() {
            return "Hello From Cookie";
        }
    }
    public static class HelloServiceInvocationHandler implements InvocationHandler {
        private Object target;
        public HelloServiceInvocationHandler(Object target) {
            this.target = target;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(proxy.getClass());

            long startTimestamp = System.currentTimeMillis();
            Object returnValue = method.invoke(target, args);
            long endTimestamp = System.currentTimeMillis();
            System.out.println(String.format("方法%s 耗时:%dms", method.getName(), endTimestamp - startTimestamp));

            return returnValue;
        }
    }

    public static void main(String[] args) {
        HelloService helloService = ((HelloService) Proxy.newProxyInstance(JdkProxyTest.class.getClassLoader(), new Class[]{HelloService.class}, new HelloServiceInvocationHandler(new HelloServiceImpl())));
        helloService.sayHello();
    }
}
