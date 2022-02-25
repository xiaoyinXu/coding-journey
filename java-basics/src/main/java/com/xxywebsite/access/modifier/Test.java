package com.xxywebsite.access.modifier;

/**
 * 同包类可以访问其默认权限、protect权限、public权限
 * @author xuxiaoyin
 * @since 2022/2/25
 **/
public class Test {
    public static void main(String[] args) {
        AccessModifierTest accessModifierTest = new AccessModifierTest();
        System.out.println(accessModifierTest.k);
        System.out.println(accessModifierTest.y);
        System.out.println(accessModifierTest.z);
    }
}
