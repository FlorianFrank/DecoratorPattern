package edu.hm.ffrank.arch.decorator_pattern;

import java.util.NoSuchElementException;

public class LoopCounter implements Counter {

    private int[] values;
    private int counter;

    public LoopCounter(int... values) {
        if(values.length == 0) {
            throw new NoSuchElementException();
        }
        this.values = values;
        this.counter = 0;
    }

    @Override
    public int read() {
        return values[counter];
    }

    @Override
    public Counter tick() {
        if(this.counter == (this.values.length-1)){
            this.counter = 0;
        }else{
         counter++;
        }
        return this;
    }
}
