package nl.inholland.apiguitarshop.concurrency.executors;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class FixedPoolThreadExecutor {

    private int counter = 0;

    public static void main(String[] args) {
        FixedPoolThreadExecutor fixedPoolThreadExecutor = new FixedPoolThreadExecutor();
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            for (int i = 0; i < 10; i++) {
                service.submit(fixedPoolThreadExecutor::incrementAndPrint);
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }

    private void incrementAndPrint() {
        counter++;
        log.info("{}: {}", Thread.currentThread().getName(), counter);
    }
}
