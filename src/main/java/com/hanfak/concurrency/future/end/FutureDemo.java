package com.hanfak.concurrency.future.end; /**
 * Check how many vegetables are in the pantry
 */

import java.util.concurrent.*;

class HowManyVegetables implements Callable<Integer> {
    public Integer call() throws Exception {
        System.out.println("Olivia is counting vegetables...");
        Thread.sleep(3000);
        return 42;
    }
}

public class FutureDemo {
    public static void main(String args[]) throws ExecutionException, InterruptedException {
        System.out.println("Barron asks Olivia how many vegetables are in the pantry.");
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> result = executor.submit(new HowManyVegetables());
        System.out.println("Barron can do other things while he waits for the result...");
        System.out.println("Olivia responded with " + result.get());
        executor.shutdown();
    }
}