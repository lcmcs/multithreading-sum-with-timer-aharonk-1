package edu.touro.cs.mcon364;

import java.util.Random;

public class Main {


    /**
     * Write a program that times the amount of time it takes to add a random list of int’s in the range 1-100. The size
     * of the list shall be 100_000_000.
     * Add the numbers using 1,2,4,8,16,32,64,128,256,512,1024 threads and store the number of milliseconds required
     * Your main program must output the best choice for number of threads for your system. (It will likely be different
     * dependent on number of cores and other factors). Include that value in a  comment
     */
    private static int timeListSum(int[] list, int threads) {
        Stopwatch timer = new Stopwatch();
        timer.start();
        Multithreading.listSum(list, threads);
        return timer.stop();
    }

    public static void main(String[] args) {
        final int NUM_TRIALS = 100;

        int[] threadNumbers = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};
        int[] totalTimes = new int[threadNumbers.length];

        for (int i = 0; i < NUM_TRIALS; i++) {
            Random rand = new Random();

            int[] numbersToSum = new int[100_000_000];
            for (int j = 0; j < 100_000_000; j++) {
                numbersToSum[j] = rand.nextInt(100) + 1;
            }

            for (int j = 0; j < threadNumbers.length; j++) {
                totalTimes[j] += timeListSum(numbersToSum, threadNumbers[j]);
            }
        }

        int lowestTimeIndex = 0;
        long lowestTime = ((long) totalTimes[0]) / NUM_TRIALS;
        for (int i = 1; i < threadNumbers.length; i++) {
            if (Math.min(lowestTime, ((long) totalTimes[i]) / NUM_TRIALS) == ((long) totalTimes[i]) / NUM_TRIALS) {
                lowestTimeIndex = i;
            }
        }

        System.out.println("Lowest time is " + lowestTime + " milliseconds at thread count " + threadNumbers[lowestTimeIndex] + ".");
        // Lowest average time is 60-70 ms w/ 8 threads
        // What is strange to me is that last year, the best run was 170 ms @ 4 threads. I would expect performance to
        //     decrease as time goes on with the same machine.
    }
}
