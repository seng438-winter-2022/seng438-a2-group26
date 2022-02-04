**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group 26         |          |                |
| ---------------- | -------- | -------------- |
| Liana Goodman    | 30089196 | LianaBG        |
| Amir Abdrakmanov | 30085827 | aabdrakmanov   |
| Jared Lundy      | 30086687 | jared840       |
| Jordan Lundy     | 30086686 | jordan427-prog |

# 1 Introduction

This lab focuses on the fundamentals of automated unit testing. Using JUnit test framework, we are developing automated tests for the JFreeChart. Specific tests will be looking at `org.jfree.data` classes `DataUtilities` and `Range`.

# 2 Detailed description of unit test strategy

// including the input partitions you have designed

## DataUtilities

### `calculateColumnTotal(Values2D date, int column, int[] validRows)`

> Returns the total of the values in one column of the supplied data table by taking only the row numbers in the array into account.

To test this function we must examine boundary cases, regular cases, and invalid cases. The cases will need to verify valid and invalid options for all parameters including valid but non-conventional functionality.
Likely, these tests will include:

- testing an out of bounds `column`
- testing an in bound but end value `column`
- testing with `validRows` entries exceeding data rows
- testing with all valid parameters
- testing with empty `validRows`

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

To test this function, the following equivilance classes were devised based on mathematics and the understanding that the variables are dependent on each other:
where upper is the calling object's upper bound, and lower is its lower bound, b0 is the argument lower bound and b1 is the argument upper bound

- upper>b1>=b0>lower
- b1>upper>b0>lower
- upper>b1>lower>b0
- b1>upper>=lower>b0
- b1=upper>lower=b0
- b1=upper>b0>lower
- upper>b1>lower=b0
- b1=upper=lower=b0
- b0>b1
- b1 is Double.NaN, b0 is valid
- b0 is Double.NaN, b1 is valid
- both b1 and b0 are Double.NaN

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
- {a range boundary contains a 'very large negative' number}
- {a rounge boundary contains an overflow ie. >MAX_SIZE(double)}
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
- negative overflow
- positive overflow

If all equivalence and boundaries are tested and passed, then getLowerBound() has been extensively tested.

# 3 Test cases developed
## DataUtilities

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

// write down the name of the test methods and classes. Organize the based on
the source code method // they test. identify which tests cover which partitions
you have explained in the test strategy section //above

# 4 How the team work/effort was divided and managed

The group work for this project was evenly divided between all 4 team members. Of the required 10 methods to test, each team member took 1 which required mock testing and 1 regularly tested method. Then, in pairs, the final 2 methods were tested. This method allowed for flexibility of work around an individual's schedule to fit in additional course work. It also allowed for each team member to work with both Mock and non-Mock test cases.

Similarly, the report was divided so that each section was completed by the student with the most relevant knowledge, such as each testing plan being described by the student designing it. Additional sections were done and reviewed by everyone.

# 5 Difficulties encountered, challenges overcome, and lessons learned

One difficulty the team came across was trying to all share a project in eclipse and have it function properly. We worked together to figure out how to share our individual processes until we all got eclipse to work.

# 6 Comments/feedback on the lab itself

Lab documentation and instructions were clearly laid out. It could have been helpful if the lab documentation had slightly clearer instructions for the mock tests, perhaps more labels/comments on the examples so that we have a clear reminder.
