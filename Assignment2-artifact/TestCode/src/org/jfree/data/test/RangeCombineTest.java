package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;


public class RangeCombineTest {
	
	@Test
	public void testRangesFullyOverlap() {
		Range R1=new Range(0,1);
		Range R2=new Range(0,1);
		Range expected= new Range(0,1);
		Range actual= Range.combine(R2, R1);
		assertTrue(expected.equals(actual));
	}
	@Test
	public void testRangesPartiallyOverlap() {
		Range R1=new Range(0,6);
		Range R2=new Range(3,10);
		Range expected= new Range(0,10);
		Range actual= Range.combine(R2, R1);
		assertTrue(expected.equals(actual));
	}
	@Test
	public void testRangesBoundaryOverlap() {
		Range R1=new Range(0,1.1);
		Range R2=new Range(1.1,2);
		Range expected= new Range(0,2);
		Range actual= Range.combine(R2, R1);
		assertTrue(expected.equals(actual));
	}
	@Test
	public void testRangesNoOverlap() {
		Range R1=new Range(0,1);
		Range R2=new Range(10,15);
		Range expected= new Range(0,15);
		Range actual= Range.combine(R2, R1);
		assertTrue(expected.equals(actual));
	}
	@Test
	public void testRangesFullyNegative() {
		Range R1=new Range(-16,-4);
		Range R2=new Range(-7,1);
		Range expected= new Range(-16,1);
		Range actual= Range.combine(R2, R1);
		assertTrue(expected.equals(actual));
	}
	@Test
	public void testRangesPartiallyNegative() {
		Range R1=new Range(-3,1);
		Range R2=new Range(0,7);
		Range expected= new Range(-3,7);
		Range actual= Range.combine(R2, R1);
		assertTrue(expected.equals(actual));
	}
	@Test
	public void testNullRange1() {
		Range R1=null;
		Range R2=new Range(0,1);
		Range expected= new Range(0,1);
		Range actual= Range.combine(R2, R1);
		assertTrue(expected.equals(actual));
	}
	@Test
	public void testNullRange2() {
		Range R1=new Range(0,1);
		Range R2=null;
		Range expected= new Range(0,1);
		Range actual= Range.combine(R2, R1);
		assertTrue(expected.equals(actual));
	}
	@Test
	public void testNullBoth() {
		Range R1=null;
		Range R2=null;
		assertNull(Range.combine(R1, R2));
	}

}
