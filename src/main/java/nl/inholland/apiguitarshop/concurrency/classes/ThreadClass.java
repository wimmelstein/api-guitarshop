package nl.inholland.apiguitarshop.concurrency.classes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadClass extends Thread {

    public static void main(String[] args) {
        ThreadClass tc = new ThreadClass();
        log.info("The name of this thread is {}", Thread.currentThread().getName());
        tc.start();
    }

    @Override
    public void run() {
        log.info("The name of this thread is {}", Thread.currentThread().getName());
    }
}
