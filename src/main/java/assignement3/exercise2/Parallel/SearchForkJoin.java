package assignement3.exercise2.Parallel;

import java.util.concurrent.RecursiveTask;

public class SearchForkJoin extends RecursiveTask<Integer> {

    private final int[] array;
    private final int searchingNumber;
    private final int endIndex;
    private final int startIndex;
    private static final int THRESHOLD_FOR_SEQUENTIAL_ALGORITHM = 50_000_000;

    public SearchForkJoin(int[] array, int searchingNumber, int startIndex, int endIndex) {
        this.array = array;
        this.searchingNumber = searchingNumber;
        this.endIndex = endIndex;
        this.startIndex = startIndex;
    }

    @Override
    public Integer compute() {
        return this.isProblemSmallEnough() ? this.computeDirectly() : this.forkTwoProblems();
    }

    private int forkTwoProblems() {

        int leftHalfSize = this.div2Floor(this.endIndex - this.startIndex);
        int rightHalfSize = this.div2Ceil(this.endIndex - this.startIndex);
        int middleIndex = this.startIndex + leftHalfSize;

        SearchForkJoin forkLeft = new SearchForkJoin(this.array, this.searchingNumber, this.startIndex, leftHalfSize);
        SearchForkJoin forkRight = new SearchForkJoin(this.array, this.searchingNumber, middleIndex, rightHalfSize);

        forkLeft.fork();
        forkRight.fork();

        return this.mergeResults(forkLeft.join(), forkRight.join());
    }

    private int computeDirectly() {
        for (int i = startIndex; i < endIndex; i++) {
            if (array[i] == searchingNumber)
                return i;
        }
        return -1;
    }

    private int mergeResults(int leftForkResult, int rightForkResult) {
        if (leftForkResult > rightForkResult) {
            return leftForkResult;
        } else if (rightForkResult > leftForkResult) {
            return rightForkResult;
        }
        return -1;
    }

    private int div2Floor(int n) {
        return n / 2;
    }

    private int div2Ceil(int n) {
        return n / 2 + n % 2;
    }

    private boolean isProblemSmallEnough() {
        return (this.endIndex - this.startIndex) >= THRESHOLD_FOR_SEQUENTIAL_ALGORITHM;
    }
}
