package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.DataUtilities; 
import org.junit.*;

public class DataUtilitiesCalculateColumnTotalArrayTest {
    
    @Test
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

}

// @Test
// public void calculateColumnTotalForTwoValues() {
//     // setup
//     Mockery mockingContext = new Mockery();
//     final Values2D values = mockingContext.mock(Values2D.class);
//     mockingContext.checking(new Expectations() {
//         {
//             one(values).getRowCount();
//             will(returnValue(2));
//             one(values).getValue(0, 0);
//             will(returnValue(7.5));
//             one(values).getValue(1, 0);
//             will(returnValue(2.5));
//         }
//     });
//     // exercise	
//     double result = DataUtilities.calculateColumnTotal(values, 0);
//     // verify
//     assertEquals(result, 10.0, .000000001d);
//     // tear-down: NONE in this test method
// }