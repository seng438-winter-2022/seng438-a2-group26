**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group 26         |          |                |
| ---------------- | -------- | -------------- |
| Liana Goodman    | 30089196 | LianaBG        |
| Amir Abdrakmanov | 30085827 | aabdrakmanov   |
| Jared Lundy      | 30086687 | jared840       |
| Jordan Lundy     | 30086686 | jordan427-prog |

1. [Introduction](#1-introduction)
2. [Detailed Description of unit test strategy](#2-detailed-description-of-unit-test-strategy)
3. [Test cases developed](#3-test-cases-developed)
4. [How the team work/effor was divided and managed](#4-how-the-team-workeffort-was-divided-and-managed)
5. [Difficulties encountered, challenges overcome, and lessons learned](#5-difficulties-encountered-challenges-overcome-and-lessons-learned)
6. [Comments/feedback on the lab itself](#6-commentsfeedback-on-the-lab-itself)

# 1 Introduction

This lab focuses on the fundamentals of automated unit testing. Using JUnit4 test framework, we are developing automated tests for the JFreeChart. Specific tests will be looking at `org.jfree.data` classes `DataUtilities` and `Range`. Certain methods in `DataUtilities` requires the use of JMock to create mocks of passed in interfaces.

# 2 Detailed description of unit test strategy
## DataUtilities

### `calculateColumnTotal(Values2D date, int column, int[] validRows)`

> Returns the total of the values in one column of the supplied data table by taking only the row numbers in the array into account.

To test this function we must examine boundary cases, regular cases, and invalid cases. The cases will need to verify valid and invalid options for all parameters including valid but non-conventional functionality.
Likely, these tests will include:

- testing an out of bounds `column`
- testing an in bound but end value `column`
- testing with `validRows` entries exceeding data rows
- testing with all valid parameters
- testing with null data (to test not permited functionality)
- testing with negative values
- testing with empty `validRows`

### `getCumulativePercentages(KeyedValues data)`

> Returns a KeyedValues object with corresponding indexes to data but with cumulative percentages

To test cases for this method were devised based on manipulating KeyedValues using mockery into the following types:

- Correct sequential List
- Correct Non-sequential List
- Correct sequential List with negative values
- List with unexpected behaviour
- Null argument

### `CalculateRowTotal(Values2D data, int row)`

>Returns the total of the values in one row of the supplied data table.

Testing the CalculateRowTotal method of Data Utilities requires the examination of a few boundary cases and equivalence classes.
Doing so should test the valid and invalid functionalities of this method, which will employ mocking the values2D parameter using 
JMock notation.
The equivalence classes and boundaries to be partitioned and tested are:

- Calculation of a valid row with valid parameters (within boundary)
- Calculation of valid row equal to a boundary
- Row out of bounds above boundary
- Negative row calculation
- Valid row with negative numbers
- Row with no values
- Testing NULL functionality

### `calculateColumnTotal(Values2D data, int row)`

>Returns the total of the values in one column of the supplied data table.

Testing the CalculateColumnTotal method of Data Utilities was done by taking in account equivalence classes, as well as
boundary classes using mocking of Values2D to ensure the functionality of the method. The tests are as followed:

- testing with valid parameters
- testing with valid column at the boundary of the table
- testing column outside of boundary of table
- negative column
- row with no values
- NULL row

### `calculateRowTotal(Values2D data, int row, int[] validCols)`
> Returns the total of the values in one row of the supplied data table by taking only the column numbers in the array into account.

Testing this method requires the testing of boundary cases, invalid cases and valid cases within the domain of the calculateRowTotal method(). Specifically, the third paramter - 
int[]validCols - is of particular interest in the determination and examination of such cases and how the method functions. As such, the test cases are as follows:

- Testing a valid row within validCols
- Testing a valid row on the boundary of validCols
- Testing a row with validCols exceeding actual columns
- Testing a negative row (should fail regardless of validCols)
- Testing an empty validCols
- Testing a negative validCols
- Testing a NULL DataUtilities
- Test with a null validcols

## Range

### `constrain(double value)`

> Returns the value within the range that is closest to the specified value.

To test this funciton we will need to examine a variety of values that may or may not be valid within range and as doubles. Test cases will also need to verify the "closest value" functionality. In this case, test cases can likely be combined without harming the test, resulting in reduced total number of test cases. These cases may include (and should explore):

- positive `value`
- negative `value`
- very large or small `value`
- `value` within the range
- `value` on range boundaries
- `value` out of the range

### `combine(Range range1, Range range2)`

> Returns a new range that is the combination of the two input ranges

To test this function, we will be dividing our test cases into the following paritions:

- Ranges do not have any overlap
- Ranges have boundary overlap
- Ranges have partial overlap
- Ranges have full overlap
- `range1` is null
- `range2` is null
- one Range partially overlaps with negative values
- one Range fully overlaps with negative values
- both ranges are null

These partitions allow us to verify the boundaries of each range while omitting equivalent cases where the ranges change place.

### `intersects(double b0, double b1)`


> returns true if the given range intersects the current range, false otherwise

To test this function, the following test cases were devised based on mathematics and the understanding that the variables are dependent on each other:
where upper is the calling object's upper bound, and lower is its lower bound, b0 is the argument lower bound and b1 is the argument upper bound

- upper>b1>=b0>lower
- b1>upper>b0>lower
- upper>b1>lower>b0
- b1>upper>=lower>b0
- b1=upper>lower=b0
- b1=upper>b0>lower
- upper>b1>lower=b0
- b1=upper=lower=b0
- b1>=b0>upper>lower
- upper>lower>b1>=b0
- b0>b1
- b1 is Double.NaN, b0 is valid
- b0 is Double.NaN, b1 is valid
- both b1 and b0 are Double.NaN
- both b0 and b1 are +Infinity
- both b0 and b1 are -Infinity
- b1 is +Infinity, b0 is -Inifinity

### `getLength()`

> Returns the length of the range.

In order to sufficiently test the getLength() method of the class Range, the upper and lower boundaries of a created Range object must be scrutinized. Since the length of the
range is merely the difference between these two values, we must create equivalency classes and boundary cases based on these parameters. The boundary cases are to be dictated
by the boundary of the Range values themselves, i.e. that lower<=upper & upper>=lower.
As such, the following equivalency classes classes and boundary cases were created:

- {lower range boundary < upper range boundary}
- {lower range boundary == upper range boundary}
- {lower range boundary > upper range boundary}
- {range is NULL}
- {a range boundary contains a very large number}
- {both boundaries are negative}
- {one boundary is negative}
- {both boundaries are zero}

These derived test cases should extensively test both the valid equivalency classes as well as invalid equivalency classes to sufficiently test the getLength() method's
functionality in accurately determining the length from the given Range boundaries.

### `getLowerBound()`

> Returns lower bound of range.

In order to properly test getLowerBound(), which tests the lower boundary of the range, we must first make a range object,
and then test equivalent classes, as well as boundary classes in order to properly determine the state of getLowerBound().
The equivalence classes and boundary classes of the lower boundary value were as followed:

- a negative value
- a positive value
- zero
- Double.MAX_VALUE-1
- Double.MIN_VALUE
- upper bound is less than lower boundary
- null Range

If all equivalence and boundaries are tested and passed, then getLowerBound() has been extensively tested.

# 3 Test cases developed

Source code can be found in seng438-a2-group26/Assignment2-artifact/TestCode/src/org/jfree/data/test/

## DataUtilities

### DataUtilitiesCalculateColumnTotalArrayTest

This class focuses on the test cases for the overwritten DataUtilities class method `calculateColumnTotal` with the array argument.
|_Method_|_Function_|
|---|---|
| testCCTAValid | all arguments are valid |
| testCCTAOutOfBoundsColumn | column argument is out of bounds |
| testCCTABoundaryColumn | column argument on boundary |
| testCCTAExtraValidRows | `validRows` contains rows outside of column values|
| testCCTANullData | `data` is null|
| testCCTANegativeValidValues|all arguments are valid but contain negatives|
| testCCTAEmptyValidRows |`validRows` contains nothing (`{}`)|

### TestDataUtilitiesCumulativePercentages

This test class focused on the test cases for `getCumulativePercentages(KeyedValues)` defined above

| _Method_                                 | _Function_                                             |
| ---------------------------------------- | ------------------------------------------------------ |
| **testSequentialList**                   | data is a correct sequential list                      |
| **testSequentialListWithNegativeValues** | data is a correct sequential list with negative values |
| **testNonSequentialList**                | data is a correct non sequential list                  |
| **testNullArgPassed**                    | data is null                                           |
| **testDataThrowsUnexpectedException**    | data is a list with unexpected behaviour               |

### DataUtilitiesCalcRowTotalTest

Test class designed to test the calculateRowTotal() method of DataUtilities, while using JMock to mock the Values2D parameter.

| _Method_                                  | _Function_                                            |
| ----------------------------------------- | ----------------------------------------------------- |
| **calculateRowTotalTwoValids**            | Valid row with valid parameters                       |
| **calculateRowTotalOutOfBounds**          | Row number out of bounds (>boundary)                  |
| **calculateRowTotalNegativeRows**         | Negative row number                                   |
| **calculateRowTotalOnBoundary**           | Valid row equal to a boundary                         |
| **calcRowTotalWithNegatives**             | Valid row with negative entries                        |
| **calcRowNoValues**                       | Row with no values                                     |
| **calcRowNullTest**                       | Null functionality                                     |

### testColumnTotal

Tests calculateColumnTotal() method from DataUtilities, and mocks the Values2D.

| _Method_ | _Function_ |
| -------- | ---------- |
| **testValidParameters** | tests valid column with valid value |
| **testBoundary** | tests boundary column |
| **testOutsideBoundary** | tests column outside of boundary |
| **testNegative** | tests negative column |
| **testEmpty** | tests an empty column |
| **nullColTest** | tests NULL table |

### rowTotalWithArray

Tests calculateRowTotal(Values2D, row, validCols) while mocking the Values2D values.

| _Method_ | _Function_ |
| ---- | ---- |
| **allValid** | Valid validCols and valid entries and row |
| **onBound** | Test row on validCols boundary |
| **greaterValidCols** | When validCols exceeds actual columns |
| **negativeRowCalc** | Calculate row total on negative row |
| **emptyValidCols** | Calculate row total with empty validCols |
| **negativeValidCols** | validCols[] contains negative value(s) |
| **nullTest** | null dataUtilities |
| **nullValidCols** | validCols is null |

## Range

### RangeConstrainTest

This class focused on the test cases for the Range class method `constrain`. As determined by the section above, the methods below test combinations of each partition. For the test cases a fixed range of **(-2, 12)** was used.

| _Method_                     | _Function_                           |
| ---------------------------- | ------------------------------------ |
| **testRangePositive**        | Positive `value` within range        |
| **testRangeNegative**        | Negative `value` within range        |
| **testRangeOutOfRangeAbove** | Positive `value` out of range        |
| **testRangeOutOfRangeBelow** | Negative `value` out of range        |
| **testRangeBoundaryUpper**   | Boundary `value` on upper range (12) |
| **testRangeBoundaryLower**   | Boundary `value` on lower range (-2) |

### RangeCombineTest

This class focused on the test cases for the Range class method `combine`. The methods were combinations of test cases that covered the outlined partitions while ignoring equivalence for efficiency.

For `testRangesNoOverlap` the documentation was unclear whether the method should combine the ranges and include the gap between them. For our developed tests, we assumed that the gap would be incorporated into the new range.

| _Method_                        | _Function_                                                                              |
| ------------------------------- | --------------------------------------------------------------------------------------- |
| **testRangesFullyOverlap**      | Combining 2 identical ranges                                                            |
| **testRangesPartiallyOverlap**  | Combining overlapping ranges                                                            |
| **testRangesBoundaryOverlap**   | Combining touching ranges                                                               |
| **testRangesNoOverlap**         | Combining ranges with no overlap                                                        |
| **testRangesFullyNegative**     | Combining partially overlapping ranges all in the negative                              |
| **testRangesPartiallyNegative** | Combining partially overlapping ranges with one in the negative and one in the positive |
| **testRangesNullRange1**        | Combining ranges with `range1` being `null`                                             |
| **testRangesNullRange2**        | Combining ranges with `range2` being `null`                                             |
| **testRangesNullBoth**          | Combining 2 `null` ranges                                                               |

### RangeTestIntersectsDouble

This class focused on test cases for the `intersects(double, double)` method as described above

| _Method_                                   | _Function_                       |
| ------------------------------------------ | -------------------------------- |
| **testB1GreaterThanEqualsB0Within Range**  | upper>b1>=b0>lower               |
| **testB1GreaterThanUpper**                 | b1>upper>b0>lower                |
| **testB0LessThanLower**                    | upper>b1>lower>b0                |
| **testArgRangeLargerThanCurrentRange**     | b1>upper>=lower>b0               |
| **testB1EqualsUpperAndB0EqualsLower**      | b1=upper>lower=b0                |
| **testB1EqualsUpperB0GreaterThanLower**    | b1=upper>b0>lower                |
| **testUpperGreaterThanB1AndB0EqualsLower** | upper>b1>lower=b0                |
| **testArgRangeDoesNotIntersectFromRight**  | b1>=b0>upper>lower               |
| **testArgRangeDoesNotIntersectFromLeft**   | upper>lower>b1>=b0               |
| **testAllEqualEachOther**                  | b1=upper=lower=b0                |
| **testB0GreaterThanB1**                    | b0>b1                            |
| **testB1NaN**                              | b1 is Double.NaN, b0 valid       |
| **testB0NaN**                              | b0 is Double.NaN, b1 valid       |
| **testBothNaN**                            | b1 and b0 are Double.NaN         |
| **testBothNegativeInf**                    | b1 and b0 are -Infinity          |
| **testBothPositiveInf**                    | b1 and b0 are +Infinity          |
| **testMaxArgRange**                        | b1 is +Infinity, b0 is -Infinity |

### RangeGetLengthTest

The following test cases were developed to throroughly test the getLength() method of Range. For most tests, an upper and a
lower boundary were assigned an arbitrary value that fit the prescribed test conditions, and then was checked against an assumed
infallible subtracted number for its accuracy.

| _Method_                                 | _Function_                                             |
| ---------------------------------------- | ------------------------------------------------------ |
| **testBothBoundariesZero**               | Length when both upper and lower bounds zero for Range |
| **testLowerBoundaryLessThanUpper**       | Length when lower boundary < upper boundary            |
| **testLowerBoundaryEqualsUpperBoundary** | Length when lower bound == upper bound                 |
| **testLowerBoundaryGreaterThanUpper**    | Length when lower bound > upper bound                  |
| **testNull**                             | Length for a NULL Range                                |
| **testBothNegative**                     | Length when both boundaries are negative values        |
| **testOneNegativeBound**                 | Length when one boundary is negative value             |
| **testLargePositive**                    | Length when a boundary == Double.MAX_VAUE              |


### getLowerBoundTest

The following test cases were used to determine any faults or bugs in getLowerBound(). An arbitrary value was used to test 
the tested cases below, and fit the requirments of the test cases.

| _Method_ | _Function_ |
| --- | --- |
| **testPositiveBoth** | lower boundary is positive value |
| **testNegativeNumber** | lower boundary is negative value |
| **testZero** | lower boundary is zero |
| **testBigValue** | lower boundary is max value -1 |
| **testSmallValue** | lower boundary is min value |
| **flipFlop** | lower boundary is greater than upper boundary |
| **nullRange** | Range is null |

# 4 How the team work/effort was divided and managed

The group work for this project was evenly divided between all 4 team members. Of the required 10 methods to test, each team member took 1 which required mock testing and 1 regularly tested method. Then, in pairs, the final 2 methods were tested. This method allowed for flexibility of work around an individual's schedule to fit in additional course work. It also allowed for each team member to work with both Mock and non-Mock test cases.

Similarly, the report was divided so that each section was completed by the student with the most relevant knowledge, such as each testing plan being described by the student designing it. Additional sections were done and reviewed by everyone.

# 5 Difficulties encountered, challenges overcome, and lessons learned

One difficulty the team came across was trying to all share a project in eclipse and have it function properly. We worked around this issue by pair programming the actual running of the tests.

Another issue encountered during the process was that the included jar files specifically hamcrest-core-1-3.jar did not have the required classes to allow for Jmock to be used, to remedy this, hamcrest-all-1-3.jar had to be found on the internet and added to the classpath (this file is included in 'seng438-a2-group26/Assignment2-artifact/Assignment2-artifact/jfreechart-1.0.19/jfreechart-1.0.19/lib/' you will need to change eclipse your classpath to run). 

Additionally, learning how to mock with JMock was somewhat of a challenge. Part of the process to understand JMock was gaining an understanding of what the SUT called from the mock but this was tricky to grasp at first. The use of mocks was an overall beneficial learing experience despite some the initial difficulty 

# 6 Comments/feedback on the lab itself

Lab documentation and instructions were clearly laid out. It could have been helpful if the lab documentation had slightly clearer instructions for the mock tests, perhaps more labels/comments on the examples so that we have a clear reminder.
