package io.github.mirrormingzz.model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * 使用 BlockingQueue 实现生产者消费者模式
 *
 * @author Mireal
 */
public class ProducerConsumerModel2 {

    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(100);

        Runnable producer = () -> {
            try {
                queue.put("数据");
                System.out.println("生产数据...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        };

        Runnable consumer = () -> {
            try {
                String product = queue.take();
                System.out.println("消费" + product + "..");

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        };

        IntStream.rangeClosed(1, 50).forEach(i -> {
            CompletableFuture.runAsync(producer);
            CompletableFuture.runAsync(consumer);
        });
        countDownLatch.await();
    }
}