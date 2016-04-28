import edu.hm.ffrank.arch.decorator_pattern.LoopCounter;
import edu.hm.ffrank.arch.decorator_pattern.NaryCounter;

/**
 * Created by florianfrank on 28.04.16.
 */
public class Main {
    public static void main(String[] args){
        NaryCounter loopCounter = new NaryCounter(9);
        System.out.println(loopCounter.tick().read());
        System.out.println(loopCounter.tick().read());

        System.out.println(loopCounter.tick().read());
        System.out.println(loopCounter.tick().read());

        System.out.println(loopCounter.tick().read());

        System.out.println(loopCounter.tick().read());

        System.out.println(loopCounter.tick().read());

        System.out.println(loopCounter.tick().read());
        System.out.println(loopCounter.tick().read());

        System.out.println(loopCounter.tick().read());




    }
}
