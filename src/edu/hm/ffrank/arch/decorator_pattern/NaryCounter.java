package edu.hm.ffrank.arch.decorator_pattern;

import java.util.IllegalFormatCodePointException;

/**
 * Created by florianfrank on 28.04.16.
 */
public class NaryCounter implements Counter {

    int base;
    int counter;
    int pow;

    public NaryCounter(int base){
        if(base> 1 && base >9){
            throw new IllegalArgumentException();
        }
        this.base = base;
        this.pow = 0;
    }

    @Override
    public int read() {
        return  pow+this.counter;
    }

    @Override
    public Counter tick() {
        if(this.counter == this.base-1){
            this.counter=0;
            this.pow+=10;
        }else {
            counter++;
        }
        return this;
    }
}
