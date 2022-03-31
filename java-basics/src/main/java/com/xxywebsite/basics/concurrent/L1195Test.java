package com.xxywebsite.basics.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuxiaoyin
 * @since 2022/3/31
 **/
public class L1195Test {
    private static AtomicInteger atomicInteger = new AtomicInteger(1);

    /**
     * odify the given class to output the series [1, 2, "Fizz", 4, "Buzz", ...] where the ith token (1-indexed) of the series is:
     * <p>
     * "FizzBuzz" if i is divisible by 3 and 5,
     * "Fizz" if i is divisible by 3 and not 5,
     * "Buzz" if i is divisible by 5 and not 3, or
     * i if i is not divisible by 3 or 5.
     **/
    public static void printFizzBuzz() {
        while (true) {
            synchronized (L1195Test.class) {
                int value;
                while (!((value = atomicInteger.intValue()) % 3 == 0 && value % 5 == 0)) {
                    try {
                        L1195Test.class.wait();
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("FizzBuzz");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                atomicInteger.incrementAndGet();
                L1195Test.class.notifyAll();
            }
        }
    }

    public static void printFizz() {
        while (true) {
            synchronized (L1195Test.class) {
                int value;
                while (!((value = atomicInteger.intValue()) % 3 == 0 && value % 5 != 0)) {
                    try {
                        L1195Test.class.wait();
                    } catch (InterruptedException e) {
                    }
                }

                System.out.println("Fuzz");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                atomicInteger.incrementAndGet();
                L1195Test.class.notifyAll();
            }
        }
    }



    public static void printBuzz() {
        while (true) {
            synchronized (L1195Test.class) {
                int value;
                while (!((value = atomicInteger.intValue()) % 5 == 0 && value % 3 != 0)) {
                    try {
                        L1195Test.class.wait();
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("Buzz");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                atomicInteger.incrementAndGet();
                L1195Test.class.notifyAll();
            }
        }
    }

    public static void printNumber() {
        while (true) {
            synchronized (L1195Test.class) {
                int value;
                while (!((value = atomicInteger.intValue()) % 3 != 0 && value % 5 != 0)) {
                    try {
                        L1195Test.class.wait();
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println(value);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                atomicInteger.incrementAndGet();
                L1195Test.class.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> printFizzBuzz(), "FizzBuzz").start();
        new Thread(() -> printFizz(), "Fizz").start();
        new Thread(() -> printBuzz(), "Buzz").start();
        new Thread(() -> printNumber(), "Number").start();

    }
}
