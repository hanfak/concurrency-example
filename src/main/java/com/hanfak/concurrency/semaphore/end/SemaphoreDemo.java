package com.hanfak.concurrency.semaphore.end; /**
 * Connecting cell phones to a charger
 */

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

class CellPhone extends Thread {
    // Binary semaphore
    private static Semaphore charger = new Semaphore(1);

    public CellPhone(String name) {
        this.setName(name);
    }
    // As the same thread acquires and releases it, it is basically a mutex
    // Could replace the semaphore with reentrant locks and it wiould work the same way
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