//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Assignement1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public Main() {
    }

    public static void main(String... args) throws Exception {
        int length = Integer.valueOf(args[0]);
        int processors = 8;
        int piece = length / processors;
        int[] data = initializeArray(length);
        long start = System.nanoTime();
        int sum_seq = sum_seq(data);
        long end = System.nanoTime();
        System.out.println("Sequential Sum: " + sum_seq + " time: " + (int)(end - start));
        start = System.nanoTime();
        int sum_Executor = sum_Executor(data, processors, piece);
        end = System.nanoTime();
        System.out.println("Executor Sum: " + sum_Executor + " time: " + (int)(end - start));
        start = System.nanoTime();
        int sum_Fork = sum_Fork(data);
        end = System.nanoTime();
        System.out.println("Fork Sum: " + sum_Fork + " time: " + (int)(end - start));
        start = System.nanoTime();
        int sum_Stream = Arrays.stream(data).parallel().sum();
        end = System.nanoTime();
        System.out.println("Stream Sum: " + sum_Stream + " time: " + (int)(end - start));
    }

    private static int sum_Fork(int[] data) {
        ForkJoinPool pool = new ForkJoinPool();
        return (Integer)pool.invoke(new SumForkJoinWorker(data, 0, data.length));
    }

    private static int sum_Executor(int[] data, int processors, int piece) throws Exception {
        int sum = 0;
        ExecutorService executorService = Executors.newFixedThreadPool(processors);
        List<Future> futures = new LinkedList();

        for(int i = 0; i < processors; ++i) {
            int start = i * piece;
            int end = (i + 1) * piece - 1;
            Callable<Integer> c = new SumWorker(data, start, end);
            Future<Integer> f = executorService.submit(c);
            futures.add(f);
        }

        int result;
        for(Iterator var12 = futures.iterator(); var12.hasNext(); sum += result) {
            Future<Integer> f = (Future)var12.next();
            result = (Integer)f.get();
        }

        executorService.shutdown();
        return sum;
    }

    private static int sum_seq(int[] data) {
        int sum = 0;

        for(int i = 0; i < data.length; ++i) {
            sum += data[i];
        }

        return sum;
    }

    private static int[] initializeArray(int length) {
        int[] data = new int[length];

        for(int i = 0; i < length; ++i) {
            data[i] = ThreadLocalRandom.current().nextInt(0, 150);
        }

        return data;
    }
}
