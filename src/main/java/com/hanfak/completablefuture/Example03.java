package com.hanfak.completablefuture;

import java.util.concurrent.CompletableFuture;

public class Example03 {
  public static void main(String... args) {

    CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
    System.out.println(cf.isDone());
    System.out.println(cf.getNow(null));
  }
}
