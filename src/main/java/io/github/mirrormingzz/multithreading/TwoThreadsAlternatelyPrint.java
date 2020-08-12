package io.github.mirrormingzz.multithreading;

import java.util.concurrent.CompletableFuture;

/**
 * 两个线程交替打印 0-100
 *
 * @author Mireal
 */
public class TwoThreadsAlternatelyPrint {
    private static int count = 0;
    private static final Object LOCK = new Object();

    public static void main(String[] args) {
        solution1();
//        solution2();
//        solution3();
    }


    /**
     * 使用 wait notify
     */
    public static void solution1() {
        Runnable printer = () -> {
            while (count <= 100) {
                synchronized (LOCK) {
                    System.out.println(Thread.currentThread().getName() + " -> " + count++);
                    LOCK.notify();
                    if (count <= 100) {
                        try {
                            // 让出当前的🔒
                            LOCK.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        CompletableFuture<Void> t1 = CompletableFuture.runAsync(printer);
        CompletableFuture<Void> t2 = CompletableFuture.runAsync(printer);
        CompletableFuture.allOf(t1, t2).join();
    }


    /**
     * 使用 synchronized
     */
    public static void solution2() {

        new Thread(() -> {
            while (count < 100) {
                synchronized (LOCK) {
                    if ((count & 1) == 0) {
                        System.out.println("偶数线程 -> " + count++);
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (count < 100) {
                synchronized (LOCK) {
                    if ((count & 1) == 1) {
                        System.out.println("奇数线程 -> " + count++);
                    }
                }
            }
        }).start();
    }

    /**
     * 使用 synchronized
     */
    public static void solution3() {
        CompletableFuture<Void> t1 = CompletableFuture.runAsync(() -> {
            while (count < 100) {
                synchronized (LOCK) {
                    if ((count & 1) == 0) {
                        System.out.println("偶数线程 -> " + count++);
                    }
                }
            }
        });
        CompletableFuture<Void> t2 = CompletableFuture.runAsync(() -> {
            while (count < 100) {
                synchronized (LOCK) {
                    if ((count & 1) == 1) {
                        System.out.println("奇数线程 -> " + count++);
                    }
                }
            }
        });
        CompletableFuture.allOf(t1, t2).join();
    }
}
