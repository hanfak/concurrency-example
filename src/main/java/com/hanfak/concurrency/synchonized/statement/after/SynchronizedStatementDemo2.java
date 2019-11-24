package concurrency.synchonized.statement.after;

/**
 * Two shoppers adding items to a shared notepad
 */

class Shopper2 extends Thread {

    static Integer garlicCount = 0;

    public void run() {
        for (int i=0; i<10_000_000; i++)
            // On the field, works only if field is an object not primitive
            // But data race still exisits
            synchronized (garlicCount) {
                garlicCount++;
            }
    }
}

/**
 *
 *  The problem now is that Java's integer object is immutable.
 *  Once you create a integer instance, you cannot change its value,
 *  but I'm doing just that on line 16 when I increment the garlicCount
 *  so what's really happening here is that every time a thread executes
 *  the garlicCount plus plus operation,
 *  Java instantiates a new integer object which will have a different object ID.
 *  So each time the thread loops back around and executes that synchronized statement,
 *  they'll actually be using a different object for the intrinsic lock.
 *  So they're not really synchronized at all.
 */
public class SynchronizedStatementDemo2 {
    public static void main(String[] args) throws InterruptedException {
        Thread barron = new Shopper2();
        Thread olivia = new Shopper2();
        barron.start();
        olivia.start();
        barron.join();
        olivia.join();
        System.out.println("We should buy " + Shopper2.garlicCount + " garlic.");
    }
}