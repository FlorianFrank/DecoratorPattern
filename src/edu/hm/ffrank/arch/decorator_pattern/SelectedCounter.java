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

import java.util.function.IntPredicate;

/**
 * This class gets another counter and a lambda expression.
 * it prints out only values which accomplish the receivables of the lambda expression.
 */
public class SelectedCounter implements Counter {

    private Counter deliveredCounter;
    private IntPredicate function;


    public SelectedCounter(Counter counter, IntPredicate function) {
        if (function == null) {
            throw new NullPointerException();
        } else {
            this.deliveredCounter = counter;
            this.function = function;
            this.tickLoop();
        }
    }

    @Override
    public int read() {
        return this.deliveredCounter.read();
    }

    @Override
    public Counter tick() {
        this.deliveredCounter.tick();
        this.tickLoop();
        return this;

    }

    private void tickLoop() {
        while (!this.function.test(this.deliveredCounter.read())) {
            this.deliveredCounter.tick();
        }
    }
}
