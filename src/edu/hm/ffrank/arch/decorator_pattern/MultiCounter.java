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
 * This class gets another counter and a countlimit.
 * It returns a value as long as the countlimit pretends.
 */
public class MultiCounter implements Counter {

    /**by constructor delivered counter.*/
    private Counter deliveredCounter;
    /**number of repeats for every number.*/
    private int countlimit;
    /**number of actual repeats of the current number.*/
    private int counter;

    /**
     * constructor sets the countlimit and the counter.
     * @param deliveredCounter base counter
     * @param countlimit number of repeats
     */
    public MultiCounter(Counter deliveredCounter, int countlimit) {
        if (countlimit < 1) {
            throw new IllegalArgumentException();
        } else {
            this.deliveredCounter = deliveredCounter;
            this.countlimit = countlimit;
            this.counter = 1;
        }
    }

    @Override
    public int read() {
        return this.deliveredCounter.read();
    }

    @Override
    public Counter tick() {
        if (this.counter == this.countlimit) {
            this.deliveredCounter.tick();
            this.counter = 1;
        } else {
            this.counter++;
        }
        return this;
    }
}
