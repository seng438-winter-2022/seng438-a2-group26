package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;


public class RangeConstrainTest {
	private Range range;

    @BeforeClass
    void setup () {
        range = new Range (-2, 12);
    }

    @AfterClass
    void teardown () {
        range = null;
    }

    public void testRangePositive () {
        double res = range.constrain(5);
        assertTrue (5.0, res);
    }

    public void testRangeNegative () {
        double res = range.constrain(-1);
        assertTrue (-1.0, res);
    }
    
    public void testRangeOutOfRangeAbove () {
        double res = range.constrain(154);
        assertTrue (12.0, res);
    }
    
    public void testRangeOutOfRangeBelow () {
        double res = range.constrain(-62);
        assertTrue (-2.0, res);
    }
    
    public void testRangeBoundaryUpper () {
        double res = range.constrain(12);
        assertTrue (12.0, res);
    }
    
    public void testRangeBoundaryLower () {
        double res = range.constrain(-2);
        assertTrue (-2.0, res);
    }
}
