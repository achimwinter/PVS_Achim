package Assignement2;

import java.util.concurrent.RecursiveTask;

class PrefixSumWorker extends RecursiveTask<int[]> {

    private final int BOUND_FOR_SEQUENTIAL_PROCESSING = 2_600_000;

    private final int[] oldArray;
    private int[] newArray;
    private final int startIndex;
    private final int endIndex;

    PrefixSumWorker(int[] oldArray, int start, int size) {
        this.oldArray = oldArray;
        this.startIndex = start;
        this.endIndex = this.startIndex + size;
        this.newArray = new int[oldArray.length];
    }

    protected int[] compute() {
        return this.isProblemSmallEnough() ? this.computeDirectly() : this.forkTwoProblems();
    }

    private int[] computeDirectly() {
        this.newArray[this.startIndex] = this.oldArray[this.startIndex];

        for (int i = this.startIndex + 1; i < this.endIndex; ++i) {
            this.newArray[i] = this.oldArray[i] + this.newArray[i - 1];
        }

        return this.newArray;
    }

    private int[] forkTwoProblems() {
        int leftHalfSize = this.div2Floor(this.endIndex - this.startIndex);
        int rightHalfSize = this.div2Ceil(this.endIndex - this.startIndex);
        int middleIndex = this.startIndex + leftHalfSize;

        PrefixSumWorker forkLeft = new PrefixSumWorker(this.oldArray, this.startIndex, leftHalfSize);
        PrefixSumWorker forkRight = new PrefixSumWorker(this.oldArray, middleIndex, rightHalfSize);

        forkLeft.fork();
        forkRight.fork();

        return this.concatArrays(forkLeft.join(), this.startIndex, leftHalfSize, forkRight.join(), middleIndex, rightHalfSize);
    }

    private int[] concatArrays(int[] array1, int start1, int size1, int[] array2, int start2, int size2) {
        int lastValue = -100000;

        for (int i = start1; i < start1 + size1; i++) {
            this.newArray[i] = array1[i];
            if(i == (start1 + size1 -1))
                lastValue = this.newArray[i];
        }

        for (int i = start2; i < start2 + size2; i++) {
            this.newArray[i] = array2[i] + lastValue;
        }

        return this.newArray;
    }

    private int div2Floor(int n) {
        return n / 2;
    }

    private int div2Ceil(int n) {
        return n / 2 + n % 2;
    }

    private boolean isProblemSmallEnough() {
        return this.endIndex - this.startIndex <= BOUND_FOR_SEQUENTIAL_PROCESSING;
    }
}
