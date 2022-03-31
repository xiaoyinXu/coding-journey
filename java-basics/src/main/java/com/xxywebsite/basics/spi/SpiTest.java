package com.xxywebsite.basics.spi;

import java.util.ServiceLoader;

/**
 * @author xuxiaoyin
 * @since 2022/3/31
 **/
public class SpiTest {
    public static void main(String[] args) {
        ServiceLoader<HelloService> serviceLoader = ServiceLoader.load(HelloService.class);
        for (HelloService helloService : serviceLoader) {
            System.out.println(helloService.sayHello());
        }
    }
}
