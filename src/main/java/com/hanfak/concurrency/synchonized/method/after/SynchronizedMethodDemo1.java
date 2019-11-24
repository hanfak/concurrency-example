package concurrency.synchonized.method.after;

/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper1 extends Thread {

    static int garlicCount = 0;

    private synchronized void addGarlic() {
        garlicCount++;
    }

    public void run() {
        for (int i=0; i<10_000_000; i++)
            addGarlic();
    }
}

/**the addGarlic method as static so that it's associated with the shopper class
and not a specific instance of a shopper.
 By doing so, when either thread invokes the synchronized addGarlic method,
 it will acquire the intrinsic lock that's associated with the class object.
 If I remove the static keyword on line nine,
 then each of the two shopper threads will invoke their own instance of the addGarlic method
 which is associated with their own object's intrinsic lock.
 If I run the program now, it does not work correctly.
 The threads are no longer using the same intrinsic lock so I end up with a data race.*/

public class SynchronizedMethodDemo1 {
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