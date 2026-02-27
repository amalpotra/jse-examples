package com.example.concurrent.forkjoin;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static java.lang.IO.println;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MyRecursiveTaskTest {

    @Test
    void compute() {
        MyRecursiveTask task = new MyRecursiveTask(100);
        ForkJoinPool pool = new ForkJoinPool(4);
        assertEquals(300, pool.invoke(task));
        pool.close();
    }

    @Test
    void computeWithCommonPool() {
        println("Using common pool with parallelism: " + ForkJoinPool.commonPool().getParallelism());
        MyRecursiveTask task = new MyRecursiveTask(100);
        assertEquals(300, ForkJoinPool.commonPool().invoke(task));
    }

}
