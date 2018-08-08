package com.hanfak.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example01 {
    public static void main(String[] args) {
        // Factory to create single thread
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });
        // Executors need to be stopped
    }
}
