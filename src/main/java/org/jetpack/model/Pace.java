package org.jetpack.model;

public class Pace {
    private long period;
    private long counter;

    public Pace(long period) {
        this.period = period;
        resetCounter();
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    public long getCounter() {
        return this.counter;
    }

    public void resetCounter() {
        this.counter = 0;
    }

    public long update(long elapsed) {
        counter += elapsed;
        long paces = counter / period;
        counter -= paces * period;

        return paces;
    }
}
