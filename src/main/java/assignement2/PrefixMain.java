package assignement2;

import utils.TimeWatch;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static utils.InitializeArray.initializeArray;

public class PrefixMain {
    public PrefixMain() {
    }

    public static void main(String... args) {
        int arraylength = 20_000_000;
        int[] array = initializeArray(arraylength);
        List<Long> timeSequential = new LinkedList<>();
        List<Long> timeParallel = new LinkedList<>();
        TimeWatch watch = TimeWatch.start();
        int[] seqArray = new int[0];
        int[] parArray = new int[0];

        int i;
        long passedTimeParallel;
        for(i = 0; i < 200; ++i) {
            watch.reset();
            seqArray = calcArray_seq(array);
            passedTimeParallel = watch.time(TimeUnit.NANOSECONDS);
            timeSequential.add(passedTimeParallel);
        }

        for(i = 0; i < 200; ++i) {
            watch.reset();
            parArray = forkJoin_prefix(array);
            passedTimeParallel = watch.time(TimeUnit.NANOSECONDS);
            timeParallel.add(passedTimeParallel);
        }

        for(i = 0; i < array.length; ++i) {
            System.out.println(seqArray[i] + "\t" + parArray[i]);
        }

        System.out.printf("Sequential needed: %,d%n", getAverage(timeSequential));
        System.out.printf("Parallel needed:   %,d%n", getAverage(timeParallel));
    }

    private static long getAverage(List<Long> timeList) {
        long result = 0;

        for(int i = 100; i < timeList.size(); ++i) {
            result += timeList.get(i);
        }

        return result / (timeList.size() - 100);
    }

    private static int[] forkJoin_prefix(int[] oldArray) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new PrefixSumWorker(oldArray, 0, oldArray.length));
    }

    private static int[] calcArray_seq(int[] oldArray) {
        int[] newArray = new int[oldArray.length];
        newArray[0] = oldArray[0];

        for(int i = 1; i < oldArray.length; ++i) {
            newArray[i] = newArray[i - 1] + oldArray[i];
        }

        return newArray;
    }


}
