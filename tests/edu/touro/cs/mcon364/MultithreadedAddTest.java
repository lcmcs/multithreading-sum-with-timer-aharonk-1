package edu.touro.cs.mcon364;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultithreadedAddTest {
    static IMultithreading multithreading = new Multithreading();

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024})
    void sum(int numberOfThreads) {
        final int N = 10_000;
        int[] numbersToSum = new int[N];

        for (int i = 0; i < N; i++) {
            numbersToSum[i] = i+1;
        }

        assertEquals((N * (N + 1)) / 2, multithreading.listSum(numbersToSum, numberOfThreads));
    }
}
