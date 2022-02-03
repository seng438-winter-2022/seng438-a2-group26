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
	public void testB1GreaterThanEqualsB0WithinRange() {
		assertTrue(testee.intersects(4,5));
	}
	@Test
	public void testB1GreaterThanUpper() {
		assertTrue(testee.intersects(4,9.5));		
	}
	@Test
	public void testB0LessThanLower() {
		assertTrue(testee.intersects(1.5,5));
	}
	@Test
	public void testArgRangeLargerThanCurrentRange() {
		assertTrue(testee.intersects(-1,10));
	}
	@Test
	public void testB1EqualsUpperAndB0EqualsLower() {
		assertTrue(testee.intersects(3,7));
	}
	@Test
	public void testB1EqualsUpperB0GreaterThanLower() {
		assertTrue(testee.intersects(5,7));
	}
	@Test
	public void testUpperGreaterThanB1AndB0EqualsLower() {
		assertTrue(testee.intersects(3,4));
	}
	@Test
	public void testAllEqualEachOther() {
		testee=new Range(1,1);
		assertFalse(testee.intersects(1,1));
	}
	@Test
	public void testB0GreaterThanB1() {
		assertFalse(testee.intersects(10,4));
	}
	@Test
	public void testB1NaN() {
		assertFalse(testee.intersects(0,Double.NaN));
	}
	@Test
	public void testB0NaN() {
		assertFalse(testee.intersects(Double.NaN,100));
	}
	@Test
	public void testBothNaN() {
		assertFalse(testee.intersects(Double.NaN,Double.NaN));
	}

}
