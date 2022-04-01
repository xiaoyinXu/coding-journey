package com.xxywebsite.basics.copy;

import lombok.Data;
import net.sf.cglib.beans.BeanCopier;

import java.util.concurrent.CyclicBarrier;

/**
 * @author xuxiaoyin
 * @since 2022/4/1
 **/
public class CopierTest {
    public static class A {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        private String name;

        private int age;

    }

    public static class B {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        private String name;

        private Integer age;

    }


    public static void main(String[] args) {
        A a = new A();
        a.setName("Cookie");
        a.setAge(24);

        // 1亿次的时候明显BeanCopier更快, fastJson和ReflectCopier差不多慢
        int n = 100_000_000;

        elapse(a, B.class, n, new MyBeanCopier());
        elapse(a, B.class, n, new FastJsonCopier());
        elapse(a, B.class, n, new ReflectCopier());

    }

    private static <T> void elapse(Object sourceObject, Class<T> targetClazz, int count, Copier copier) {
        long startTimestamp = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            copier.copy(sourceObject, targetClazz);
        }
        long endTimestamp = System.currentTimeMillis();
        System.out.println(String.format("copier:%s, 耗时:%dms", copier.getClass().getName(), endTimestamp - startTimestamp));
    }
}
