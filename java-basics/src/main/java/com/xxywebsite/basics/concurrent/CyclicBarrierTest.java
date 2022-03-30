package com.xxywebsite.basics.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xuxiaoyin
 * @since 2022/3/30
 **/
public class CyclicBarrierTest {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                Thread.sleep(1000);
                System.out.println("finish");
            }
        });
        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
            System.out.println(1);
        }).start();

        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
            System.out.println(2);
        }).start();

        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
            System.out.println(3);
        }).start();

        Thread.sleep(5000);

        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
            System.out.println(1);
        }).start();

        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
            System.out.println(2);
        }).start();

        new Thread(() -> {
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
            } catch (BrokenBarrierException e) {
            }
            System.out.println(3);
        }).start();


    }
}
