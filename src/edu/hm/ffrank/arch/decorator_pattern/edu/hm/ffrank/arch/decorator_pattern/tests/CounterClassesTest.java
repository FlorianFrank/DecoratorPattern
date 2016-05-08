/**
 * Organisation: Hochschule München
 * Fach: Rechnerarchitektur
 * System: Macbook Pro Mitte 2014 (Intel Core i5 2x 2,6 Ghz, 8GB RAM, SSD)
 * Java: Version 1.8
 *
 * @version 07.05.2016
 * @author Florian Frank, Alioun Diagne
 */
package edu.hm.ffrank.arch.decorator_pattern.edu.hm.ffrank.arch.decorator_pattern.tests;



import edu.hm.ffrank.arch.decorator_pattern.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.Parameters;

/**
 * Testclass for all  Counterclasses
 *
 */
@RunWith(Parameterized.class)
public class CounterClassesTest {

    @Parameterized.Parameter()
    private Integer numberSystem;
    @Parameterized.Parameter(1)
    private Integer tick0;
    @Parameterized.Parameter(2)
    private Integer tick1;
    @Parameterized.Parameter(3)
    private Integer tick2;
    @Parameterized.Parameter(4)
    private Integer tick3;
    @Parameterized.Parameter(5)
    private Integer tick4;
    @Parameterized.Parameter(6)
    private Integer tick5;
    @Parameterized.Parameter(7)
    private Integer tick6;
    @Parameterized.Parameter(8)
    private Integer tick7;
    @Parameterized.Parameter(9)
    private Integer tick8;
    @Parameterized.Parameter(10)
    private Integer tick9;

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
        loopCounter.tick();
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
        naryCounter.tick();
        naryCounter1.tick();
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
    public void printCounterTest() {
        LoopCounter loopCounter = new LoopCounter(1, 2, 3);
        NaryCounter naryCounter = new NaryCounter(numberSystem);
        ClockSecondCounter clockSecondCounter = new ClockSecondCounter();

        PrintCounter loopPrintCounter = new PrintCounter(loopCounter, 'a');
        PrintCounter naryPrintCounter = new PrintCounter(naryCounter, 'a');
        PrintCounter clockSecondPrintCounter = new PrintCounter(clockSecondCounter, 'a');

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        int loopCount = 0;
        int exp = 0;
        int narycount = 0;
        for (int i = 0; i < 50; i++) {
            if (loopCount == 3) {
                loopCount = 0;
            }
            loopCount++;
            loopPrintCounter.tick();
            assertEquals(loopCount + "a", outContent.toString());
            outContent.reset();

            naryPrintCounter.tick();
            assertEquals((exp + narycount) + "a", outContent.toString());
            outContent.reset();
            if (narycount == numberSystem - 1) {
                exp += 10;
                narycount = 0;
            } else {
                narycount++;
            }
        }

        clockSecondPrintCounter.tick();
        assertEquals((Calendar.getInstance().get(Calendar.SECOND) - 1) + "a", outContent.toString());
    }

