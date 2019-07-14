package com.hanfak.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example02 {
  public static void main(String... args) {
    ExecutorService executorService = Executors.newCachedThreadPool();

    CompletableFuture<String> completableFuture
            = new CompletableFuture<>();


    System.out.println(completableFuture.isDone());
    executorService.submit(() -> {
              Thread.sleep(500);
              return CompletableFuture.completedFuture("Hello");
            }
    );

    System.out.println("goodbye");
    System.out.println(completableFuture.isDone());

    try {
      System.out.println(completableFuture.getNow("Default"));
      System.out.println(completableFuture.isDone());
    } finally {
      executorService.shutdown();
    }
  }
}
