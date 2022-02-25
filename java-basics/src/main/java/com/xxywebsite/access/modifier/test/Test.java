package com.xxywebsite.access.modifier.test;

import com.xxywebsite.access.modifier.AccessModifierTest;

/**
 * 子类可以访问其protected, public属性
 * @author xuxiaoyin
 * @since 2022/2/25
 **/
public class Test extends AccessModifierTest {
    public static void main(String[] args) {
    }
    public void test() {
        System.out.println(this.k);
        System.out.println(this.z);
    }
}
