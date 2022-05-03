package edu.touro.cs.mcon364;

public interface IMultithreading {
    /**
     * Sums a list of integers.
     *
     * @param list    a list of integers to sum
     * @param threads the number of threads among which the list is divided evenly
     * @return the sum of the items in the list
     */
    long listSum(int[] list, int threads);
}
