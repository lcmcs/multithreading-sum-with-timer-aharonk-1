package edu.touro.cs.mcon364;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MultithreadedAddTest {
    @Test
    void sum() {
        final int N = 10_000;
        int[] numbersToSum = new int[N];

        for (int i = 0; i < N; i++) {
            numbersToSum[i] = i+1;
        }

        IMultithreading multithreading = new Multithreading();

        for (int numberOfThreads : Main.threadNumbers) {
            assertEquals((N * (N + 1)) / 2, multithreading.listSum(numbersToSum, numberOfThreads));
        }
    }
}
