package com.xxywebsite.bytecode;

/**
 * @author xuxiaoyin
 * @since 2022/9/9
 **/
public class JavaAgentTestTimed {
    public String hi() {
        return "hi";
    }

    public String hello(String name) {
        return String.format("hello from %s", name);

    }

    public static void main(String[] args) {
        JavaAgentTestTimed javaAgentTestTimed = new JavaAgentTestTimed();
        System.out.println(javaAgentTestTimed.hi());
        System.out.println(javaAgentTestTimed.hello("Cookie"));
    }
}
