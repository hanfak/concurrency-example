package com.hanfak.completablefuture;

import java.util.concurrent.CompletableFuture;

public class Example10 {
  // Consuming the Result of the Previous Stage
  public static void main(String... args)  {
    StringBuilder result = new StringBuilder();
    CompletableFuture.completedFuture("thenAccept message")
            .thenAccept(s -> result.append(s)); // executed synchronously
    boolean sizeBiggerZero = result.length() > 0;
    System.out.println("Result was empty: " + sizeBiggerZero);

  }
}
