package com.hanfak.executor.scheduledExecutor.example02;

import java.util.Date;

public class WorkerThread implements Runnable{

    private String command;

    public WorkerThread(String s){
        this.command = s;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" Start. Time = "+ new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName()+" End. Time = "+ new Date());
    }

    @Override
    public String toString(){
        return this.command;
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
