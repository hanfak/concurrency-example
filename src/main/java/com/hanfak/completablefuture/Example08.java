package com.hanfak.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example08 {
  // Running multiple completable futures in parallel
  public static void main(String... args) throws ExecutionException, InterruptedException {
    CompletableFuture<String> future1
            = CompletableFuture.supplyAsync(() -> "Hello");
    CompletableFuture<String> future2
            = CompletableFuture.supplyAsync(() -> "Beautiful");
    CompletableFuture<String> future3
            = CompletableFuture.supplyAsync(() -> "World");

    CompletableFuture<Void> combinedFuture
            = CompletableFuture.allOf(future1, future2, future3);

    combinedFuture.get();

    System.out.println(future1.isDone());
    System.out.println(future2.isDone());
    System.out.println(future3.isDone());

    String combined = Stream.of(future1, future2, future3)
            .map(CompletableFuture::join)
            .collect(Collectors.joining(" "));

    System.out.println(combined);
  }
}
