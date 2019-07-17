package com.hanfak.completablefuture;

public class Example12 {
  // Need to use java 9
  // Completing a Computation Exceptionally
  public static void main(String... args) {
    // There is a failure in the computation

//    CompletableFuture<String> cf = CompletableFuture.completedFuture("message")
//            .thenApplyAsync(String::toUpperCase,
//                    CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
//    CompletableFuture<String> exceptionHandler = cf.handle((s, th) -> (th != null) ? "message upon cancel" : "");
//    cf.completeExceptionally(new RuntimeException("completed exceptionally"));
//    System.out.println("Was not completed exceptionally " + cf.isCompletedExceptionally());
//    try {
//      cf.join();
//      fail("Should have thrown an exception");
//    } catch (CompletionException ex) { // just for testing
//      System.out.println("completed exceptionally " + ex.getCause().getMessage());
//    }
//    System.out.println("message upon cancel " + exceptionHandler.join());

  }
}
