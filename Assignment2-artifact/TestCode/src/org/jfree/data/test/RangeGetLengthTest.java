package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

//RangeGetLengthTest.java

public class RangeGetLengthTest {
	private Range rangeUnderTest;
	private double upperBound;
	private double lowerBound;
	private double expected;
	//DELTA variable for assertEquals; allows 1e-15 difference between expected and actual
	private static final double DELTA = 1e-15;
	
	@Before
	public void setUp() throws Exception {
		upperBound = 0.0;
		lowerBound = 0.0;
		expected = upperBound - lowerBound;
		rangeUnderTest = new Range(upperBound, lowerBound);
	}

	@After
	public void tearDown() throws Exception {
		rangeUnderTest = null;
	}

	/**
	 * Test that a Range with both zero boundaries has length of zero returned
	 */
	@Test
	public void testBothBoundariesZero() {
		
		assertEquals(expected, rangeUnderTest.getLength(), DELTA);
	}
	
	/**
	 * Test when lowerBound<upperBound. This is a valid test case that should return
	 * upperBound-lowerBound as the length from getLength()
	 */
	@Test
	public void testLowerBoundaryLessThanUpper() {
		lowerBound = 3.5;
		upperBound = 6.0;
		expected = upperBound-lowerBound;
		rangeUnderTest = new Range(lowerBound, upperBound);
		assertEquals(expected, rangeUnderTest.getLength(), DELTA);
	}
	
	/**
	 * Test when lowerBound==upperBound. getLength() should return 0.0 (upper-lower)
	 */
	@Test
	public void testLowerBoundaryEqualsUpperBoundary() {
		lowerBound = 17.5;
		upperBound = lowerBound;
		expected = upperBound-lowerBound;
		rangeUnderTest = new Range(lowerBound,upperBound);
		assertEquals(expected, rangeUnderTest.getLength(), DELTA);
	}
	
	/**
	 * Test when lowerBound>upperBound, which is invalid according to getLength's requirements
	 * Asserts that the getLength() method throws IllegalArgumentException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testLowerBoundaryGreaterThanUpper() {
		lowerBound = 10.0;
		upperBound = 5.0;
		rangeUnderTest = new Range(lowerBound, upperBound);
		rangeUnderTest.getLength();
		//assertEquals(0.0, rangeUnderTest.getLength(), DELTA);
	}

	/**
	 * Test getLength() on NULL Range. Expects NullPointerException to be thrown by getLength()
	 */
	@Test(expected = NullPointerException.class)
	public void testNull() {
		rangeUnderTest = null;
		rangeUnderTest.getLength();
		
	}
	

	/**
	 * Test when one boundary is negative. Should return upper-lower
	 */
	@Test
	public void testOneNegativeBound() {
		lowerBound = -5.0;
		upperBound = 3.0;
		expected = upperBound-lowerBound;
		rangeUnderTest = new Range(lowerBound, upperBound);
		assertEquals(expected, rangeUnderTest.getLength(), DELTA);
	}
	
	/**
	 * Test when both numbers are negative. Should return upper-lower
	 */
	@Test
	public void testBothNegative() {
		lowerBound = -6.75;
		upperBound = -3.25;
		expected = upperBound - lowerBound;
		rangeUnderTest = new Range(lowerBound, upperBound);
		assertEquals(expected, rangeUnderTest.getLength(), DELTA);
	}
	
	/**
	 * Testing the getLength() can handle the max double size as one of the boundaries.
	 */
	@Test
	public void testLargePositive() {
		lowerBound = 0.0;
		upperBound = Double.MAX_VALUE;
		expected = upperBound - lowerBound;
		rangeUnderTest = new Range(lowerBound, upperBound);
		assertEquals(expected, rangeUnderTest.getLength(), DELTA);
	}

}


