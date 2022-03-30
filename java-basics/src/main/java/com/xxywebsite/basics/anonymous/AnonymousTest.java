package com.xxywebsite.basics.anonymous;

/**
 * 1、匿名类和lambda function的区别：
 *  匿名类是compile time生成的，和"普通类等价"
 *  while
 *  lambda function "class" is generated at runtime.
 *
 * 2、为什么匿名类里引用外部的变量，必须保证变量是final或者effectively final ?
 *
 * 首先匿名类方法体用到的变量，最终会作为"生成类"的成员变量
 *
 * final class AnonymousTest$1 implements Runnable {
 *     private int val1;
 *     private int val2;
 *     AnonymousTest$1(int var1, int var2) {
 *         this.val1 = var1;
 *         this.val2 = var2;
 *     }
 *
 *     public void run() {
 *         System.out.println(this.val1);
 *         System.out.println(this.val2);
 *     }
 * }
 *
 * 由于上述机制，最终导致会存在两个"同名变量", 一个为匿名类里的"var1"和一个是外部方法里的"var1"。 假如你在匿名类外部赋值"var1 = 20", 实际不会影响匿名类里的var1, it's confusing this way. 为了避免这个问题，所以java直接强制要求变量为final。
 * Yes, a local class or anonymous class that captures local variables from the enclosing method is a "closure" -- it contains runnable code that can use local variables from the enclosing method even after that run of the enclosing method has already finished. But local variables cease to exist at the end of their scope, so the local or anonymous class must have some way to access these variables after those variables no longer exist in their original scope. Java's solution is to have the local or anonymous class have a copy of each captured local variable. The problem with this is that there are now two different copies of the variable with the same name, one used inside the local or anonymous class, one outside. If you could assign to one copy, it would not affect the other copy, causing problems. The solution was to required that captured variables are final (before JDK 8) or effectively final (since JDK 8), which means they cannot be assigned to.
 * @author xuxiaoyin
 * @since 2022/3/30
 **/

public class AnonymousTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                final int i2 = i;
                final int j2 = j;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(i2);
                        System.out.println(j2);
                    }
                }).start();
            }
        }
    }
}
