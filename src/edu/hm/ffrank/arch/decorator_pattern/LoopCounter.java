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

import java.util.NoSuchElementException;

/**
 * This class gets a number of integer values.
 * after calling the tick method it returns the next value.
 * After reaching the end it starts with the first value again.
 */
public class LoopCounter implements Counter {

    private int[] values;
    private int counter;

    public LoopCounter(int... values) {
        if (values.length == 0) {
            throw new NoSuchElementException();
        }
        this.values = values;
        this.counter = 0;
    }

    @Override
    public int read() {
        return values[counter];
    }

    @Override
    public Counter tick() {
        if (this.counter == (this.values.length - 1)) {
            this.counter = 0;
        } else {
            counter++;
        }
        return this;
    }
}
