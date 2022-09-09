package com.xxywebsite.bytecode.bytebuddy;

import com.xxywebsite.bytecode.common.HelloService;
import com.xxywebsite.bytecode.common.HelloServiceImpl;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.function.Supplier;

/**
 * @author xuxiaoyin
 * @since 2022/9/9
 **/
public class DynamicProxyTest {
    public static void main(String[] args) throws Exception {
        HelloService helloService = new ByteBuddy()
                .subclass(HelloServiceImpl.class)
                .implement(HelloService.class)
                .method(ElementMatchers.nameEndsWith("Hello"))
                .intercept(MethodDelegation.to(new TimingInterceptor()))
                .make()
                .load(ClassLoader.getSystemClassLoader())
                .getLoaded()
                .newInstance();


        System.out.println(helloService.sayHello("Cookie"));
    }
}
