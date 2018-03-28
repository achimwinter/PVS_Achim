package Assignement1;

import java.util.concurrent.Callable;

public class SumWorker implements Callable<Integer> {
    private int[] data;
    private int start;
    private int end;

    public SumWorker(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    public Integer call() {
        int sum = 0;

        for(int i = this.start; i <= this.end; ++i) {
            sum += this.data[i];
        }

        return sum;
    }
}
