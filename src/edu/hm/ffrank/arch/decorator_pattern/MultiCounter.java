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

public class MultiCounter implements Counter {

    private Counter deliveredCounter;
    private int countlimit;
    private int counter;


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
