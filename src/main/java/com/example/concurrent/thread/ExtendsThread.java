package com.example.concurrent.thread;

public class ExtendsThread extends Thread {
    @Override
    public void run() {
        System.out.println("Hello from a thread that extends Thread!");
    }
}
