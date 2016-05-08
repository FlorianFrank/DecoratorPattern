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
 * This class counts up values of in a delivered number system.
 */
public class NaryCounter implements Counter {

    /**sets the numbersystem.*/
    private int base;
    /**represents the right part of the number.*/
    private int counter;
    /**represents the rest of the number.*/
    private int pow;

    /**
     * sets the numbersystem (base).
     * @param base (numbersystem)
     */
    public NaryCounter(int base) {
        if (base > 1 && base > 9) {
            throw new IllegalArgumentException();
        }
        this.base = base;
        this.pow = 0;
    }

    @Override
    public int read() {
        return pow + this.counter;
    }

    @Override
    public Counter tick() {
        if (this.counter == this.base - 1) {
            this.counter = 0;
            this.pow += 10;
        } else {
            counter++;
        }
        return this;
    }
}
