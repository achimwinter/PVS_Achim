package assignement3.exercise2;

import assignement3.exercise2.Parallel.SearchForkJoin;
import assignement3.exercise2.Sequential.SequentialSearch;
import utils.InitializeArray;
import utils.TimeWatch;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SearchMain {

    private static int arraySize = 20;
    private static final int searchingNumber = 1111;
    private static TimeWatch watch;

    public static void main(final String... args) {
        int[] array = InitializeArray.initializeArray(arraySize);
        int positionIndex = ThreadLocalRandom.current().nextInt(0, arraySize-1);
        array[positionIndex] = searchingNumber;
        watch = TimeWatch.start();
        long timeParallel = usingParallelSearch(array);
        long timeSequential = usingSequentialSearch(array);

        System.out.println("Numberposition: " + positionIndex);
        System.out.println("Sequential needed: " + timeSequential);
        System.out.println("Parallel needed: " + timeParallel);
        System.out.println("Speedup: " + ((float) timeSequential / (float) timeParallel));

    }

    private static long usingParallelSearch(int[] array) {
        int position = 0;
        List<Long> timeNeeded = new LinkedList<>();
        SearchForkJoin search = new SearchForkJoin(array, searchingNumber, 0, arraySize);
        for (int i = 0; i <= 100; i++) {
            watch.reset();
            position = search.compute();
            timeNeeded.add(watch.time(TimeUnit.NANOSECONDS));
        }
        System.out.println(position);
        return getAverage(timeNeeded);

    }

    private static long usingSequentialSearch(int[] array) {
        int position = 0;
        List<Long> timeNeeded = new LinkedList<>();
        SequentialSearch search = new SequentialSearch(array, searchingNumber);
        for (int i = 0; i <= 200; i++) {
            watch.reset();
            position = search.searchForElement();
            timeNeeded.add(watch.time(TimeUnit.NANOSECONDS));
        }
        System.out.println(position);
        return getAverage(timeNeeded);
    }

    private static long getAverage(List<Long> timeList) {
        long result = 0;

        for (int i = 20; i < timeList.size(); ++i) {
            result += timeList.get(i);
        }

        return result / (timeList.size() - 20);
    }
}
