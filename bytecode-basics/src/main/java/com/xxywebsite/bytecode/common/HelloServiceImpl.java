package com.xxywebsite.bytecode.common;

/**
 * @author xuxiaoyin
 * @since 2022/9/9
 **/
public class HelloServiceImpl implements HelloService {
    private String a;
    private String b;
    private String c;
    @Override
    public String sayHello(String name) {
        return String.format("Hello From %s~~", name);
    }
}
