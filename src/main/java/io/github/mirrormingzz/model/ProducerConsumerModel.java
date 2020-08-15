package io.github.mirrormingzz.model;

import java.util.LinkedList;

/**
 * 使用 wait 和 notify 实现生产者消费者模式
 *
 * @author Mireal
 */
public class ProducerConsumerModel {
    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();
        Producer producer = new Producer(eventStorage);
        Consumer consumer = new Consumer(eventStorage);

        new Thread(producer).start();
        new Thread(consumer).start();
    }

    static class Producer implements Runnable {
        private EventStorage storage;

        public Producer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.put(i);
            }
        }
    }

    static class Consumer implements Runnable {
        private EventStorage storage;

        public Consumer(EventStorage storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                storage.take(i);
            }
        }
    }

    static class EventStorage {
        private int size;
        private LinkedList<String> storage;

        public EventStorage() {
            size = 10;
            storage = new LinkedList<>();
        }

        public synchronized void put(int i) {
            while (storage.size() == this.size) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            storage.push("数据" + i);
            System.out.println("生产 -> 数据" + i + " 当前仓库中已经有了 " + storage.size() + " 条数据");
            notify();
        }

        public synchronized void take(int i) {
            while (storage.size() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("消费 -> " + storage.poll() + " 当前仓库中剩下 " + storage.size() + " 条数据");
            notify();
        }
    }
}
