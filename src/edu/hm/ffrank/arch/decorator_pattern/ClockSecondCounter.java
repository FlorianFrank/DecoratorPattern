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

import edu.hm.cs.rs.arch.a03_decorator.Counter;
import java.util.Calendar;

/**
 * This class contains the method read which returns the actual Seconds.
 * The method tick waits till the next second and and returns the actual time.
 */
public class ClockSecondCounter implements Counter {

    /**time to sleep after every looprun in the tick()-method.*/
    private static final int TIME_TO_SLEEP=1;
    /**counter for seconds.*/
    private int counter;

    /**
     * constructor sets counter to current seconds.
     */
    public ClockSecondCounter() {
        counter = Calendar.getInstance().get(Calendar.SECOND);
    }


    @Override
    public int read() {
        return this.counter;
    }

    @Override
    public Counter tick() {
            final int currentSecond = Calendar.getInstance().get(Calendar.SECOND);
            while (currentSecond == Calendar.getInstance().get(Calendar.SECOND)) {
                try {
                    Thread.sleep(TIME_TO_SLEEP);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
            this.counter++;
        return this;
    }
}
