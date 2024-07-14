package com.example.thread;

public class StoppableRunnable implements Runnable {
    private boolean stopRequested = false;

    public synchronized void requestStop() {
        stopRequested = true;
    }

    public synchronized boolean isStopRequested() {
        return stopRequested;
    }

    @Override
    public void run() {
        System.out.println("StoppableRunnable is running!");
        while (!isStopRequested()) {
            try {
                Thread.sleep(1000);
                System.out.println("...");
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted!");
            }
        }
        System.out.println("StoppableRunnable is stopping!");
    }
}
