package nl.inholland.apiguitarshop.concurrency.classes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnableLambda {

    public static void main(String[] args) {
        new Thread(() ->
                log.info("The name of this thread is {}",
                        Thread.currentThread().getName()))
                .start();
    }
}
