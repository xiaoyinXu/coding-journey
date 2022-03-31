package com.xxywebsite.basics.spi;

/**
 * @author xuxiaoyin
 * @since 2022/3/31
 **/
public class HelloServiceImpl1 implements HelloService {
    @Override
    public String sayHello() {
        return "Hello1";
    }
}
