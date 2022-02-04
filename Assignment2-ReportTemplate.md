**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group 26         |          |                     |
| ---------------- | -------- | --------------|
| Liana Goodman    | 30089196 | LianaBG       |
| Amir Abdrakmanov | 30085827 | aabdrakmanov  |
| Jared Lundy      | 30086687 | jared840      |
| Jordan Lundy     | 30086686 | jordan427-prog|

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

To test this funciton we will need to examin a variety of values that may or may not be valid within range and as doubles. Test cases will also need to verify the "closest value" functionality. In this case, test cases can likely be combined without harming the test, resulting in reduced total number of test cases. These cases may include (and should explore):
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

These derived test cases should extensively test both the valid equivalency classes as well as invalid equivalency classes to sufficiently test the getLength() method's
functionality in accurately determining the length from the given Range boundaries.


# 3 Test cases developed

Text…

// write down the name of the test methods and classes. Organize the based on
the source code method // they test. identify which tests cover which partitions
you have explained in the test strategy section //above

# 4 How the team work/effort was divided and managed

The group work for this project was evenly divided between all 4 team members. Of the required 10 methods to test, each team member took 1 which required mock testing and 1 regularly tested method. Then, in pairs, the final 2 methods were tested. This method allowed for flexibility of work around an individual's schedule to fit in additional course work.

Similarly, the report was divided so that each section was completed by the student with the most relevant knowledge, such as each testing plan being described by the author. 

# 5 Difficulties encountered, challenges overcome, and lessons learned

Text…

# 6 Comments/feedback on the lab itself

Lab documentation and instructions were clearly laid out. It could have been helpful to have reminders in the lab document about how to work complicated tests such as the mock tests, similarly to how there were instuctions for the project and compilation.
