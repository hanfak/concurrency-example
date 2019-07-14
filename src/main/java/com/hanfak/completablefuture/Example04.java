package com.hanfak.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

public class Example04 {
  public static void main(String... args) {
    // implement a async process
    // Uses common forkjoinpool if no executor is specified
    CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
      System.out.println("Is thread a daemon thread" + Thread.currentThread().isDaemon());
      randomSleep();
    });
    System.out.println(cf.isDone());
    sleepEnough();
    System.out.println(cf.isDone());
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
