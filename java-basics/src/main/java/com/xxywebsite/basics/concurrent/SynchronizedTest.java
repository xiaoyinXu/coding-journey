package com.xxywebsite.basics.concurrent;

import lombok.SneakyThrows;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author xuxiaoyin
 * @since 2022/3/30
 **/
public class SynchronizedTest {
    public static class Producer implements Runnable {
        private BlockingQueue<Integer> blockingQueue;
        public Producer(BlockingQueue<Integer> blockingQueue) {
           this.blockingQueue = blockingQueue;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                blockingQueue.offer(1);
                System.out.println(String.format("生产者生产, 目前队列元素:%d", blockingQueue.size()));

                long millis = (long) (Math.random() * 1000);
                Thread.sleep(millis);
            }
        }
    }

    public static class Consumer implements Runnable {
        private BlockingQueue<Integer> blockingQueue;
        public Consumer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                blockingQueue.poll();
                System.out.println(String.format("消费者消费, 目前队列元素:%d", blockingQueue.size()));

                long millis = (long) (Math.random() * 1000);
                Thread.sleep(millis);
            }
        }
    }

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);
        new Thread(new Producer(blockingQueue)).start();
        new Thread(new Consumer(blockingQueue)).start();
    }

}
