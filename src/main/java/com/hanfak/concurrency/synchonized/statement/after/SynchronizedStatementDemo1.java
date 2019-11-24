package concurrency.synchonized.statement.after;

/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper extends Thread {

    static int garlicCount = 0;

    public void run() {
        for (int i=0; i<10_000_000; i++)
            // On the instnace, wont work
            synchronized (this) {
                garlicCount++;
            }
    }
}

public class SynchronizedStatementDemo1 {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper();
        Thread olivia = new Shopper();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + Shopper.garlicCount + " garlic.");
    }
}