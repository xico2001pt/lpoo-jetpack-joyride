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

    public void resetCounter() {
        this.counter = 0;
    }

    public int update(long elapsed) {
        int paces = 0;
        counter += elapsed;
        while (counter >= period) {
            paces++;
            counter -= period;
        }
        return paces;
    }
}
