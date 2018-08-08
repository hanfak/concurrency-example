package com.hanfak.executor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Example08 {
    public static void main(String[] args) {
        /*The invokeAll() method invokes all of the Callable objects you pass to it
        in the collection passed as parameter. The invokeAll() returns a list of
        Future objects via which you can obtain the results of the executions of each Callable.

        Keep in mind that a task might finish due to an exception, so it may not have "succeeded".
         There is no way on a Future to tell the difference.
        */
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Set<Callable<String>> callables = new HashSet<>();

        callables.add(() -> "Task 1");
        callables.add(() -> "Task 2");
        callables.add(() -> "Task 3");
        callables.add(() -> "Task 4");
        callables.add(() -> "Task 5");


        List<Future<String>> futures = null;
        try {
            futures = executorService.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(Future<String> future : futures){
            try {
                System.out.println("future.get = " + future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }
}
