package com.example.thread;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class VirtualThreadsTest {

    @Test
    void testVirtualThread() {
        // Equivalent to Thread.ofVirtual().start(runnable);
        Thread.startVirtualThread(() -> System.out.println("Hello from a virtual thread!"));
    }

    @Test
    void testLotOfVirtualThreads() throws InterruptedException {
        List<Thread> vThreads = new ArrayList<>();

        int nThreads = 100_000;
        for (int i = 0; i < nThreads; i++) {
            int finalI = i;
            Thread vThread = Thread.startVirtualThread(() -> {
                int result = 0;
                for (int j = 0; j < 10; j++) {
                    result = j + 1;
                }
                System.out.println("vThread[" + finalI + "]: " + result);
            });
            vThreads.add(vThread);
        }

        for (Thread vThread : vThreads) {
            // Join all of 'em
            vThread.join();
        }
        System.out.println("All virtual threads have finished!");
    }
}
