package io.github.mirrormingzz.concurrency.cas;

/**
 * 模拟CAS操作，等价代码
 *
 * @author Mireal
 */
public class SimulatedCAS {
    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue, int newValue) {
        int oldValue = value;
        if (oldValue == expectedValue) {
            value = newValue;
        }
        return oldValue;
    }
}
