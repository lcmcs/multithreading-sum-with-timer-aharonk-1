package edu.touro.cs.mcon364;

public interface ITimer {
    /**
     * Starts or resumes the Timer.
     */
    void start();

    /**
     * Stops the timer and returns the time since it was started.
     * @return time in milliseconds since {@link #start()} was called.
     */
    int stop();

    /**
     * Resets timer to 0 and stops time from transpiring.
     */
    void reset();
}