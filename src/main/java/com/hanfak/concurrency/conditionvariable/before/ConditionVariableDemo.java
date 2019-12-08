package com.hanfak.concurrency.conditionvariable.before; /**
 * Two hungry people, anxiously waiting for their turn to take soup
 */

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class HungryPerson extends Thread {

    private int personID;
    private static Lock slowCookerLid = new ReentrantLock();
    private static int servings = 11;

    public HungryPerson(int personID) {
        this.personID = personID;
    }

    public void run() {
        while (servings > 0) {
            slowCookerLid.lock();
            try {
                if ((personID != servings % 2) && servings > 0) { // check if it's your turn
                    servings--; // it's your turn - take some soup!
                    System.out.format("Person %d took some soup! Servings left: %d\n", personID, servings);
                } else { // not your turn - put the lid back
                    System.out.format("Person %d checked... then put the lid back.\n", personID);
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
        for (int i=0; i<2; i++)
            new HungryPerson(i).start();
    }
}