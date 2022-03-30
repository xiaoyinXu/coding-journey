package com.xxywebsite.basics.concurrent;

/**
 * Thread1 waiting for lock2 while Thread1 waiting for lock1
 * @author xuxiaoyin
 * @since 2022/3/30
 **/
public class DeadLockTest {
    private static Object lock1 = new Object();
    private static Object lock2 = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                synchronized (lock2) {
                    System.out.println(0);
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {

                synchronized (lock1) {
                    System.out.println(0);
                }
            }
        }).start();

    }
}
