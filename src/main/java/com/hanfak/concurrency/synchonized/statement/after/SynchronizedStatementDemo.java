package concurrency.synchonized.statement.after;

/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper1 extends Thread {

    static int garlicCount = 0;

    public void run() {
        for (int i=0; i<10_000_000; i++)
            // On the class field
            synchronized (Shopper1.class) {
                garlicCount++;
            }
    }
}

public class SynchronizedStatementDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper1();
        Thread olivia = new Shopper1();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + Shopper1.garlicCount + " garlic.");
    }
}