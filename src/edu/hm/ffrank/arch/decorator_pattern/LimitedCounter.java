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
 * This counter gets another counter and a limit.
 * the read method only returns the value if the value is below the limit
 * else it returns the limit.
 */
public class LimitedCounter implements Counter {

    /**limit for the counts.*/
    private int limit;
    /**delivered counter.*/
    private Counter counter;

    /**
     * constructor sets limit and counter.
     * @param counter delivered counter
     * @param limit limit for all values
     */
    public LimitedCounter(Counter counter, int limit) {
        if (limit < 0) {
            throw new IllegalArgumentException();
        } else {
            this.limit = limit;
            this.counter = counter;
        }
    }

    @Override
    public int read() {
        if (this.counter.read() < limit) {
            return this.counter.read();
        } else {
            return limit;
        }
    }

    @Override
    public Counter tick() {
        this.counter.tick();
        return this;
    }
}
