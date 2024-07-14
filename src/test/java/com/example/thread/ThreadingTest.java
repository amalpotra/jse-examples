package com.example.thread;

import org.junit.jupiter.api.Test;

class ThreadingTest {
    @Test
    void testThreadDefaultRun() {
        // Constructor defaults to a platform thread i.e., 1-1 mapped to kernel thread.
        Thread thread = new Thread();
        thread.start();
    }

    @Test
    void testThreadWithOfPlatform() {
        Thread.ofPlatform()
                .name("Started Thread")
                .start(() -> System.out.println("Hello from a platform thread!"));

        Thread unstartedThread = Thread.ofPlatform()
                .name("Unstarted Thread")
                .unstarted(() -> System.out.println("Hello from a platform thread!"));
        unstartedThread.start();
    }

    @Test
    void testThreadExtendsThread() {
        ExtendsThread extendsThread = new ExtendsThread();
        extendsThread.start();
    }

    @Test
    void testThreadImplementsRunnable() {
        ImplementsRunnable implementsRunnable = new ImplementsRunnable();
        Thread thread = new Thread(implementsRunnable);
        thread.start();
    }

    @Test
    void testThreadLambda() {
        // Since Runnable is a functional interface, we can use a lambda expression to create a new Runnable object.
        Thread.ofPlatform().start(() -> System.out.println("Hello from a thread that uses a lambda!"));
    }

    @Test
    void testThreadAnonymousClass() {
        // We can also use an anonymous class to create a new Runnable object.
        Thread.ofPlatform().start(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from a thread that uses an anonymous class!");
            }
        });
    }

    @Test
    void testStoppableRunnable() throws InterruptedException {
        StoppableRunnable stoppableRunnable = new StoppableRunnable();
        Thread.ofPlatform().start(stoppableRunnable);

        // Sleep the main thread for 5 seconds to allow the StoppableRunnable thread to run.
        Thread.sleep(5000);
        stoppableRunnable.requestStop();
    }
}
