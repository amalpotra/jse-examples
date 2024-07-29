package com.example.thread;

import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

class ThreadsTest {
    @Test
    void testThreadDefaultRun() {
        /* Constructor defaults to a platform thread i.e., 1-1 mapped to a kernel thread which is OS-specific.
         * They are expensive to create when compared to virtual threads and should be used sparingly.
         * It Usually takes around 2MB of memory and a bit of CPU processing power.
         */
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
        sleep(5000);
        stoppableRunnable.requestStop();
    }

    @Test
    void testDaemonThread() throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("Daemon thread is running!");
            while (true) {
                try {
                    sleep(1000);
                    System.out.println("...");
                } catch (InterruptedException e) {
                    System.err.println("Thread interrupted!");
                }
            }
        });

        /* Unlike a user thread, a daemon thread will not prevent the JVM from exiting when the main thread finishes.
         * The JVM will exit when the main thread finishes, even if the daemon thread is still running.
         */
        thread.setDaemon(true);
        thread.start();

        // If the thread was a user thread, the JVM would not exit even after the main thread finishes.
        sleep(5000);
    }

    @Test
    void testThreadJoin() throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                for (int i = 0; i < 5; i++) {
                    sleep(1000);
                    System.out.println("Thread 1");
                }
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted!");
            }
            System.out.println("Thread 1 is done!");
        });

        Thread thread2 = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    sleep(1000);
                    System.out.println("Thread 2");
                }
            } catch (InterruptedException e) {
                System.err.println("Thread interrupted!");
            }
            System.out.println("Thread 2 is done!");
        });

        thread1.start();

        thread2.setDaemon(true);
        thread2.start();

        // Wait for thread1 to finish before continuing.
        thread1.join();
        System.out.println("Thread 1 has finished!");

        // Even daemon threads can be joined.
        thread2.join();
        System.out.println("Thread 2 has finished!");
    }
}
