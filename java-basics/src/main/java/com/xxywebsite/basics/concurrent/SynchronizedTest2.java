package com.xxywebsite.basics.concurrent;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author xuxiaoyin
 * @since 2022/3/30
 **/
public class SynchronizedTest2 {
    public static class Producer implements Runnable {
        private List<Integer> dataList;
        private int threshold;
        public Producer(List<Integer> dataList, int threshold) {
           this.dataList = dataList;
           this.threshold = threshold;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                synchronized (SynchronizedTest2.class) {
                    while (dataList.size() == threshold) {
                        SynchronizedTest2.class.wait();
                    }
                    dataList.add(1);
                    System.out.println(String.format("生产者生产, 目前队列元素:%d", dataList.size()));

                    SynchronizedTest2.class.notifyAll();
                }
            }
        }
    }

    public static class Consumer implements Runnable {
        private List<Integer> dataList;
        public Consumer(List<Integer> dataList) {
            this.dataList = dataList;
        }

        @SneakyThrows
        @Override
        public void run() {
            while (true) {
                synchronized (SynchronizedTest2.class) {
                    while (dataList.isEmpty()) {
                        SynchronizedTest2.class.wait();
                    }

                    dataList.remove(dataList.size() - 1);
                    System.out.println(String.format("消费者消费, 目前队列元素:%d", dataList.size()));
                    SynchronizedTest2.class.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> dataList = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            new Thread(new Producer(dataList, 10)).start();
        }
        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer(dataList)).start();
        }
    }

}
