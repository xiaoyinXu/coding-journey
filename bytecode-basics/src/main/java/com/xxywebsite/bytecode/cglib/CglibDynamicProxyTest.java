package com.xxywebsite.bytecode.cglib;

import com.xxywebsite.bytecode.common.HelloService;
import com.xxywebsite.bytecode.common.HelloServiceImpl;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author xuxiaoyin
 * @since 2022/9/9
 **/
public class CglibDynamicProxyTest {
    public static void main(String[] args) {
        HelloService helloService = (HelloService) Enhancer.create(HelloServiceImpl.class, new MyMethodInterceptor());
        System.out.println(helloService.sayHello("Cookie"));
    }
    public static class MyMethodInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("start invoking");
            return proxy.invokeSuper(obj, args);
        }
    }
}
