package edu.hm.ffrank.arch.decorator_pattern;

/**
 * Created by florianfrank on 28.04.16.
 */
public class MultiCounter implements Counter {

    public Counter deliveredCounter;
    public int counter;
    public int amount;
    public int currentAmount;

   public MultiCounter(Counter deliveredCounter, int amount){
        this.deliveredCounter = deliveredCounter;
       this.amount = amount;
       this.currentAmount = 0;
       this.counter = this.deliveredCounter.read();
   }

    @Override
    public int read() {
        return this.counter;
    }

    @Override
    public Counter tick() {
        if(this.currentAmount<this.amount){
            this.currentAmount++;
        }else {
            this.currentAmount=0;
            this.counter = this.deliveredCounter.tick().read();
        }
        return this;
    }
}
