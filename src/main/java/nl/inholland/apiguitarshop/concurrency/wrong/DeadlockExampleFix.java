package nl.inholland.apiguitarshop.concurrency.wrong;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeadlockExampleFix {

    public static final Object lock1 = new Object() {
        @Override
        public String toString() {
            return "lock1";
        }
    };

    public static final Object lock2 = new Object() {
        @Override
        public String toString() {
            return "lock2";
        }
    };

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                log.info("{}: I have {} ", Thread.currentThread().getName(), lock1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    // Empty
                }
                log.info("{}: waiting for {}", Thread.currentThread().getName(), lock2);
                synchronized (lock2) {
                    log.info("{}: Holding {} and {}", Thread.currentThread().getName(), lock1, lock2);
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (lock1) {
                log.info("{}: I have {} ", Thread.currentThread().getName(), lock1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ie) {
                    // Empty
                }
                log.info("{}: waiting for {}", Thread.currentThread().getName(), lock1);
                synchronized (lock2) {
                    log.info("{}: Holding {} and {}", Thread.currentThread().getName(), lock1, lock2);
                }
            }
        }).start();

    }
}
