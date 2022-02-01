**SENG 438 - Software Testing, Reliability, and Quality**

**Lab. Report \#2 – Requirements-Based Test Generation**

| Group 26         |          |         |
| ---------------- | -------- | ------- |
| Liana Goodman    | 30089196 | LianaBG |
| Amir Abdrakmanov |          |         |
| Jared Lundyy     |          |         |
| Jordan Lundy     |          |         |

# 1 Introduction

Text…

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
- very large `value`
- overflowed `value`
- `value` within the range
- `value` on range boundaries
- `value` out of the range
# 3 Test cases developed

Text…

// write down the name of the test methods and classes. Organize the based on
the source code method // they test. identify which tests cover which partitions
you have explained in the test strategy section //above

# 4 How the team work/effort was divided and managed

Text…

# 5 Difficulties encountered, challenges overcome, and lessons learned

Text…

# 6 Comments/feedback on the lab itself

Text…
