package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

import org.junit.Test;

public class RangeTestIntersectsDouble {
	private static Range testee;
	
	@BeforeClass
	public static void setup() {
		testee=new Range(3,7);
	}
	@After
	public void reset() {
		testee=new Range(3,7);
	}
	@AfterClass
	public static void teardown() {
		testee=null;
	}

	@Test
	//upper>b1>=b0>lower
	public void testB1GreaterThanEqualsB0WithinRange() {
		assertTrue(testee.intersects(4,5));
	}
	@Test
	//b1>upper>b0>lower
	public void testB1GreaterThanUpper() {
		assertTrue(testee.intersects(4,9.5));		
	}
	@Test
	//upper>b1>lower>b0
	public void testB0LessThanLower() {
		assertTrue(testee.intersects(1.5,5));
	}
	@Test
	//b1>upper>=lower>b0
	public void testArgRangeLargerThanCurrentRange() {
		assertTrue(testee.intersects(-1,10));
	}
	@Test
	//b1=upper>lower=b0
	public void testB1EqualsUpperAndB0EqualsLower() {
		assertTrue(testee.intersects(3,7));
	}
	@Test
	//b1=upper>b0>lower
	public void testB1EqualsUpperB0GreaterThanLower() {
		assertTrue(testee.intersects(5,7));
	}
	@Test
	//upper>b1>lower=b0
	public void testUpperGreaterThanB1AndB0EqualsLower() {
		assertTrue(testee.intersects(3,4));
	}
	@Test
	//b1>=b0>upper>lower
	public void testArgRangeDoesNotIntersectFromRight() {
		assertFalse(testee.intersects(100,200));
	}
	@Test
	//upper>lower>b1>=b0
	public void testArgRangeDoesNotIntersectFromLeft() {
		assertFalse(testee.intersects(-500,-16));
	}
	@Test
	//b1=upper=lower=b0
	public void testAllEqualEachOther() {
		testee=new Range(1,1);
		assertFalse(testee.intersects(1,1));
	}
	
	@Test
	//b0>b1
	public void testB0GreaterThanB1() {
		assertFalse(testee.intersects(10,4));
	}
	@Test
	//b1 is Double.NaN, b0 is valid
	public void testB1NaN() {
		assertFalse(testee.intersects(0,Double.NaN));
	}
	@Test
	//b0 is Double.NaN, b1 is valid
	public void testB0NaN() {
		assertFalse(testee.intersects(Double.NaN,100));
	}
	@Test
	//both b1 and b0 are Double.NaN
	public void testBothNaN() {
		assertFalse(testee.intersects(Double.NaN,Double.NaN));
	}
	@Test
	//both b0 and b1 are -Infinity
	public void testBothNegativeInf() {
		assertFalse(testee.intersects(Double.NEGATIVE_INFINITY,Double.NEGATIVE_INFINITY));
	}
	@Test
	//both b0 and b1 are +Infinity
	public void testBothPositiveInf() {
		assertFalse(testee.intersects(Double.POSITIVE_INFINITY,Double.POSITIVE_INFINITY));
	}
	@Test
	//b1 is +Infinity, b0 is -Infinity
	public void testMaxArgRange() {
		assertTrue(testee.intersects(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY));
	}

}