    @Test
    public void jumpCounterTest() {
        LoopCounter loopCounter = new LoopCounter(1, 2, 3);
        NaryCounter naryCounter = new NaryCounter(numberSystem);
        NaryCounter compareNaryCounter = new NaryCounter(numberSystem);
        ClockSecondCounter clockSecondCounter = new ClockSecondCounter();
        UCounter uCounter = new UCounter();

        JumpCounter uJumpCounter = new JumpCounter(uCounter, 2);
        JumpCounter loopJumpCounter = new JumpCounter(loopCounter, 2);
        JumpCounter naryJumpCounter = new JumpCounter(naryCounter, 3);
        JumpCounter clockSecondJumpCounter = new JumpCounter(clockSecondCounter, 3);

        assertEquals(0, uJumpCounter.read());
        uJumpCounter.tick();
        assertEquals(2, uJumpCounter.read());
        uJumpCounter.tick();
        assertEquals(4, uJumpCounter.read());
        uJumpCounter.tick();
        assertEquals(6, uJumpCounter.read());

        assertEquals(1, loopJumpCounter.read());
        loopJumpCounter.tick();
        assertEquals(3, loopJumpCounter.read());
        loopJumpCounter.tick();
        assertEquals(2, loopJumpCounter.read());
        loopJumpCounter.tick();
        assertEquals(1, loopJumpCounter.read());
        loopJumpCounter.tick();
        assertEquals(3, loopJumpCounter.read());
        loopJumpCounter.tick();
        assertEquals(2, loopJumpCounter.read());

        Calendar cal = Calendar.getInstance();
        assertEquals(cal.get(Calendar.SECOND), clockSecondJumpCounter.read());
        clockSecondJumpCounter.tick();
        cal.add(Calendar.SECOND, 3);
        assertEquals(cal.get(Calendar.SECOND), clockSecondJumpCounter.read());
        clockSecondJumpCounter.tick();
        cal.add(Calendar.SECOND, 3);
        assertEquals(cal.get(Calendar.SECOND), clockSecondJumpCounter.read());
        clockSecondJumpCounter.tick();
        cal.add(Calendar.SECOND, 3);
        assertEquals(cal.get(Calendar.SECOND), clockSecondJumpCounter.read());

        assertEquals(compareNaryCounter.read(), naryJumpCounter.read());
        naryJumpCounter.tick();
        assertEquals(compareNaryCounter.tick().tick().tick().read(), naryJumpCounter.read());
        naryJumpCounter.tick();
        assertEquals(compareNaryCounter.tick().tick().tick().read(), naryJumpCounter.read());
        naryJumpCounter.tick();
        assertEquals(compareNaryCounter.tick().tick().tick().read(), naryJumpCounter.read());
    }

    @Test(expected = IllegalArgumentException.class)
    public void jumpCounterArgumentTest() {
        UCounter uCounter = new UCounter();
        JumpCounter jumpCounter = new JumpCounter(uCounter, -1);
        jumpCounter.tick();
    }

