package io.github.mirrormingzz.concurrency.threadlocal;

/**
 * @author Mireal
 */
public class ThreadLocalNPE {

    ThreadLocal<Long> longThreadLocal = new ThreadLocal<Long>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }

    public long get() {
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        System.out.println(threadLocalNPE.get());
        Thread thread1 = new Thread(() -> {
            threadLocalNPE.set();
            System.out.println(threadLocalNPE.get());
        });
        thread1.start();
    }
}
