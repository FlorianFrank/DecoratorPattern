package edu.hm.ffrank.arch.decorator_pattern;

/**
 * Created by florianfrank on 28.04.16.
 */
public class ShiftedCounter implements Counter {

    private Counter deliveredCounter;
    private int step;
    private int counter;

    public ShiftedCounter(Counter counter, int step){
        if(step<0 ){
            throw new IllegalArgumentException();
        }else {
            this.deliveredCounter = counter;
            this.step = step;
            this.counter = this.deliveredCounter.read()+step;
        }

    }

    @Override
    public int read() {
        return this.counter;
    }

    @Override
    public Counter tick() {
        this.counter = this.deliveredCounter.tick().read()+step;
        return this;
    }
}
