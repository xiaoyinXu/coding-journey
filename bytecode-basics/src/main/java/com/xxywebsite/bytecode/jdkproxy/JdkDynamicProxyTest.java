package com.xxywebsite.bytecode.jdkproxy;

import com.xxywebsite.bytecode.common.HelloService;
import com.xxywebsite.bytecode.common.HelloServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xuxiaoyin
 * @since 2022/9/9
 **/
public class JdkDynamicProxyTest {
    public static void main(String[] args) {
        HelloService helloService = (HelloService) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{HelloService.class}, new MyInvocationHandler(new HelloServiceImpl()));
        System.out.println(helloService.sayHello("Cookie"));
    }

    public static class MyInvocationHandler implements InvocationHandler {
        private Object instance;

        public MyInvocationHandler(Object instance) {
            this.instance = instance;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(String.format("开始执行 method:%s", method.getName()));
            Object retValue = method.invoke(instance, args);
            return retValue;
        }
    }
}
