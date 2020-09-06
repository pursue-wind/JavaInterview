package io.github.mirrormingzz.concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 演示AtomicIntegerFieldUpdater的用法
 *
 * @author Mireal
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable {

    private static Candidate jerry;
    private static Candidate tom;

    public static final AtomicIntegerFieldUpdater<Candidate> SCORE_UPDATER = AtomicIntegerFieldUpdater
            .newUpdater(Candidate.class, "score");

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            tom.score++;
            SCORE_UPDATER.getAndIncrement(jerry);
        }
    }

    public static class Candidate {
        volatile int score;
    }

    public static void main(String[] args) throws InterruptedException {
        jerry = new Candidate();
        tom = new Candidate();
        AtomicIntegerFieldUpdaterDemo r = new AtomicIntegerFieldUpdaterDemo();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("普通变量：" + tom.score);
        System.out.println("升级后：" + jerry.score);
    }
}
