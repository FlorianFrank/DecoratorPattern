/**
 * Organisation: Hochschule München
 * Fach: Rechnerarchitektur
 * System: Macbook Pro Mitte 2014 (Intel Core i5 2x 2,6 Ghz, 8GB RAM, SSD)
 * Java: Version 1.8
 *
 * @version 07.05.2016
 * @author Florian Frank, Alioun Diagne
 */

import edu.hm.ffrank.arch.decorator_pattern.*;

/**
 * Mainclass.
 */
public class Main {
    public static void main(String[] args) {
        UCounter uCounter = new UCounter();
        ShiftedCounter shiftedCounter = new ShiftedCounter(uCounter,2);
        System.out.println(shiftedCounter.read());
        System.out.println(shiftedCounter.tick().read());
        System.out.println(shiftedCounter.tick().read());
        System.out.println(shiftedCounter.tick().read());
        System.out.println(shiftedCounter.tick().read());
    }
}
