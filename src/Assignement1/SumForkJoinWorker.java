//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Assignement1;

import java.util.concurrent.RecursiveTask;

public class SumForkJoinWorker extends RecursiveTask<Integer> {
    private static final int THRESHOLD_FOR_SEQUENTIAL_PROCESSING = 20;
    private final int[] array;
    private final int startIndex;
    private final int endIndex;

    public SumForkJoinWorker(int[] array, int start, int size) {
        this.array = array;
        this.startIndex = start;
        this.endIndex = start + size;
    }

    protected Integer compute() {
        return this.isProblemSmallEnough() ? this.computeDirectly() : this.forkTwoNewProblems();
    }

    private int forkTwoNewProblems() {
        int leftHalfSize = this.div2Floor(this.endIndex - this.startIndex);
        int rightHalfSize = this.div2Ceil(this.endIndex - this.startIndex);
        int middleIndex = this.startIndex + leftHalfSize;
        SumForkJoinWorker forkLeft = new SumForkJoinWorker(this.array, this.startIndex, leftHalfSize);
        SumForkJoinWorker forkRight = new SumForkJoinWorker(this.array, middleIndex, rightHalfSize);
        forkLeft.fork();
        forkRight.fork();
        return forkLeft.join() + forkRight.join();
    }

    private int div2Floor(int n) {
        return n / 2;
    }

    private int div2Ceil(int n) {
        return n / 2 + n % 2;
    }

    private boolean isProblemSmallEnough() {
        return this.endIndex - this.startIndex <= THRESHOLD_FOR_SEQUENTIAL_PROCESSING;
    }

    private Integer computeDirectly() {
        int sum = 0;

        for(int i = this.startIndex; i < this.endIndex; ++i) {
            sum += this.array[i];
        }

        return sum;
    }
}
