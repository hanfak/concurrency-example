package com.hanfak.concurrency.conditionvariable.end; /**
 * Two hungry people, anxiously waiting for their turn to take soup
 */

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class HungryPerson extends Thread {

    private int personID;
    private static Lock slowCookerLid = new ReentrantLock();
    private static int servings = 11;
    private static Condition soupTaken = slowCookerLid.newCondition();

    public HungryPerson(int personID) {
        this.personID = personID;
    }

    public void run() {
        while (servings > 0) {
            slowCookerLid.lock();
            try {
                while ((personID != servings % 5) && servings > 0) { // check if it's not your turn
                    System.out.format("Person %d checked... then put the lid back.\n", personID);
                    soupTaken.await();
                }
                if (servings > 0) {
                    //signal condition
                    servings--; // it's your turn - take some soup!
                    System.out.format("Person %d took some soup! Servings left: %d\n", personID, servings);
                    soupTaken.signalAll(); // wakes all threads up, to check it is theirturn
//                    soupTaken.signal();
                    // Could use thismethod, but for multiple threads the signal method
                    // which will only wake up one of the waiting threads.
                    // If it doesn't wake up the correct thread, whose turn it is next,
                    // then the program will get stuck.
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                    slowCookerLid.unlock();
            }
        }
    }
}

public class ConditionVariableDemo {
    public static void main(String args[]) {
        for (int i=0; i<5; i++)
            new HungryPerson(i).start();
    }
}