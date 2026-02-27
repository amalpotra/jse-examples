package com.example.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

public class MyRecursiveTask extends RecursiveTask<Long> {
    private final long workLoad;

    public MyRecursiveTask(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected Long compute() {
        if (workLoad > 16) {
            System.out.println("Splitting workLoad : " + workLoad);
            var workLoad1 = workLoad / 2;
            var workLoad2 = workLoad - workLoad1;

            MyRecursiveTask subTask1 = new MyRecursiveTask(workLoad1);
            MyRecursiveTask subTask2 = new MyRecursiveTask(workLoad2);

            invokeAll(subTask1, subTask2);

            return subTask1.join() + subTask2.join();
        } else {
            System.out.println("Doing workLoad myself: " + workLoad);
            return workLoad * 3;
        }
    }
}
