package com.hanfak.executor.scheduledExecutor;

import java.util.concurrent.*;

public class Example01 {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);
        //schedules the given Callable for execution after the given delay.
        ScheduledFuture scheduledFuture =
                scheduledExecutorService.schedule(() -> {
                            System.out.println("Executed!");
                            return "Called!";
                        },
                        5,
                        TimeUnit.SECONDS);

        try {
            System.out.println("result = " + scheduledFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        scheduledExecutorService.shutdown();
    }
}
