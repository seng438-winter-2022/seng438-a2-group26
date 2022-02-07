package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class getLowerBoundTest{
	private Range tester;
	
	@Before
	public void setUp() throws Exception {
		tester=new Range(3,8);
	}

	@After
	public void tearDown() throws Exception {
		tester=null;
	}

	@Test
	public void testPositiveBoth() {
		assertEquals(3,tester.getLowerBound(),0);
	}
	@Test
	public void testNegativeNumber() {
		tester=new Range(-4,-1);
		assertEquals(-4,tester.getLowerBound(),0);
	}	
	@Test
	public void testZero() {
		tester = new Range(0,0);
		assertEquals(0,tester.getLowerBound(),0);
	}
	@Test 
	public void testBigValue() 
	{
		tester=new Range(Double.MAX_VALUE-1,Double.MAX_VALUE);
		assertEquals(Double.MAX_VALUE-1,tester.getLowerBound(),0);
	}
	@Test
	public void testSmallValue()
	{
		tester = new Range(Double.MIN_VALUE,8);
		assertEquals(Double.MIN_VALUE,tester.getLowerBound(),0);
	}
	
	@Test(expected=Exception.class)
	public void flipFlop()
	{
		tester=new Range(5,-1);
		tester.getLowerBound();
	}
	
	@Test(expected=Exception.class)
	public void nullRange()
	{
		tester=null;
		tester.getLowerBound();
	}
}
