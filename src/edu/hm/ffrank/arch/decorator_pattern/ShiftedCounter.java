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

/**
 * this class gets a base counter.
 * the read method deliveres the current value of the base counter shifted about a
 * delivered value.
 */
public class ShiftedCounter implements Counter {

    /**by constructor delivered counter.*/
    private Counter deliveredCounter;
    /**the value of the shift.*/
    private int step;

    /**
     * constructor initializes the counter and the value of the shift.
     * @param counter base counter
     * @param step value of the shift
     */
    public ShiftedCounter(Counter counter, int step) {
        if (step < 0) {
            throw new IllegalArgumentException();
        } else {
            this.deliveredCounter = counter;
            this.step = step;
        }

    }

    @Override
    public int read() {
        return this.deliveredCounter.read()+step;
    }

    @Override
    public Counter tick() {
        this.deliveredCounter.tick();
        return this;
    }
}
