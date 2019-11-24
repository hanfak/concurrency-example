package concurrency.threadlifecycle;

/**
 * Two threads cooking soup
 */

class ChefOlivia extends Thread {
    public void run() {
        System.out.println("Olivia started & waiting for sausage to thaw...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Olivia is done cutting sausage.");
    }
}

public class ThreadLifecycleDemo1 {
    public static void main(String args[]) throws InterruptedException {
        System.out.println("Barron started & requesting Olivia's help.");
        Thread olivia = new ChefOlivia();
        System.out.println("  Olivia state: " + olivia.getState());


        System.out.println("Barron tells Olivia to start.");
        olivia.start();
        System.out.println("  Olivia state: " + olivia.getState());


        System.out.println("Barron continues cooking soup.");
        System.out.println("  Olivia state: " + olivia.getState());

        Thread.sleep(500);
        System.out.println("  Olivia state: " + olivia.getState());


        System.out.println("Barron patiently waits for Olivia to finish and join...");
        olivia.join();
        System.out.println("  Olivia state: " + olivia.getState());


        System.out.println("Barron and Olivia are both done!");
    }
}