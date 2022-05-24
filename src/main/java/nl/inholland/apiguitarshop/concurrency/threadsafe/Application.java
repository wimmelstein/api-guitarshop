package nl.inholland.apiguitarshop.concurrency.threadsafe;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        StoreManager manager = new StoreManager();
        FixedStoreManager fixedManager = new FixedStoreManager();

        Thread inventoryTask = new Thread(() -> manager.populateSoldProducts());
        Thread displayTask = new Thread(() -> manager.displayProducts());

        inventoryTask.start();
        Thread.sleep(2000);
        displayTask.start();
    }
}
