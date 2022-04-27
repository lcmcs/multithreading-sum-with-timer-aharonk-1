package edu.touro.cs.mcon364;

public class Multithreading implements IMultithreading {
    private static int[] numbersToSum;
    private static int numThreads;

    public long listSum(int[] list, int threads) {
        numbersToSum = list;
        numThreads = threads;

        Adder[] nest = new Adder[threads];

        for (int i = 0; i < threads; i++) {
            nest[i] = new Adder(i);
            nest[i].start();
        }

        int total = 0;

        try {
            for (Adder a : nest) {
                a.join();
                total += a.getSum();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return total;
    }

    private static class Adder extends Thread {
        private final int STARTING_INDEX;
        private int sum = 0;

        public Adder(int i) {
            STARTING_INDEX = i;
        }

        public int getSum() {
            return sum;
        }

        @Override
        public void run() {
            for (int i = STARTING_INDEX; i < numbersToSum.length; i += numThreads) {
                sum += numbersToSum[i];
            }
        }
    }
}
