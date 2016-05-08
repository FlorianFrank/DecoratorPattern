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

public class JumpCounter implements Counter {

    private Counter deliveredCounter;
    private int step;

    public JumpCounter(Counter deliveredCounter, int step) {
        if (step < 0) {
            throw new IllegalArgumentException();
        } else {
            this.deliveredCounter = deliveredCounter;
            this.step = step;
        }
    }

    @Override
    public int read() {
        return this.deliveredCounter.read();
    }

    @Override
    public Counter tick() {
        int counter = 0;
        while (counter < this.step) {
            this.deliveredCounter.tick();
            counter++;
        }
        return this;
    }
}
