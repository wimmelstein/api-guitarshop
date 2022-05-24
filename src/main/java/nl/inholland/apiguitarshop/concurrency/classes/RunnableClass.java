package nl.inholland.apiguitarshop.concurrency.classes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RunnableClass implements Runnable {

    public static void main(String[] args) {
        RunnableClass rc = new RunnableClass();
        log.info("This thread has name {}", Thread.currentThread().getName());
        new Thread(rc).start();
    }

    @Override
    public void run() {
        log.info("This thread has name {}", Thread.currentThread().getName());
    }
}
