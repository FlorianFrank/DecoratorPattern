/**
 * Organisation: Hochschule München
 * Fach: Rechnerarchitektur
 * System: Macbook Pro Mitte 2014 (Intel Core i5 2x 2,6 Ghz, 8GB RAM, SSD)
 * Java: Version 1.8
 *
 * @version 07.05.2016
 * @author Florian Frank, Alioun Diagne
 */
package edu.hm.ffrank.arch.decorator_pattern;

public class ShiftedCounter implements Counter {

    private Counter deliveredCounter;
    private int step;
    private int counter;

    public ShiftedCounter(Counter counter, int step) {
        if (step < 0) {
            throw new IllegalArgumentException();
        } else {
            this.deliveredCounter = counter;
            this.step = step;
            this.counter = this.deliveredCounter.read() + step;
        }

    }

    @Override
    public int read() {
        return this.counter;
    }

    @Override
    public Counter tick() {
        this.counter = this.deliveredCounter.tick().read() + step;
        return this;
    }
}
