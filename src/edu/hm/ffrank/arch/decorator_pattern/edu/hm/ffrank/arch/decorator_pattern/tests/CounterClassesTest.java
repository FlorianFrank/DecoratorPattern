package edu.hm.ffrank.arch.decorator_pattern.edu.hm.ffrank.arch.decorator_pattern.tests;

import edu.hm.ffrank.arch.decorator_pattern.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameters;

/**
 * Testclass for all elementary Counterclasses
 *
 * @author florianfrank
 */
@RunWith(Parameterized.class)
public class CounterClassesTest {

    @Parameterized.Parameter(0)
    public Integer numberSystem;
    @Parameterized.Parameter(1)
    public Integer tick0;
    @Parameterized.Parameter(2)
    public Integer tick1;
    @Parameterized.Parameter(3)
    public Integer tick2;
    @Parameterized.Parameter(4)
    public Integer tick3;
    @Parameterized.Parameter(5)
    public Integer tick4;
    @Parameterized.Parameter(6)
    public Integer tick5;
    @Parameterized.Parameter(7)
    public Integer tick6;
    @Parameterized.Parameter(8)
    public Integer tick7;
    @Parameterized.Parameter(9)
    public Integer tick8;
    @Parameterized.Parameter(10)
    public Integer tick9;

    @Parameters
    public static List<Integer[]> data() {
        Integer[][] testData = new Integer[][]{
                {2, 0, 1, 10, 11, 20, 21, 30, 31, 40, 41},
                {3, 0, 1, 2, 10, 11, 12, 20, 21, 22, 30},
                {4, 0, 1, 2, 3, 10, 11, 12, 13, 20, 21},
                {5, 0, 1, 2, 3, 4, 10, 11, 12, 13, 14},
                {6, 0, 1, 2, 3, 4, 5, 10, 11, 12, 13},
                {7, 0, 1, 2, 3, 4, 5, 6, 10, 11, 12},
                {8, 0, 1, 2, 3, 4, 5, 6, 7, 10, 11},
                {9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 10}};
        return Arrays.asList(testData);
    }

    @Test
    public void loopcounterOrderTest() {

        int[] array = new int[10];
        for (int i = 0; i < 10; i++) {
            array[i] = i;
        }

        LoopCounter loopCounter = new LoopCounter(array);

        for (int i = 0; i < 1000; i++) {
            for (int i2 = 0; i2 < 10; i2++) {
                assertEquals(loopCounter.read(), array[i2]);
                loopCounter.tick();
            }
        }

    }

    @Test(expected = NoSuchElementException.class)
    public void loopCounterArgumentTest() {
        int[] array = new int[0];
        LoopCounter loopCounter = new LoopCounter(array);

    }

    @Test
    public void naryCounterTest() {
        NaryCounter naryCounter = new NaryCounter(numberSystem);
        assertEquals(naryCounter.read(), (int) tick0);
        naryCounter.tick();
        assertEquals(naryCounter.read(), (int) tick1);
        naryCounter.tick();
        assertEquals(naryCounter.read(), (int) tick2);
        naryCounter.tick();
        assertEquals(naryCounter.read(), (int) tick3);
        naryCounter.tick();
        assertEquals(naryCounter.read(), (int) tick4);
        naryCounter.tick();
        assertEquals(naryCounter.read(), (int) tick5);
        naryCounter.tick();
        assertEquals(naryCounter.read(), (int) tick6);
        naryCounter.tick();
        assertEquals(naryCounter.read(), (int) tick7);
        naryCounter.tick();
        assertEquals(naryCounter.read(), (int) tick8);
        naryCounter.tick();
        assertEquals(naryCounter.read(), (int) tick9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void naryCounterArgumentTest() {
        NaryCounter naryCounter = new NaryCounter(1);
        NaryCounter naryCounter1 = new NaryCounter(10);
    }

    @Test
    public void clockSecondCounterTest() {
        int counter;
        ClockSecondCounter clockSecondCounter = new ClockSecondCounter();
        counter = clockSecondCounter.read();
        for (int i = 0; i < 5; i++) {
            assertEquals(clockSecondCounter.read(), counter);
        }
        clockSecondCounter.tick();
        assertEquals(clockSecondCounter.read(), Calendar.getInstance().get(Calendar.SECOND));
    }

    @Test
    public void PrintCounterTest(){
        LoopCounter loopCounter = new LoopCounter(1,2,3);
        NaryCounter naryCounter = new NaryCounter(numberSystem);
        ClockSecondCounter clockSecondCounter = new ClockSecondCounter();

        PrintCounter loopPrintCounter = new PrintCounter(loopCounter,'a');
        PrintCounter naryPrintCounter = new PrintCounter(naryCounter,'a');
        PrintCounter clockSecondPrintCounter = new PrintCounter(clockSecondCounter,'a');

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        int loopCount= 0;
        int exp = 0;
        int narycount=0;
        for(int i=0;i<50;i++){
            if(loopCount==3){
                loopCount = 0;
            }
            loopCount++;
            loopPrintCounter.tick();
            assertEquals(loopCount+"a",outContent.toString());
            outContent.reset();

            naryPrintCounter.tick();
            assertEquals((exp+narycount)+"a",outContent.toString());
            outContent.reset();
            if(narycount==numberSystem-1){
                exp+=10;
                narycount=0;
            }else {
                narycount++;
            }
        }

        clockSecondPrintCounter.tick();
        assertEquals((Calendar.getInstance().get(Calendar.SECOND)-1)+"a",outContent.toString());
    }

    @Test
    public void jumpCounterTest(){
        LoopCounter loopCounter = new LoopCounter(1,2,3);
        NaryCounter naryCounter = new NaryCounter(numberSystem);
        ClockSecondCounter clockSecondCounter = new ClockSecondCounter();
        UCounter uCounter = new UCounter();

        JumpCounter uJumpCounter = new JumpCounter(uCounter,2);
        JumpCounter loopJumpCounter = new JumpCounter(loopCounter,3);
        JumpCounter naryJumpCounter = new JumpCounter(naryCounter,3);
        JumpCounter clockSecondJumpCounter = new JumpCounter(clockSecondCounter,3);

            assertEquals(0,uJumpCounter.read());
            uJumpCounter.tick();
            assertEquals(2,uJumpCounter.read());
            uJumpCounter.tick();
            assertEquals(4,uJumpCounter.read());
            uJumpCounter.tick();
            assertEquals(6,uJumpCounter.read());

            assertEquals(3,loopJumpCounter.read());
            loopJumpCounter.tick();
            assertEquals(6,loopJumpCounter.read());
            loopJumpCounter.tick();
            assertEquals(9,loopJumpCounter.read());
            loopCounter.tick();
            assertEquals(3,loopJumpCounter.read());
            loopJumpCounter.tick();
            assertEquals(6,loopJumpCounter.read());
            loopJumpCounter.tick();
            assertEquals(9,loopJumpCounter.read());

            assertEquals(Calendar.getInstance().get(Calendar.SECOND)+4,clockSecondJumpCounter.read());
            clockSecondJumpCounter.tick();
            assertEquals(Calendar.getInstance().get(Calendar.SECOND)+4,clockSecondJumpCounter.read());
            clockSecondJumpCounter.tick();
            assertEquals(Calendar.getInstance().get(Calendar.SECOND)+4,clockSecondJumpCounter.read());
            clockSecondJumpCounter.tick();
            assertEquals(Calendar.getInstance().get(Calendar.SECOND)+4,clockSecondJumpCounter.read());




    }
}
