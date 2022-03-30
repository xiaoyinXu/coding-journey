package com.xxywebsite.basics.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * 三个线程分别打印A B C，
 * 保证最终的结果为ABC、ACB的组合
 *
 * @author xuxiaoyin
 * @since 2022/3/30
 **/

public class ABCACBTest {
    private static Semaphore bSemaphore = new Semaphore(0);
    private static Semaphore cSemaphore = new Semaphore(0);
    private static CountDownLatch countDownLatch = new CountDownLatch(0);

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                }
                countDownLatch = new CountDownLatch(2);

                System.out.println("A");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                bSemaphore.release(1);
                cSemaphore.release(1);
            }
        }).start();
        new Thread(() -> {
            while (true) {
                try {
                    bSemaphore.acquire(1);
                } catch (InterruptedException e) {
                }
                System.out.println("B");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                countDownLatch.countDown();
            }
        }
        ).start();
        new Thread(() -> {
            while (true) {
                try {
                    cSemaphore.acquire(1);
                } catch (InterruptedException e) {
                }
                System.out.println("C");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                countDownLatch.countDown();
            }
        }).

                start();

    }
}
