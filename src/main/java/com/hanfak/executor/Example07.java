package com.hanfak.executor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ExecutorService.html#invokeAny(java.util.Collection)
public class Example07 {
    public static void main(String[] args) {
        /*
        The invokeAny() method takes a collection of Callable objects, or subinterfaces of Callable.
        Invoking this method does not return a Future, but returns the result of one of the Callable objects.
        You have no guarantee about which of the Callable's results you get. Just one of the ones that finish.

        If one of the tasks complete (or throws an exception), the rest of the Callable's are cancelled.
        */
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Set<Callable<String>> callables = new HashSet<>();

        callables.add(() -> "Task 1");
        callables.add(() -> "Task 2");
        callables.add(() -> "Task 3");
        callables.add(() -> "Task 4");

        String result = null;
        try {
            result = executorService.invokeAny(callables);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("result = " + result);

        executorService.shutdown();
    }
}
