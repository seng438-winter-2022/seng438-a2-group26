package org.jfree.data.test;

import static org.junit.Assert.*;



import org.jfree.data.DataUtilities;
import org.junit.*;

import org.jfree.data.DataUtilities;
import org.junit.*;
import org.jmock.*;
import org.jfree.data.Values2D;

public class rowTotalWithArray extends DataUtilities {
	private Mockery mock;
	Values2D vals;
	
	@Before
	public void setUp() throws Exception {
		mock = new Mockery();
		vals = mock.mock(Values2D.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Testing valid table with validCols
	 */
	@Test
	public void allValid() {
		mock.checking(new Expectations() {
			{
				
				one(vals).getColumnCount();
				will(returnValue(2));
				one(vals).getRowCount();
				will(returnValue(2));
				one(vals).getValue(0,0);
				will(returnValue(2.5));
				one(vals).getValue(0, 1);
				will(returnValue(3.5));
				one(vals).getValue(1,0);
				will(returnValue(4.5));
				one(vals).getValue(1, 1);
				will(returnValue(5.5));
			}
		});
		int[] valids = {0,1};
		double result = DataUtilities.calculateRowTotal(vals, 0, valids);
		assertEquals(6.0, result, 0);
	}
	
	/**
	 * Test row on validCols boundary. I.E. validCols only = 1, thus test calc on row 1
	 */
	@Test 
	public void onBound() {
		mock.checking(new Expectations() {
			{
				
				one(vals).getColumnCount();
				will(returnValue(2));
				one(vals).getRowCount();
				will(returnValue(2));
				one(vals).getValue(0,0);
				will(returnValue(2.5));
				one(vals).getValue(0, 1);
				will(returnValue(3.5));
				one(vals).getValue(1,0);
				will(returnValue(4.5));
				one(vals).getValue(1, 1);
				will(returnValue(5.5));
			}
		});
		int[] valids = {1};
		double result = DataUtilities.calculateRowTotal(vals, 1, valids);
		assertEquals(5.5, result, 0);
	}
	
	/**
	 * Test to ensure calc of row sum on validCols>actual_cols results in 0
	 */
	@Test
	public void greaterValidCols() {
		mock.checking(new Expectations() {
			{
				
				one(vals).getColumnCount();
				will(returnValue(3));
				one(vals).getRowCount();
				will(returnValue(3));
				one(vals).getValue(0,0);
				will(returnValue(1.5));
				one(vals).getValue(0, 1);
				will(returnValue(5));
				one(vals).getValue(0, 2);
				will(returnValue(5.75));
				one(vals).getValue(1,0);
				will(returnValue(9.5));
				one(vals).getValue(1, 1);
				will(returnValue(7.25));
				one(vals).getValue(1,2);
				will(returnValue(8.675));
				one(vals).getValue(2,0);
				will(returnValue(5));
				one(vals).getValue(2,1);
				will(returnValue(15.55));
				one(vals).getValue(2,2);
				will(returnValue(5.875));
			}
		});
		int[] valids = {3,4};
		double result = DataUtilities.calculateRowTotal(vals, 2, valids);
		assertEquals(0, result, 0);
	}
	
	
/**
	 * CalcRow called on a negative row number. Should give an exception as its invalid.
	 */
	@Test(expected = Exception.class)
	public void negativeRowCalc() {
		mock.checking(new Expectations() {
			{
				
				one(vals).getColumnCount();
				will(returnValue(1));
				one(vals).getRowCount();
				will(returnValue(1));
				one(vals).getValue(0,0);
				will(returnValue(2.5));
				
				one(vals).getValue(-1, 0);
				will(throwException(new Exception("Illegal negative rows")));
				
			}
		});
		int[] valids = {0};
		double results = DataUtilities.calculateRowTotal(vals, -1, valids);
	}
	
	
	/**
	 * Test row calc with empty valid cols. Thus, nothing should be valid and zero should be sum
	 */
	@Test 
	public void emptyValidCols() {
		mock.checking(new Expectations() {
			{
				
				one(vals).getColumnCount();
				will(returnValue(2));
				one(vals).getRowCount();
				will(returnValue(2));
				one(vals).getValue(0,0);
				will(returnValue(2.5));
				one(vals).getValue(0, 1);
				will(returnValue(3.5));
				one(vals).getValue(1,0);
				will(returnValue(4.5));
				one(vals).getValue(1, 1);
				will(returnValue(5.5));
			}
		});
		int[] valids = {};
		double result = DataUtilities.calculateRowTotal(vals, 1, valids);
		assertEquals(0, result, 0);
	}
	
	/**
	 * Test row on validCols with negative values. Since negative columns do not exist, should
	 * pass only if exception thrown
	 */
	@Test(expected = Exception.class)
	public void negativeValidCols() {
		mock.checking(new Expectations() {
			{
				
				one(vals).getColumnCount();
				will(returnValue(2));
				one(vals).getRowCount();
				will(returnValue(2));
				one(vals).getValue(0,0);
				will(returnValue(2));
				one(vals).getValue(0, 1);
				will(returnValue(5.25));
				one(vals).getValue(1,0);
				will(returnValue(7.5));
				one(vals).getValue(1, 1);
				will(returnValue(8.25));
				
				one(vals).getValue(1, -1);
				will(throwException(new Exception("Illegal negative cols")));
			}
		});
		int[] valids = {-1};
		double result = DataUtilities.calculateRowTotal(vals, 1, valids);
		
	}
	
	/**
	 * Test with validCols and a NULL Datautilities. Expects an exception to pass!
	 */
	@Test(expected = Exception.class)
	public void nullTest() {
		int[] valids = {2,5,6};
		double result = DataUtilities.calculateRowTotal(null, 1, valids);
	}
	
	
	
	/**
	 * Test row with validCols==null. Pass if exception is thrown
	 */
	@Test(expected = Exception.class)
	public void nullValidCols() {
		mock.checking(new Expectations() {
			{
				
				one(vals).getColumnCount();
				will(returnValue(2));
				one(vals).getRowCount();
				will(returnValue(2));
				one(vals).getValue(0,0);
				will(returnValue(2));
				one(vals).getValue(0, 1);
				will(returnValue(5.25));
				one(vals).getValue(1,0);
				will(returnValue(7.5));
				one(vals).getValue(1, 1);
				will(returnValue(8.25));
				
				one(vals).getValue(1, -1);
				will(throwException(new Exception("Illegal negative cols")));
			}
		});
		int[] valids = null;
		double result = DataUtilities.calculateRowTotal(vals, 1, valids);
		
	}








}
