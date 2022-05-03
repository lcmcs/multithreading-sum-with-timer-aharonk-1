package edu.touro.cs.mcon364;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StopwatchTest {
    @Test
    void timing() {
        Stopwatch timer = new Stopwatch();
        try {
            long start = System.currentTimeMillis();
            timer.start();

            Thread.sleep(10_000);

            long end = System.currentTimeMillis();
            int time = timer.stop();

            assertEquals(end-start, time);
        } catch (InterruptedException ignored) {
            assert false;
        }
    }
}
