package com.hanfak.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Example05 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future future = executorService.submit(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
            }
        });
        System.out.println(future.isDone());
        System.out.println(future.isDone());
        System.out.println(future.isDone());
        System.out.println(future.isDone());
        System.out.println(future.isDone());
        future.get();  //returns null if the task has finished correctly.
        System.out.println(future.isDone());

    }
}
