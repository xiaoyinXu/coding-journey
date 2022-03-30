package com.xxywebsite.basics.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * A输出完后，B、C、D、E ... 随便乱序输出， 所有输出完之后再循环
 *
 * 关键在于 B、C、D、E...输出后，怎么自动aSemaphore.release();
 * @author xuxiaoyin
 * @since 2022/3/30
 **/
public class ABCNTest {
    private static Semaphore aSemaphore = new Semaphore(1);

    private static class ReversableCountDownLatch extends CountDownLatch {
        private int count = 0;
        private Semaphore semaphore;
        private CountDownLatch countDownLatch;
        public ReversableCountDownLatch(int count) {
            //
            super(count);

            countDownLatch = new CountDownLatch(count);
        }

        @Override
        public void countDown() {
            countDownLatch.countDown();
        }

        public void reverse(int count) {
            countDownLatch = new CountDownLatch(count);
        }

        @Override
        public void await() throws InterruptedException {
            countDownLatch.await();
        }
    }

    public static void main(String[] args) {
        int n = 5;
        ReversableCountDownLatch reversableCountDownLatch = new ReversableCountDownLatch(0);
        List<Semaphore> semaphoreList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            semaphoreList.add(new Semaphore(0));
        }

        // A Thread
        new Thread(() -> {
            while (true) {
                try {
                    reversableCountDownLatch.await();
                } catch (InterruptedException e) {
                }
                reversableCountDownLatch.reverse(n);

                System.out.println("A");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }

                for (Semaphore semaphore : semaphoreList) {
                    semaphore.release(1);
                }
            }

        }).start();

        for (int i = 0; i < n; i++) {
            char ch = (char)('A' + i + 1);
            final int index = i;
            new Thread(() -> {
                while (true) {
                    Semaphore semaphore = semaphoreList.get(index);
                    try {
                        semaphore.acquire(1);
                    } catch (InterruptedException e) {
                    }

                    System.out.println(ch);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }

                    reversableCountDownLatch.countDown();
                }
            }).start();
        }
    }

}
