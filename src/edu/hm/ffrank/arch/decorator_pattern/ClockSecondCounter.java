package edu.hm.ffrank.arch.decorator_pattern;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by florianfrank on 28.04.16.
 */
public class ClockSecondCounter implements Counter {

    private int counter;

    public ClockSecondCounter(){
        counter = Calendar.getInstance().get(Calendar.SECOND);
    }


    @Override
    public int read() {
        return this.counter;
    }

    @Override
    public Counter tick() {
       if(this.counter != Calendar.getInstance().get(Calendar.SECOND)){
           this.counter = Calendar.getInstance().get(Calendar.SECOND);
       }else{
           while (this.counter == Calendar.getInstance().get(Calendar.SECOND)){
               try {
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           this.counter = Calendar.getInstance().get(Calendar.SECOND);
        }

        return this;
    }
}
