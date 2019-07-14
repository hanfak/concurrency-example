package com.hanfak.concurrencypackage;

public class Example01 {
    public static void main(String[] args) {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        task.run();

        Thread thread = new Thread(task);
        // We do not know if the thread will invoke the runnable before or after the "Done"
        thread.start();

        System.out.println("Done!");
    }
}
