package com.hanfak.executor;

import java.util.concurrent.*;

public class Example03 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Use of callable
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        /*Since submit() doesn't wait until the task completes,
        the executor service cannot return the result of the callable directly.
        Instead the executor returns a special result of type Future which can be
        used to retrieve the actual result at a later point in time.*/

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        System.out.println("future done? " + future.isDone());

        Integer result = future.get();
        /*Calling the method get() blocks the current thread and waits
        until the callable completes before returning the actual result 123*/
        System.out.println("future done? " + future.isDone());
        System.out.println("result: " + result);

        try {
            System.out.println("attempt to shutdown executor");
            executor.shutdown();
            executor.awaitTermination(5, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            System.err.println("tasks interrupted");
        }
        finally {
            if (!executor.isTerminated()) {
                System.err.println("cancel non-finished tasks");
            }
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
}
