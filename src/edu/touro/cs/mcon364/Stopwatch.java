package edu.touro.cs.mcon364;

import java.lang.annotation.Inherited;

public class Stopwatch implements ITimer {
    private long startTime;
    private int timeElapsed;
    private boolean running;

    /**
     * {@inheritDoc}
     *
     * @throws IllegalStateException if <code>Start</code> is called after a <code>Start</code> without a {@link #reset()}.
     */
    @Override
    public void start() throws IllegalStateException {
        if (running) {
            throw new IllegalStateException("Timer cannot be started while running.");
        }

        running = true;
        startTime = System.currentTimeMillis();
    }

    /**
     * {@inheritDoc}
     *
     * @return time in milliseconds since {@link #start()} was called.
     * @throws IllegalStateException if the timer is not running.
     */
    @Override
    public int stop() throws IllegalStateException {
        if (!running) {
            throw new IllegalStateException("The timer has not been started.");
        }

        running = false;
        timeElapsed += (int) (System.currentTimeMillis() - startTime);
        return timeElapsed;
    }

    @Override
    public void reset() {
        running = false;
        timeElapsed = 0;
    }
}
