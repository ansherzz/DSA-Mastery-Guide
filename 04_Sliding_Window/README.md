# 04. Sliding Window

## What this pattern is
Sliding window maintains a contiguous range while expanding and shrinking it to satisfy a condition. It is ideal for substring and subarray problems.

## When to use it
- When the answer is about a contiguous window.
- When you need longest, shortest, max, or min segment under a constraint.
- When you can update state by adding one element and removing one element.
- When a window condition becomes valid or invalid as you move.

## How to identify it in interviews
- The prompt mentions substring, subarray, contiguous, window, or segment.
- You want the longest or shortest range that satisfies a condition.
- A brute-force approach would try every range.
- You can maintain counts while moving left and right.

## Upgrade path / mental model
Decide the window invariant first: what must be true before you record an answer or before you shrink?

## Problems in this folder

### Easy
- `MaximumAverageSubarraySlidingWindow.java` - Slide a fixed window and update the sum in O(1).
- `MinimumRecolorsSlidingWindow.java` - Find the fixed window with the fewest white blocks.
- `ContainsDuplicateIISlidingWindow.java` - Keep a set of the last k values and look for a repeat inside it.
- `DefuseTheBombSlidingWindow.java` - Slide a fixed-size sum around a circular array.

### Medium
- `LongestSubstringWithoutRepeatingSlidingWindow.java` - Expand right, and shift left only when a repeat breaks the window.
- `MinimumSizeSubarraySumSlidingWindow.java` - Positive values make expand-and-shrink logic possible.
- `PermutationInStringSlidingWindow.java` - Use a fixed-length window and maintain frequency counts.
- `LongestRepeatingCharacterReplacementSlidingWindow.java` - Maintain a window where all but the most frequent letter can be changed within k.
- `MaxConsecutiveOnesIIISlidingWindow.java` - Keep the longest window containing at most k zeros.
- `FruitIntoBasketsSlidingWindow.java` - Longest window with at most two distinct values.
- `FindAllAnagramsSlidingWindow.java` - Compare fixed-window letter counts against the pattern's counts.
- `LongestSubstringKDistinctSlidingWindow.java` - Shrink whenever the window holds more than k distinct characters.
- `SubarrayProductLessThanKSlidingWindow.java` - Count valid subarrays ending at each right index using a running product.
- `MaximumVowelsInSubstringSlidingWindow.java` - Track the vowel count of a fixed-length window.
- `MaximumSumOfDistinctSubarraysSlidingWindow.java` - Fixed window with a frequency map to confirm all values are distinct.
- `LongestSubarrayAfterDeletingOneSlidingWindow.java` - Longest window with at most one zero, minus the deleted element.
- `MaximumPointsFromCardsSlidingWindow.java` - Minimize the middle window that is left behind.
- `GrumpyBookstoreOwnerSlidingWindow.java` - Add the best rescued window to the always-satisfied total.
- `BinarySubarraysWithSumSlidingWindow.java` - Count exactly-goal as atMost(goal) - atMost(goal - 1).
- `LongestNiceSubarraySlidingWindow.java` - Track the bits used by the window and shrink when a new value shares one.
- `MaxConsecutiveOnesIISlidingWindow.java` - Find the longest window containing at most one zero.
- `SubarraysOfSizeKWithAverageThresholdSlidingWindow.java` - Maintain a fixed-size window sum and compare it with k * threshold.
- `CountNumberOfNiceSubarraysSlidingWindow.java` - Count exactly k odd numbers as atMost(k) - atMost(k - 1).
- `GetEqualSubstringsWithinBudgetSlidingWindow.java` - Grow a window of per-index costs and shrink it when the budget is exceeded.
- `MinimumOperationsToReduceXToZeroSlidingWindow.java` - Flip the problem: find the longest middle window with sum total - x.
- `FrequencyOfMostFrequentElementSlidingWindow.java` - Sort, then keep the longest window that can be raised to its largest value within k.

### Hard
- `MinimumWindowSubstringSlidingWindow.java` - Grow until valid, then shrink to the smallest valid range.
- `MaximumOfEachWindowSlidingWindow.java` - Use a monotonic deque of candidates for each window's maximum.
- `SubarraysWithKDistinctSlidingWindow.java` - Count exactly-k distinct as atMost(k) - atMost(k - 1).
- `SubstringWithConcatenationOfAllWordsSlidingWindow.java` - Slide a window of word-sized tokens once per alignment offset.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac LongestSubstringWithoutRepeatingSlidingWindow.java
java LongestSubstringWithoutRepeatingSlidingWindow
```
