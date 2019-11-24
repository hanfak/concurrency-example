package concurrency.mutualexclusion; /**
 * Two shoppers adding items to a shared notepad
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Shopper1 extends Thread {

    static int garlicCount = 0;
    static Lock pencil = new ReentrantLock();

    public void run() {
        for (int i=0; i<5; i++) {
            pencil.lock();
            garlicCount++; // This is the critical code that needs to be locked
            pencil.unlock();
            // this is thinking code which does not need to be locked, ow will block the other thread and take
            // longer to complete
            System.out.println(Thread.currentThread().getName() + " is thinking.");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}

public class MutualExclusionDemo1 {
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