/**
 * Organisation: Hochschule München
 * Fach: Rechnerarchitektur
 * System: Macbook Pro Mitte 2014 (Intel Core i5 2x 2,6 Ghz, 8GB RAM, SSD)
 * Java: Version 1.8
 *
 * @version 07.05.2016
 * @author Florian Frank, Alioun Diagne
 */

import edu.hm.ffrank.arch.decorator_pattern.LoopCounter;
import edu.hm.ffrank.arch.decorator_pattern.SelectedCounter;

public class Main {
    public static void main(String[] args) {
        LoopCounter loopCounter = new LoopCounter(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        SelectedCounter selectedCounter = new SelectedCounter(loopCounter, n -> n % 3 == 1);
        System.out.println(selectedCounter.read());
        System.out.println(selectedCounter.tick().read());
        System.out.println(selectedCounter.tick().read());
        System.out.println(selectedCounter.tick().read());
        System.out.println(selectedCounter.tick().read());
        System.out.println(0 % 2);
    }
}
