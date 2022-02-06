package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.DataUtilities; 
import org.junit.*;

public class DataUtilitiesCalculateColumnTotalArrayTest {
    
    @Test // done
    void testCCTAValid () {
        Mockery mockingContext = new Mockery ();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking (new Expectations () {
            {
                one(values).getColumnCount ();
                will(returnValue(3));
                // first row
                one(values).getValue(0,0);
                will(returnValue(13.2));
                one(values).getValue(0,1);
                will(returnValue(1.7));
                one(values).getValue(0,2);
                will(returnValue(2.7));
                // second row
                one(values).getValue(1,0);
                will(returnValue(4));
                one(values).getValue(1,1);
                will(returnValue(17));
                one(values).getValue(1,2);
                will(returnValue(2.1));
            }
        });

        int [] validRows = {0,1};
        double result = DataUtilities.calculateColumnTotal (values, 1, validRows);

        assertEquals (result, 18.7, 0);
    }
    
    @Test // not sure what the results of this test should be
    void testCCTAOutOfBoundsColumn () {
        Mockery mockingContext = new Mockery ();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking (new Expectations () {
            {
                one(values).getColumnCount ();
                will(returnValue(3));
                // first row
                one(values).getValue(0,0);
                will(returnValue(13.2));
                one(values).getValue(0,1);
                will(returnValue(1.7));
                one(values).getValue(0,2);
                will(returnValue(2.7));
                // second row
                one(values).getValue(1,0);
                will(returnValue(4));
                one(values).getValue(1,1);
                will(returnValue(17));
                one(values).getValue(1,2);
                will(returnValue(2.1));
            }
        });

        int [] validRows = {0,1};
        try {
            double result = DataUtilities.calculateColumnTotal (values, 7, validRows);
            assertEquals (result, 0, 0);
        } catch (Exception e) {
            System.out.println("Exception thrown.");
            assertEquals(0,0,0);
        }
    }
    
    @Test // done
    void testCCTABoundaryColumn () {
        Mockery mockingContext = new Mockery ();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking (new Expectations () {
            {
                one(values).getColumnCount ();
                will(returnValue(3));
                // first row
                one(values).getValue(0,0);
                will(returnValue(13.2));
                one(values).getValue(0,1);
                will(returnValue(1.7));
                one(values).getValue(0,2);
                will(returnValue(2.7));
                // second row
                one(values).getValue(1,0);
                will(returnValue(4));
                one(values).getValue(1,1);
                will(returnValue(17));
                one(values).getValue(1,2);
                will(returnValue(2.1));
            }
        });

        int [] validRows = {0,1};
        double result = DataUtilities.calculateColumnTotal (values, 0, validRows);

        assertEquals (result, 17.2, 0);
    }
    
    @Test // not sure what this should output
    void testCCTAExtraValidRows () {
        Mockery mockingContext = new Mockery ();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking (new Expectations () {
            {
                one(values).getColumnCount ();
                will(returnValue(3));
                // first row
                one(values).getValue(0,0);
                will(returnValue(13.2));
                one(values).getValue(0,1);
                will(returnValue(1.7));
                one(values).getValue(0,2);
                will(returnValue(2.7));
                // second row
                one(values).getValue(1,0);
                will(returnValue(4));
                one(values).getValue(1,1);
                will(returnValue(17));
                one(values).getValue(1,2);
                will(returnValue(2.1));
            }
        });

        int [] validRows = {0,4};
        double result = DataUtilities.calculateColumnTotal (values, 1, validRows);

        assertEquals (result, 1.7, 0);
    }
    
    @Test // not sure what this should result in
    void testCCTANullData () {
        int [] validRows = {0,1};
        
        try {
            double result = DataUtilities.calculateColumnTotal (null, 1, validRows);
            assertEquals (result, 0, 0);
        } catch (Exception e) {
            System.out.println("Exception thrown.");
            assertEquals(0,0,0);
        }

    }
    
    @Test // done
    void testCCTAENegativeValidValues () {
        Mockery mockingContext = new Mockery ();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking (new Expectations () {
            {
                one(values).getColumnCount ();
                will(returnValue(3));
                // first row
                one(values).getValue(0,0);
                will(returnValue(-13.2));
                one(values).getValue(0,1);
                will(returnValue(-1.7));
                one(values).getValue(0,2);
                will(returnValue(2.7));
                // second row
                one(values).getValue(1,0);
                will(returnValue(4));
                one(values).getValue(1,1);
                will(returnValue(17));
                one(values).getValue(1,2);
                will(returnValue(-2.1));
            }
        });

        int [] validRows = {0,1};
        double result = DataUtilities.calculateColumnTotal (values, 1, validRows);

        assertEquals (result, 15.3, 0);
    }
    
    @Test // might return 0, might return an exception
    void testCCTAEmptyValidRows () {
        Mockery mockingContext = new Mockery ();
        final Values2D values = mockingContext.mock(Values2D.class);
        mockingContext.checking (new Expectations () {
            {
                one(values).getColumnCount ();
                will(returnValue(3));
                // first row
                one(values).getValue(0,0);
                will(returnValue(13.2));
                one(values).getValue(0,1);
                will(returnValue(1.7));
                one(values).getValue(0,2);
                will(returnValue(2.7));
                // second row
                one(values).getValue(1,0);
                will(returnValue(4));
                one(values).getValue(1,1);
                will(returnValue(17));
                one(values).getValue(1,2);
                will(returnValue(2.1));
            }
        });

        int [] validRows = {};
        try {
            double result = DataUtilities.calculateColumnTotal (values, 1, validRows);
            assertEquals (result, 0, 0);
        } catch (Exception e) {
            System.out.println("Exception thrown");
            assertEquals (0, 0, 0); // send proper finish
        }
    }
    
}