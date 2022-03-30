package com.xxywebsite.classloader.openjdk;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @author xuxiaoyin
 * @since 2022/3/28
 **/
public class ObjectHeaderTest {
    public static class A {
        private String name;
        private Integer age;
    }
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
        System.out.println("The identity hash code is " + System.identityHashCode(a1) + "," + Integer.toHexString(System.identityHashCode(a1)));
        System.out.println("The identity hash code is " + System.identityHashCode(a2));
        System.out.println(ClassLayout.parseInstance(a1).toPrintable());
        System.out.println(ClassLayout.parseInstance(a2).toPrintable());
//        System.out.println(ClassLayout.parseClass(A.class).toPrintable());

    }
}
