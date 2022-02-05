package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;


public class RangeConstrainTest {
	private static Range range;

    @BeforeClass
    public static void setup () {
        range = new Range (-2, 12);
    }

    @AfterClass
    public static void teardown () {
        range = null;
    }

    @Test
    public void testRangePositive () {
        double res = range.constrain(5);
        assertEquals (5.0, res, 0);
    }

    @Test
    public void testRangeNegative () {
        double res = range.constrain(-1);
        assertEquals (-1.0, res, 0);
    }
    
    @Test
    public void testRangeOutOfRangeAbove () {
        double res = range.constrain(154);
        assertEquals (12.0, res, 0);
    }
    
    @Test
    public void testRangeOutOfRangeBelow () {
        double res = range.constrain(-62);
        assertEquals (-2.0, res, 0);
    }
    
    @Test
    public void testRangeBoundaryUpper () {
        double res = range.constrain(12);
        assertEquals (12.0, res, 0);
    }
    
    @Test
    public void testRangeBoundaryLower () {
        double res = range.constrain(-2);
        assertEquals (-2.0, res, 0);
    }
}
