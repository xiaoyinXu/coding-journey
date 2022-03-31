package com.xxywebsite.basics.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.Phaser;

/**
 * @author xuxiaoyin
 * @since 2022/3/30
 **/
public class PhaserTest {
    public static class MyThread extends Thread {
        private Phaser phaser;

        public MyThread(Phaser phaser) {
            this.phaser = phaser;
            phaser.register();
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                // sleep (1-5s)
                long sleepMillis = ((long) (Math.random() * 5) + 1) * 1000;
                Thread.sleep(sleepMillis);
                phaser.arriveAndAwaitAdvance();
                System.out.println(Thread.currentThread().getName());
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(0);
        new Thread(() -> {
            phaser.register();
        }).start();
        new Thread(() -> {
            phaser.register();
        }).start();

        Thread.sleep(2000);

        new Thread(() -> {
            phaser.arriveAndDeregister();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
            }
            System.out.println(1);
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            phaser.arriveAndAwaitAdvance();
            System.out.println(2);
        }).start();
    }
}
