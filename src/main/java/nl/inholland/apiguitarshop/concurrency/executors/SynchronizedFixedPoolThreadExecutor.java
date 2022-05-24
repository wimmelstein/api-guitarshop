package nl.inholland.apiguitarshop.concurrency.executors;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedFixedPoolThreadExecutor {

    private int counter = 0;
    private final Object lock = new Object();

    public static void main(String[] args) {
        SynchronizedFixedPoolThreadExecutor synchronizedFixedPoolThreadExecutor = new SynchronizedFixedPoolThreadExecutor();
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            for (int i = 0; i < 10; i++) {
                service.submit(synchronizedFixedPoolThreadExecutor::incrementAndPrint);
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    private void incrementAndPrint() {
        synchronized (lock) {
            counter++;
            log.info("{}: {}", Thread.currentThread().getName(), counter);
        }
    }
}
