/**
 *
 *
 * @author Florian Frank
 */
package edu.hm.ffrank.arch.decorator_pattern;

public class JumpCounter implements Counter {

    private Counter deliveredCounter;
    private int step;

    public JumpCounter(Counter deliveredCounter, int step){
        this.deliveredCounter = deliveredCounter;
        this.step = step;
    }

    @Override
    public int read() {
        return this.deliveredCounter.read()*step;
    }

    @Override
    public Counter tick() {
        this.deliveredCounter.tick();
        return this;
    }
}
