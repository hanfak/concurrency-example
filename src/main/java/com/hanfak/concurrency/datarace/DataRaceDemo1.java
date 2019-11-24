package concurrency.datarace;

/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper1 extends Thread {

    static int garlicCount = 0;

    public void run() {
        for (int i=0; i<10_000_000; i++)
            // This actually does three steps, read, modify and write
            garlicCount++;
    }
}
// This will return different values, and not the value of 20_000_000
// garlicCount++, is being acted upon by two different threads, at different stages of the operation
public class DataRaceDemo1 {
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