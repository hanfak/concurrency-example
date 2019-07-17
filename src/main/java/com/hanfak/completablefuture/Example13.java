package com.hanfak.completablefuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;

public class Example13 {
  // Composing CompletableFutures
  public static void main(String... args) {

    String original = "Message";
    CompletableFuture<String> cf = completedFuture(original).thenApply(Example13::delayedUpperCase)
            .thenCompose(upper -> completedFuture(original).thenApply(Example13::delayedLowerCase)
                    .thenApply(s -> upper + s));
    System.out.println("MESSAGEmessage " + cf.join());

  }

  private static String delayedUpperCase(String s) {
    randomSleep();
    return s.toUpperCase();
  }

  private static String delayedLowerCase(String s) {
    randomSleep();
    return s.toLowerCase();
  }

  private static void randomSleep() {
    try {
      Thread.sleep(new Random().nextInt(1000));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
