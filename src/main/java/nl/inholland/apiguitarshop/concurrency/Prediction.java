package nl.inholland.apiguitarshop.concurrency;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Prediction {

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(
                    () -> {
                        for (int j = 0; j < 5; j++) {
                            log.info("{}, j= {} ", Thread.currentThread().getName(), j);
                        }
                    }
            ).start();

            new Thread(() ->
                    log.info("{}: {}", Thread.currentThread().getName(), "Hello world"))
                    .start();
        }
    }
}
