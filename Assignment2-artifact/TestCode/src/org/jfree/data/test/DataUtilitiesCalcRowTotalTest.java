
package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.junit.*;
import org.jmock.*;
import org.jfree.data.Values2D;


public class DataUtilitiesCalcRowTotalTest extends DataUtilities {
	
	//Mockery object to mock the dependent values2D class
	private Mockery values2DMock;
	//Values2D value variable
	Values2D val;
	
	private static final double DELTA = 1e-15;
	double calcResult;

	@Before
	public void setUp() throws Exception {
		
		//call Mockery ctor for values2DMock
		values2DMock = new Mockery();
		//create values variable to mock the values2D class
		val = values2DMock.mock(Values2D.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * First mocking test to ensure two valid Row values will be properly calculated
	 * Valid values chosen were 5.5 and 4.5, giving 10.0 as the expected result return
	 */
	@Test
	public void calculateRowTotalTwoValids() {
		values2DMock.checking(new Expectations() {
			{
				
				one(val).getColumnCount();
				will(returnValue(2));
				one(val).getValue(0,0);
				will(returnValue(4.5));
				one(val).getValue(0, 1);
				will(returnValue(5.5));
			}
		});
		calcResult = DataUtilities.calculateRowTotal(val, 0);
		
		assertEquals(calcResult, 10.0, DELTA);
	}
	
	/**
	 * Test for calculating a row that is out of bounds
	 * i.e. calculateRowTotal is called on row 1 despite only row 0 existing, thus Row above Boundary
	 * Test expects IndexOutOfBoundsException that is mocked in the mocked values2d object
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void calculateRowTotalOutOfBounds() {
		values2DMock.checking(new Expectations() {
			{
				one(val).getColumnCount();
				will(returnValue(3));
				one(val).getRowCount();
				will(returnValue(1));
				one(val).getValue(0,  0);
				will(returnValue(1.0));
				one(val).getValue(0, 1);
				will(returnValue(10.5));
				one(val).getValue(0, 2);
				will(returnValue(5.5));
				
				one(val).getValue(1, 0);
				will(throwException(new IndexOutOfBoundsException("Row requested exceeds actual Rows")));
				
			}
		});
		calcResult = DataUtilities.calculateRowTotal(val, 1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void calculateRowTotalNegativeRows() {
		values2DMock.checking(new Expectations() {
			{
				one(val).getColumnCount();
				will(returnValue(1));
				one(val).getRowCount();
				will(returnValue(1));
				
				one(val).getValue(-1, 0);
				will(throwException(new IndexOutOfBoundsException("Negative row requested - Invalid!")));
				
			}
		});
		calcResult = DataUtilities.calculateRowTotal(val, -1);
	}
	
}
