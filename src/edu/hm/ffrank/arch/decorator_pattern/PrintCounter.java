package edu.hm.ffrank.arch.decorator_pattern;

/**
 * Created by florianfrank on 28.04.16.
 */
public class PrintCounter implements Counter {

    private char symbol;
    Counter counter;

    public PrintCounter(Counter counter, char symbol){
        this.counter = counter;
        this.symbol = symbol;
    }

    @Override
    public int read() {
        return this.counter.read();
    }

    @Override
    public Counter tick() {
        System.out.print(this.counter.read()+""+this.symbol);
        this.counter.tick();
        return this;
    }
}
