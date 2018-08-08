package com.hanfak.executor.scheduledExecutor.example02;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class ScheduledThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        //schedule to run after sometime
        System.out.println("Current Time = " + new Date());

        IntStream.range(0, 3).forEach(i -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            WorkerThread worker = new WorkerThread("do heavy processing");
            scheduledThreadPool.schedule(worker, 10, TimeUnit.SECONDS);
//            scheduledThreadPool.scheduleAtFixedRate(worker, 0, 10, TimeUnit.SECONDS);
//            scheduledThreadPool.scheduleWithFixedDelay(worker, 0, 1, TimeUnit.SECONDS);

        });


        //add some delay to let some threads spawn by scheduler
        Thread.sleep(30000);

        scheduledThreadPool.shutdown();
        while (!scheduledThreadPool.isTerminated()) {
            //wait for all tasks to finish
        }
        System.out.println("Finished all threads");
    }

}
