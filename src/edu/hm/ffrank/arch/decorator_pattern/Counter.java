/* (C) 2012, R. Schiedermeier, rs@cs.hm.edu
 * Oracle Corporation Java 1.7.0_03, Linux i386 2.6.32.58
 * violet (Intel Core2 CPU 6600/2400 MHz, 2 Cores, 3328 MB RAM)
 **/
package edu.hm.ffrank.arch.decorator_pattern;

/**
 * Interface fuer alle Zaehler.
 *
 * @author Reinhard Schiedermeier, rs@cs.hm.edu
 * @version 2012-04-02
 */
public interface Counter {
    /**
     * Liefert den aktuellen Zaehlerstand.
     *
     * @return Aktueller Zaehlerstand.
     */
    int read();

    /**
     * Zaehlt weiter.
     *
     * @return Dieser Zaehler.
     */
    Counter tick();
}