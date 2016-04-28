import edu.hm.ffrank.arch.decorator_pattern.ClockSecondCounter;
import edu.hm.ffrank.arch.decorator_pattern.LoopCounter;
import edu.hm.ffrank.arch.decorator_pattern.NaryCounter;
import edu.hm.ffrank.arch.decorator_pattern.PrintCounter;

/**
 * Created by florianfrank on 28.04.16.
 */
public class Main {
    public static void main(String[] args){
    NaryCounter naryCounter = new NaryCounter(3);
        PrintCounter printCounter = new PrintCounter(naryCounter,'\n');
        printCounter.tick().tick().tick();




    }
}
