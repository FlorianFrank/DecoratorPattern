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

import java.util.Calendar;

/**
 * This class contains the method read which returns the actual Seconds
 * the method tick waits till the next second and and returns the actual time
 */
public class ClockSecondCounter implements Counter {

    private int counter;

    public ClockSecondCounter() {
        counter = Calendar.getInstance().get(Calendar.SECOND);
    }


    @Override
    public int read() {
        return this.counter;
    }

    @Override
    public Counter tick() {
        if (this.counter != Calendar.getInstance().get(Calendar.SECOND)) {
            this.counter = Calendar.getInstance().get(Calendar.SECOND);
        } else {
            while (this.counter == Calendar.getInstance().get(Calendar.SECOND)) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.counter = Calendar.getInstance().get(Calendar.SECOND);
        }

        return this;
    }
}
