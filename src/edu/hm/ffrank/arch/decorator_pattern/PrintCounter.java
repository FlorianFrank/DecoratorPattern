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
/**
 * This class gets another counter and a symbol.
 * By calling the tick method it concat the value before calling the tick method and the symbol
 * and prints the string out.
 */
public class PrintCounter implements Counter {

    /**by constructor delivered counter.*/
    private final Counter counter;
    /**symbol which is being added to the current number.*/
    private final char symbol;

    /**
     * constructor sets counter and the symbol.
     * @param counter base counter
     * @param symbol symbol which is being added to the current number
     */
    public PrintCounter(Counter counter, char symbol) {
        if(counter == null){
            throw  new NullPointerException();
        }
        this.counter = counter;
        this.symbol = symbol;
    }

    @Override
    public int read() {
        return this.counter.read();
    }

    @Override
    public Counter tick() {
        System.out.print(this.counter.read()+ this.symbol);
        this.counter.tick();
        return this;
    }
}
