package com.xxywebsite.library.test;

import com.xxywebsite.library.HelloUtil;

/**
 * 依赖A 和 依赖B里面都有com.xxywebsite.library.HelloUtil这个类
 * 最终类加载器加载的是classpath里靠前jar包的com.xxywebsite.library.HelloUtil类
 * @author xuxiaoyin
 * @since 2022/2/24
 **/
public class SameClassNameTest {
    public static void main(String[] args) {
        HelloUtil.sayHello();
    }
}
