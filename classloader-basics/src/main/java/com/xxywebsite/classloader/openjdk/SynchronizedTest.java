package com.xxywebsite.classloader.openjdk;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author xuxiaoyin
 * @since 2022/3/29
 **/
public class SynchronizedTest {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        System.out.println(Integer.toHexString(System.identityHashCode(lock)));

        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        Thread.sleep(3000);

        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

        new Thread(() -> {
            synchronized (lock) {
                System.out.println(5);
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println(5);
                System.out.println(ClassLayout.parseInstance(lock).toPrintable());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();
        Thread.sleep(1000);
        System.out.println(ClassLayout.parseInstance(lock).toPrintable());


//        Object lock = new Object();
//        System.out.println(ClassLayout.parseInstance(lock).toPrintable());

//        new Thread(() -> {
//            synchronized (lock) {
//                try {
//                    System.out.println(1);
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                }
//            }
//        }).start();
//
//        Thread.sleep(1000);
//        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
//        new Thread(() -> {
//            synchronized (lock) {
//                try {
//                    System.out.println(2);
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {
//                }
//            }
//            System.out.println(ClassLayout.parseInstance(lock).toPrintable());
//        }).start();
//
//        Thread.sleep(1000);
//        System.out.println(ClassLayout.parseInstance(lock).toPrintable());
    }

}
