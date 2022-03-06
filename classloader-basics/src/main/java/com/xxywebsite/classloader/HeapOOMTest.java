package com.xxywebsite.classloader;

/**
 * @author xuxiaoyin
 * @since 2022/3/2
 **/
public class HeapOOMTest {
    public static int[] nums = new int[Integer.MAX_VALUE - 2];
    public static void main(String[] args) {
        System.out.println("Hello");
    }
}
