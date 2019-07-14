package com.hanfak.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Example05 {
  public static void main(String... args) throws ExecutionException, InterruptedException {
    ExecutorService executorService = Executors.newCachedThreadPool();

    CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
      System.out.println("Some action");
      randomSleep();
      return "action done";
    }, executorService);

    System.out.println(cf.isDone());
    sleepEnough();
    System.out.println(cf.isDone());
    System.out.println(cf.get());
    executorService.shutdown();
  }

  private static void randomSleep() {
    try {
      Thread.sleep(new Random().nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void sleepEnough() {
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
