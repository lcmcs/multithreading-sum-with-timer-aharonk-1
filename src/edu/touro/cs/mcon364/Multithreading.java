package edu.touro.cs.mcon364;

import java.util.concurrent.*;

public class Multithreading {
    private static int[] numbersToSum;
    private static int numThreads;

    /**
     * Sums a list of integers.
     *
     * @param list    a list of integers to sum
     * @param threads the number of threads among which the list is divided evenly
     * @return the sum of the items in the list
     */
    public static long listSum(int[] list, int threads) {
        numbersToSum = list;
        numThreads = threads;

        ExecutorService nest = Executors.newFixedThreadPool(threads);
        Future<Integer>[] subSums = new Future[threads];

        for (int i = 0; i < threads; i++) {
            subSums[i] = nest.submit(new Adder(i));
        }

        int total = 0;

        try {
            nest.shutdown();
            if (nest.awaitTermination(10, TimeUnit.SECONDS)) {
                for (Future<Integer> subSum : subSums) {
                    total += subSum.get();
                }
            } else {
                throw new TimeoutException("The execution did not complete.");
            }
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
        }

        return total;
    }

    private static class Adder implements Callable<Integer> {
        private final int STARTING_INDEX;

        public Adder(int i) {
            STARTING_INDEX = i;
        }

        @Override
        public Integer call() {
            int sum = 0;
            for (int i = STARTING_INDEX; i < numbersToSum.length; i += numThreads) {
                sum += numbersToSum[i];
            }
            return sum;
        }
    }
}
