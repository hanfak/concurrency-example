package com.hanfak.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example07 {
  public static void main(String... args) {
    ExecutorService executorService = Executors.newFixedThreadPool(3);

    //async
    CompletableFuture<String> stringCompletableFuture = CompletableFuture.completedFuture(getMessage());
    CompletableFuture<String> cf = stringCompletableFuture
            .thenApplyAsync(s -> {
              String thread = Thread.currentThread().getName();
              System.out.println("thread in  thenApplyAsync = " + thread);
              System.out.println(Thread.currentThread().isDaemon());
              randomSleep();
              return s.toUpperCase();
            })
            .thenApply(s -> {
              // This will happend after thenApplyAsync is finished and use the same thread
              String thread = Thread.currentThread().getName();
              System.out.println("thread in thenApply= " + thread);
              return s + " added too";
            })
            // Run on different threads but in order , supplied by executor, result will happen in undetermined order
            .thenApplyAsync(s -> {
              randomSleep();
              randomSleep();
              randomSleep();
              String thread = Thread.currentThread().getName();
              System.out.println("thread in thenApplyAsync (1st) with executor= " + thread);
              return s + " more stuff 2";
            }, executorService)
            .thenApplyAsync(s -> {
              randomSleep();
              String thread = Thread.currentThread().getName();
              System.out.println("thread in thenApplyAsync (2nd) with executor= " + thread);
              return s + " more stuff 1";
            }, executorService)
            .thenApplyAsync(s -> {
              String thread = Thread.currentThread().getName();
              System.out.println("thread in thenApplyAsync (3rd) with executor= " + thread);
              return s + " more stuff 3";
            }, executorService);
            // If I add another thenApplyAsync with executor, it will wait till thread has finished and use it
    System.out.println(cf.getNow("Default"));
    System.out.println(cf.join()); // returns result of async call
    executorService.shutdown();
  }

  private static String getMessage() {
    String message = "message";
    System.out.println(message);
    return message;
  }

  private static void randomSleep() {
    try {
      Thread.sleep(new Random().nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