    @Test
    public void limitedCounterTest() {
        UCounter ucounter = new UCounter();
        LoopCounter loopCounter = new LoopCounter(1, 2, 3);
        NaryCounter naryCounter = new NaryCounter(3);
        ClockSecondCounter clockSecondCounter = new ClockSecondCounter();

        LimitedCounter limitedUCounter = new LimitedCounter(ucounter, 10);
        LimitedCounter limitedLoopCounter = new LimitedCounter(loopCounter, 2);
        LimitedCounter limitedNaryCounter = new LimitedCounter(naryCounter, 10);
        LimitedCounter limitedClockSecondCounter = new LimitedCounter(clockSecondCounter, 30);

        for (int i = 0; i < 20; i++) {
            if (i < 11) {
                assertEquals(i, limitedUCounter.read());
            } else {
                assertEquals(10, limitedUCounter.read());
            }
            limitedUCounter.tick();
        }

        for (int i = 1; i < 20; i++) {
            if (i % 3 == 0) {
                assertEquals(2, limitedLoopCounter.read());
            } else {
                assertEquals(loopCounter.read(), limitedLoopCounter.read());
            }
            limitedLoopCounter.tick();
        }

        assertEquals(0, limitedNaryCounter.read());
        limitedNaryCounter.tick();
        assertEquals(1, limitedNaryCounter.read());
        limitedNaryCounter.tick();
        assertEquals(2, limitedNaryCounter.read());
        limitedNaryCounter.tick();
        assertEquals(10, limitedNaryCounter.read());
        limitedNaryCounter.tick();
        assertEquals(10, limitedNaryCounter.read());
        limitedNaryCounter.tick();
        assertEquals(10, limitedNaryCounter.read());

        for (int i = 0; i < 10; i++) {
            if (Calendar.getInstance().get(Calendar.SECOND) <= 30) {
                assertEquals(Calendar.getInstance().get(Calendar.SECOND), limitedClockSecondCounter.read());
            } else {
                assertEquals(30, limitedClockSecondCounter.read());
            }
            limitedClockSecondCounter.tick();
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void limitedCounterArgumentTest() {
        UCounter uCounter = new UCounter();
        LimitedCounter limitedCounter = new LimitedCounter(uCounter, -2);
        limitedCounter.tick();
    }

    @Test
    public void multiCounterTest() {
        UCounter uCounter = new UCounter();
        LoopCounter loopCounter = new LoopCounter(1, 2, 3);
        NaryCounter naryCounter = new NaryCounter(3);
        MultiCounter uMultiCounter = new MultiCounter(uCounter, 3);
        for (int i = 0; i < 10; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                assertEquals(i, uMultiCounter.read());
                uMultiCounter.tick();
            }
        }

        MultiCounter loopMultiCounter = new MultiCounter(loopCounter, 3);
        for (int i = 0; i < 10; i++) {
            for (int i2 = 0; i2 < 3; i2++) {
                if (i % 3 == 0) {
                    assertEquals(3, loopMultiCounter.read());
                } else if (i % 2 == 0) {
                    assertEquals(2, loopMultiCounter.read());
                } else {
                    assertEquals(1, loopMultiCounter.read());
                }
                loopMultiCounter.tick();
            }
        }

        MultiCounter naryMultiCounter = new MultiCounter(naryCounter, 3);
        assertEquals(0, naryMultiCounter.read());
        naryMultiCounter.tick();
        assertEquals(0, naryMultiCounter.read());
        naryMultiCounter.tick();
        assertEquals(0, naryMultiCounter.read());
        naryMultiCounter.tick();
        assertEquals(1, naryMultiCounter.read());
        naryMultiCounter.tick();
        assertEquals(1, naryMultiCounter.read());
        naryMultiCounter.tick();
        assertEquals(1, naryMultiCounter.read());
        naryMultiCounter.tick();
        assertEquals(2, naryMultiCounter.read());
        naryMultiCounter.tick();
        assertEquals(2, naryMultiCounter.read());
        naryMultiCounter.tick();
        assertEquals(2, naryMultiCounter.read());
        naryMultiCounter.tick();
        assertEquals(10, naryMultiCounter.read());
        naryMultiCounter.tick();
        assertEquals(10, naryMultiCounter.read());
        naryMultiCounter.tick();
        assertEquals(10, naryMultiCounter.read());
        naryMultiCounter.tick();

    }

    @Test(expected = IllegalArgumentException.class)
    public void multiCounterArgumentTest() {
        UCounter uCounter = new UCounter();
        MultiCounter multiCounter = new MultiCounter(uCounter, 0);
        multiCounter.tick();
    }

    @Test
    public void seletedCounterTest() {
        LoopCounter loopCounter = new LoopCounter(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        SelectedCounter selectedLoopCounter = new SelectedCounter(loopCounter, n -> n % 3 == 1);
        assertEquals(1, selectedLoopCounter.read());
        selectedLoopCounter.tick();
        assertEquals(4, selectedLoopCounter.read());
        selectedLoopCounter.tick();
        assertEquals(7, selectedLoopCounter.read());
        selectedLoopCounter.tick();
        assertEquals(10, selectedLoopCounter.read());

        UCounter uCounter = new UCounter();
        SelectedCounter selectedUcounter = new SelectedCounter(uCounter, n -> n % 2 == 0);
        assertEquals(0, selectedUcounter.read());
        selectedUcounter.tick();
        assertEquals(2, selectedUcounter.read());
        selectedUcounter.tick();
        assertEquals(4, selectedUcounter.read());
        selectedUcounter.tick();
        assertEquals(6, selectedUcounter.read());
        selectedUcounter.tick();
        assertEquals(8, selectedUcounter.read());


    }

    @Test(expected = NullPointerException.class)
    public void selectedCounterArgumentTest() {
        LoopCounter loopCounter = new LoopCounter(1, 2, 3);
        SelectedCounter selectedCounter = new SelectedCounter(loopCounter, null);
        selectedCounter.tick();
    }


}
