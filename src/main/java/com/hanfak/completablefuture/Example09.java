package com.hanfak.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Example09 {
  // Running multiple completable futures in parallel
  public static void main(String... args) throws ExecutionException, InterruptedException {
    // Run a task specified by a Runnable Object asynchronously.
    String thread = Thread.currentThread().getName();
    System.out.println("thread outside of async call= " + thread);

    CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
      // Simulate a long-running Job
      try {
        TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
      String thread1 = Thread.currentThread().getName();

      System.out.println("thread inside of async call= " + thread1);

      System.out.println("Async action 1. I'll run in a separate thread than the main thread.");
    });

    CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
      // Simulate a long-running Job
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (InterruptedException e) {
        throw new IllegalStateException(e);
      }
      String thread1 = Thread.currentThread().getName();

      System.out.println("thread inside of async call= " + thread1);

      System.out.println("Async action 2. I'll run in a separate thread than the main thread.");
      return "Result";
    });

    // Block and wait for the future to complete
    future1.get();
    System.out.println(future1.isDone());
    String result = future2.get();
    System.out.println(future2.isDone());
    System.out.println("result = " + result);

  }
}
