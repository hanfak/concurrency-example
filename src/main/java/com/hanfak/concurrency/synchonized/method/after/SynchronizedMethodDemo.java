package concurrency.synchonized.method.after;

/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper extends Thread {

    static int garlicCount = 0;

    private static synchronized void addGarlic() {
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

public class SynchronizedMethodDemo {
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