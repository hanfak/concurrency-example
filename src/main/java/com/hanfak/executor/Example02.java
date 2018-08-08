package com.hanfak.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Example02 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });

        //The active threads inside this ExecutorService prevents the JVM from shutting down.
        try {
           /* The executor shuts down softly by waiting a certain amount of time for termination of currently running tasks.
            After a maximum of five seconds the executor finally shuts down by interrupting all running tasks.
            */
            System.out.println("attempt to shutdown executor");
            /*
            The ExecutorService will not shut down immediately, but it will no longer accept new tasks,
            and once all threads have finished current tasks, the ExecutorService shuts dow
             */
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
            /*
            If you want to shut down the ExecutorService immediately, you can call the shutdownNow() method.
            This will attempt to stop all executing tasks right away,
            and skips all submitted but non-processed tasks.
            There are no guarantees given about the executing tasks.
            Perhaps they stop, perhaps the execute until the end. It is a best effort attempt.
             */
            executor.shutdownNow();
            System.out.println("shutdown finished");
        }
    }
}
