package com.example.thread;

public class ImplementsRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hello from a thread that implements Runnable!");
    }
}
