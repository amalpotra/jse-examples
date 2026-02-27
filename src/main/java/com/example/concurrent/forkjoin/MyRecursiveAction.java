package com.example.concurrent.forkjoin;

import java.util.concurrent.RecursiveAction;

public class MyRecursiveAction extends RecursiveAction {
    private final long workLoad;

    public MyRecursiveAction(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {
        if (workLoad > 16) {
            System.out.println("Splitting workLoad : " + workLoad);
            var workLoad1 = workLoad / 2;
            var workLoad2 = workLoad - workLoad1;

            MyRecursiveAction subTask1 = new MyRecursiveAction(workLoad1);
            MyRecursiveAction subTask2 = new MyRecursiveAction(workLoad2);

            invokeAll(subTask1, subTask2);
        } else {
            System.out.println("Doing workLoad myself: " + workLoad);
        }
    }
}
