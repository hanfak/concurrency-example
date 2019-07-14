package com.hanfak.completablefuture;

import java.util.concurrent.CompletableFuture;

public class Example06 {
  public static void main(String... args) {
    // Not aysnc
    CompletableFuture<String> cf = CompletableFuture.completedFuture(getMessage())
            .thenApply(s -> {
              System.out.println(Thread.currentThread().isDaemon());
              return s.toUpperCase();
            })
            .thenApply(s -> s + " added to");

    System.out.println(cf.getNow(null)); // blocking until all the steps in cf is complete
  }

  private static String getMessage() {
    String message = "message";
    System.out.println(message);
    return message;
  }
}
