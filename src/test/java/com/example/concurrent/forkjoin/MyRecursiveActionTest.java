package com.example.concurrent.forkjoin;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static java.lang.IO.println;

class MyRecursiveActionTest {

    @Test
    void testCompute() {
        ForkJoinPool pool = new ForkJoinPool(4);
        MyRecursiveAction task = new MyRecursiveAction(100);
        pool.invoke(task);
        pool.close();
    }

    @Test
    void testComputeWithCommonPool() {
        println("Using common pool with parallelism: " + ForkJoinPool.commonPool().getParallelism());
        MyRecursiveAction task = new MyRecursiveAction(100);
        ForkJoinPool.commonPool().invoke(task);
    }

}
