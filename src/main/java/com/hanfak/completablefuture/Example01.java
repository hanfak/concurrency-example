package com.hanfak.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example01 {
  public static void main(String... args) {
    ExecutorService executorService = Executors.newCachedThreadPool();

    CompletableFuture<String> completableFuture
            = new CompletableFuture<>();


    executorService.submit(() -> {
              Thread.sleep(500);
              completableFuture.complete("Hello");
              return null;
            }
    );
    System.out.println("goodbye");

    try {
      System.out.println(completableFuture.get());
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    } finally {
      executorService.shutdown();
    }
  }
}
