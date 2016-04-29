import edu.hm.ffrank.arch.decorator_pattern.*;

/**
 * Created by florianfrank on 28.04.16.
 */
public class Main {
    public static void main(String[] args){
    NaryCounter naryCounter = new NaryCounter(9);
       for(int i=0;i<10;i++){
           System.out.println(naryCounter.read());
           naryCounter.tick();
       }







    }
}
