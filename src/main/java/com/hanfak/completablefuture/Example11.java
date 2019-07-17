package com.hanfak.completablefuture;

import java.util.concurrent.CompletableFuture;

public class Example11 {
  // Asynchronously Consuming the Result of the Previous Stage
  public static void main(String... args)  {

    StringBuilder result = new StringBuilder();
    CompletableFuture<Void> cf = CompletableFuture.completedFuture("thenAcceptAsync message")
            .thenAcceptAsync(s -> result.append(s));
    cf.join();
    boolean sizeBiggerZero = result.length() > 0;
    System.out.println("Result was empty: " + sizeBiggerZero);

  }
}
