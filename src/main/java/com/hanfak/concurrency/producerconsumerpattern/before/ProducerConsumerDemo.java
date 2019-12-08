package com.hanfak.concurrency.producerconsumerpattern.before; /**
 * Producers serving soup for Consumers to eat
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class SoupProducer extends Thread {

    private BlockingQueue servingLine;

    public SoupProducer(BlockingQueue servingLine) {
        this.servingLine = servingLine;
    }

    public void run() {
        for (int i=0; i<20; i++) { // serve 20 bowls of soup
            try {
                servingLine.add("Bowl #" + i);
                System.out.format("Served Bowl #%d - remaining capacity: %d\n", i, servingLine.remainingCapacity());
                Thread.sleep(200); // time to serve a bowl of soup
            } catch (Exception e) { e.printStackTrace(); }
        }
    }
}

class SoupConsumer extends Thread {

    private BlockingQueue servingLine;

    public SoupConsumer(BlockingQueue servingLine) {
        this.servingLine = servingLine;
    }

    public void run() {
        while (true) {
            try {
                String bowl = (String)servingLine.take();
                System.out.format("Ate %s\n", bowl);
                Thread.sleep(300); // time to eat a bowl of soup
             } catch (Exception e) { e.printStackTrace(); }
        }
    }
}

public class ProducerConsumerDemo {
    public static void main(String args[]) {
        BlockingQueue servingLine = new ArrayBlockingQueue<String>(5);
        new SoupConsumer(servingLine).start();
        new SoupProducer(servingLine).start();
    }
}