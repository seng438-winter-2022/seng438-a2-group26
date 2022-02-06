package org.jfree.data.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.UnknownKeyException;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;

public class TestDataUtilitiesCumulativePercentages {
	private Mockery mockContext;
	private KeyedValues values;
	
	@Before
	public void setUp() {
		mockContext=new Mockery();
		values = mockContext.mock(KeyedValues.class);
	}
	
	@After
	public void teardown() {
		mockContext=null;
		values=null;
	}

	@Test
	//Correct sequential list
	public void testSequentialList() {
		mockContext.checking(new Expectations() {
			{
				
				allowing(values).getValue(0);
				will(returnValue(88));
				
				allowing(values).getKey(0);
				will(returnValue(0));

				allowing(values).getValue(1);
				will(returnValue(99));
				
				allowing(values).getKey(1);
				will(returnValue(1));

				allowing(values).getValue(2);
				will(returnValue(7));
				
				allowing(values).getKey(2);
				will(returnValue(2));
				
				allowing(values).getItemCount();
				will(returnValue(3));

				
			}
		});
		KeyedValues result = DataUtilities.getCumulativePercentages(values);
		double [] actualValues = new double[3];
		for(int i=0; i<result.getKeys().size(); i++) {
			actualValues[i]=(double) result.getValue(result.getKey(i));

		}
		
	}
	
	
	@Test
	//sequential list with negative values
	public void testSequentialListWithNegativeValues() {
		mockContext.checking(new Expectations() {
			{
				
				allowing(values).getValue(0);
				will(returnValue(-4));
				
				allowing(values).getKey(0);
				will(returnValue(0));

				allowing(values).getValue(1);
				will(returnValue(-6));
				
				allowing(values).getKey(1);
				will(returnValue(1));

				allowing(values).getValue(2);
				will(returnValue(2));
				
				allowing(values).getKey(2);
				will(returnValue(2));
				
				allowing(values).getItemCount();
				will(returnValue(3));

				
			}
		});
		KeyedValues result = DataUtilities.getCumulativePercentages(values);
		Boolean goodValues=true;
		for(int i=0; i<result.getKeys().size(); i++) {
			if((double) result.getValue(result.getKey(i))>1.0||(double) result.getValue(result.getKey(i))<0.0) {
				goodValues=false;
				break;
			}
		}
		assertTrue("Value greater than 1.0 or less than 0.0 returned",goodValues);
	}

	@Test
	//correct non sequential list
	public void testNonSequentialList() {
		mockContext.checking(new Expectations() {
			{
				
				allowing(values).getValue(0);
				will(returnValue(17));
				
				allowing(values).getValue(1);
				will(throwException(new UnknownKeyException ("1")));
				
				allowing(values).getItemCount();
				will(returnValue(3));

			}
		});
		Boolean thrown=false;
		try {
		DataUtilities.getCumulativePercentages(values);
		}catch(UnknownKeyException e) {
			thrown=true;
		}
		
		assertTrue(thrown);
	}
	
	@Test
	//null argument passed
	public void testNullArgPassed() {
		Boolean thrown =false;
		try {
		DataUtilities.getCumulativePercentages(null);
		}
		catch(IllegalArgumentException e) {
			thrown=true;
		}
		assertTrue(thrown);
	
	}
	@Test
	//unexpected behavior 
	public void testDataThrowsUnexpectedException () {
		mockContext.checking(new Expectations() {
			{
				
				allowing(values).getValue(0);
				will(returnValue(17));
				
				allowing(values).getKey(0);
				will(throwException(new IndexOutOfBoundsException("0")));
				
				allowing(values).getItemCount();
				will(returnValue(1));

			}
		});
		Boolean thrown=false;
		try {
		DataUtilities.getCumulativePercentages(values);
		}catch(IndexOutOfBoundsException e) {
			thrown=true;
		}
		
		assertTrue(thrown);
	}
}
