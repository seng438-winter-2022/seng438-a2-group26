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
}
