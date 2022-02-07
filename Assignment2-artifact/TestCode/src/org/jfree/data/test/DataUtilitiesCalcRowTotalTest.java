
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

	/**
	 * Test that calculating on a negative row index returns a index out of bounds exception
	 */
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
	
	/**
	 * Calculate row total on a boundary value
	 * In this case, total rows = 1, and we'll call calculate on the 0th indexed row(boundary)
	 */
	@Test
	public void calculateRowTotalOnBoundary() {
		values2DMock.checking(new Expectations() {
			{
				one(val).getColumnCount();
				will(returnValue(2));
				one(val).getRowCount();
				will(returnValue(1));
				one(val).getValue(0,0);
				will(returnValue(2.5));
				one(val).getValue(0, 1);
				will(returnValue(5.0));
			}
		});
		calcResult = DataUtilities.calculateRowTotal(val, 0);
		assertEquals(calcResult, 7.5, DELTA);
	}
	
	/**
	 * Test for a row with entirely negative values to be added properly.
	 */
	@Test
	public void calcRowTotalWithNegatives() {
		values2DMock.checking(new Expectations() {
			{
				one(val).getColumnCount();
				will(returnValue(3));
				one(val).getRowCount();
				will(returnValue(2));
				one(val).getValue(0, 0);
				will(returnValue(-2.5));
				one(val).getValue(0, 1);
				will(returnValue(-5.75));
				one(val).getValue(0, 2);
				will(returnValue(-21.3455));
				one(val).getValue(1, 0);
				will(returnValue(-1.667));
				one(val).getValue(1, 1);
				will(returnValue(-2.50));
				one(val).getValue(1, 2);
				will(returnValue(-15.5982315));
			}
		});
		calcResult = DataUtilities.calculateRowTotal(val, 1);
		double checkRes = (-1.667-2.5-15.5982315);
		assertEquals(checkRes, calcResult, DELTA);
	}
}
