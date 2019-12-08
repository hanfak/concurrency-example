package com.hanfak.concurrency.countdownlatch.end; /**
 * Deciding how many bags of chips to buy for the party
 */

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Shopper extends Thread {

    public static int bagsOfChips = 1; // start with one on the list
    private static Lock pencil = new ReentrantLock();
    private static CountDownLatch fistBump = new CountDownLatch(3); // If this is more, and the number threads dont exist, then it will get stuck
    // If less than number of threads, then it will start the other threads and the remaining threads, so can still get race condition

    public Shopper(String name) {
        this.setName(name);
    }
   // Countdown is type of barrier, instead of waiting for threads to finsih, it waits for count to finsih
    // cannot be reset
    public void run() {
        if (this.getName().contains("Olivia")) {
            pencil.lock();
            try {
                bagsOfChips += 3;
                System.out.println(this.getName() + " ADDED three bags of chips.");
            } finally {
                pencil.unlock();
            }
            fistBump.countDown();
        } else { // "Barron"
            try {
                fistBump.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pencil.lock();
            try {
                bagsOfChips *= 2;
                System.out.println(this.getName() + " DOUBLED the bags of chips.");
            } finally {
                pencil.unlock();
            }
        }
    }
}

public class CountDownLatchDemo {
    public static void main(String args[]) throws InterruptedException  {
        // create 10 shoppers: Barron-0...4 and Olivia-0...4
        Shopper[] shoppers = new Shopper[10];
        for (int i=0; i<shoppers.length/2; i++) {
            shoppers[2*i] = new Shopper("Barron-"+i);
            shoppers[2*i+1] = new Shopper("Olivia-"+i);
        }
        for (Shopper s : shoppers)
            s.start();
        for (Shopper s : shoppers)
            s.join();
        System.out.println("We need to buy " + Shopper.bagsOfChips + " bags of chips.");
    }
}