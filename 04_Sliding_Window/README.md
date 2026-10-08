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
- `P01_MaximumAverageSubarraySlidingWindow.java` - Slide a fixed window and update the sum in O(1).
- `P02_MinimumRecolorsSlidingWindow.java` - Find the fixed window with the fewest white blocks.
- `P03_ContainsDuplicateIISlidingWindow.java` - Keep a set of the last k values and look for a repeat inside it.
- `P04_DefuseTheBombSlidingWindow.java` - Slide a fixed-size sum around a circular array.

### Medium
- `P05_LongestSubstringWithoutRepeatingSlidingWindow.java` - Expand right, and shift left only when a repeat breaks the window.
- `P06_MinimumSizeSubarraySumSlidingWindow.java` - Positive values make expand-and-shrink logic possible.
- `P07_PermutationInStringSlidingWindow.java` - Use a fixed-length window and maintain frequency counts.
- `P08_LongestRepeatingCharacterReplacementSlidingWindow.java` - Maintain a window where all but the most frequent letter can be changed within k.
- `P09_MaxConsecutiveOnesIIISlidingWindow.java` - Keep the longest window containing at most k zeros.
- `P10_FruitIntoBasketsSlidingWindow.java` - Longest window with at most two distinct values.
- `P11_FindAllAnagramsSlidingWindow.java` - Compare fixed-window letter counts against the pattern's counts.
- `P12_LongestSubstringKDistinctSlidingWindow.java` - Shrink whenever the window holds more than k distinct characters.
- `P13_SubarrayProductLessThanKSlidingWindow.java` - Count valid subarrays ending at each right index using a running product.
- `P14_MaximumVowelsInSubstringSlidingWindow.java` - Track the vowel count of a fixed-length window.
- `P15_MaximumSumOfDistinctSubarraysSlidingWindow.java` - Fixed window with a frequency map to confirm all values are distinct.
- `P16_LongestSubarrayAfterDeletingOneSlidingWindow.java` - Longest window with at most one zero, minus the deleted element.
- `P17_MaximumPointsFromCardsSlidingWindow.java` - Minimize the middle window that is left behind.
- `P18_GrumpyBookstoreOwnerSlidingWindow.java` - Add the best rescued window to the always-satisfied total.
- `P19_BinarySubarraysWithSumSlidingWindow.java` - Count exactly-goal as atMost(goal) - atMost(goal - 1).
- `P20_LongestNiceSubarraySlidingWindow.java` - Track the bits used by the window and shrink when a new value shares one.
- `P21_MaxConsecutiveOnesIISlidingWindow.java` - Find the longest window containing at most one zero.
- `P22_SubarraysOfSizeKWithAverageThresholdSlidingWindow.java` - Maintain a fixed-size window sum and compare it with k * threshold.
- `P23_CountNumberOfNiceSubarraysSlidingWindow.java` - Count exactly k odd numbers as atMost(k) - atMost(k - 1).
- `P24_GetEqualSubstringsWithinBudgetSlidingWindow.java` - Grow a window of per-index costs and shrink it when the budget is exceeded.
- `P25_MinimumOperationsToReduceXToZeroSlidingWindow.java` - Flip the problem: find the longest middle window with sum total - x.
- `P26_FrequencyOfMostFrequentElementSlidingWindow.java` - Sort, then keep the longest window that can be raised to its largest value within k.

### Hard
- `P27_MinimumWindowSubstringSlidingWindow.java` - Grow until valid, then shrink to the smallest valid range.
- `P28_MaximumOfEachWindowSlidingWindow.java` - Use a monotonic deque of candidates for each window's maximum.
- `P29_SubarraysWithKDistinctSlidingWindow.java` - Count exactly-k distinct as atMost(k) - atMost(k - 1).
- `P30_SubstringWithConcatenationOfAllWordsSlidingWindow.java` - Slide a window of word-sized tokens once per alignment offset.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P05_LongestSubstringWithoutRepeatingSlidingWindow.java
java P05_LongestSubstringWithoutRepeatingSlidingWindow
```
