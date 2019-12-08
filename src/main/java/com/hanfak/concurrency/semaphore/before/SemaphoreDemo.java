package com.hanfak.concurrency.semaphore.before; /**
 * Connecting cell phones to a charger
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

class CellPhone extends Thread {

    private static Semaphore charger = new Semaphore(4);

    public CellPhone(String name) {
        this.setName(name);
    }

    public void run() {
        try {
            charger.acquire();
            System.out.println(this.getName() + " is charging...");
            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(this.getName() + " is DONE charging!");
            charger.release();
        }
    }
}

public class SemaphoreDemo {
    public static void main(String args[]) {
        for (int i =0; i < 10; i++)
            new CellPhone("Phone-"+i).start();
    }
}