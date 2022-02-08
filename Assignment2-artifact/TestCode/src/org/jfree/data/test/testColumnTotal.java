package org.jfree.data.test;

import static org.junit.Assert.*;



import org.jfree.data.DataUtilities;
import org.junit.*;
import org.jmock.*;
import org.jfree.data.Values2D;


public class testColumnTotal extends DataUtilities {
	
	private Mockery mocking;
	Values2D values;
	
	
	@Before
	public void setUp() throws Exception {
	mocking = new Mockery();
	values=mocking.mock(Values2D.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testValidParameters() {
		mocking.checking(new Expectations() {
				{
				 one(values).getColumnCount();
				 will(returnValue(2));
				 one(values).getRowCount();
				 will(returnValue(2));
				 one(values).getValue(0, 0);
				 will(returnValue(1.1));
				 one(values).getValue(1, 0);
				 will(returnValue(2.2));
				 
					
				}
	});
		double calc=DataUtilities.calculateColumnTotal(values, 0);
		
		assertEquals(calc,1.1+2.2,0);
}
	@Test 
	public void testBoundary() {
		mocking.checking(new Expectations() {
			{
				one(values).getColumnCount();
				will(returnValue(2));
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getValue(0, 1);
				will(returnValue(3.0));
				one(values).getValue(1, 1);
				will(returnValue(3.0));
			}
		
	});
		
		double calcMe=DataUtilities.calculateColumnTotal(values, 1);
		assertEquals(calcMe,6.0,0);
}

@Test(expected = IndexOutOfBoundsException.class)
public void testOutsideBoundary() {
	mocking.checking(new Expectations() {
		{
			one(values).getColumnCount();
			will(returnValue(1));
			one(values).getRowCount();
			will(returnValue(1));
			one(values).getValue(0,4);
			
			will(throwException(new IndexOutOfBoundsException("column is out of bounds")));
			
		}
	});
	double errorBad=DataUtilities.calculateColumnTotal(values, 4);
	
}


@Test(expected = IndexOutOfBoundsException.class)
public void testNegative() {
	mocking.checking(new Expectations() {
		{
			one(values).getColumnCount();
			will(returnValue(1));
			one(values).getRowCount();
			will(returnValue(1));
			one(values).getValue(0,-1);
			
			will(throwException(new IndexOutOfBoundsException("column is out of bounds")));
			
		}
	});
	double errorBad2=DataUtilities.calculateColumnTotal(values, -1);
	
}



@Test(expected = Exception.class)
public void testEmpty() {
	mocking.checking(new Expectations() {
		{
			one(values).getColumnCount();
			will(returnValue(1));
			one(values).getRowCount();
			will(returnValue(1));
			one(values).getValue(0,0);
			
			will(throwException(new NullPointerException("column is out of bounds")));
			
		}
	});
	double d=DataUtilities.calculateColumnTotal(values, 0);
	//assertEquals(0,d,0);
	
}

@Test(expected = Exception.class)
public void nullColTest() {
	double null1=DataUtilities.calculateColumnTotal(null, 0);
}




}
	
	
