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

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Fixed-size window aggregate** - P01, P02, P03, P06, P22, P27, P28, P34
- **Fixed-size window with a frequency map** - P04, P05, P13, P17, P18, P23, P47
- **Longest window that satisfies a constraint** - P08, P09, P10, P14, P15, P16, P19, P24, P25, P31, P32, P33, P37
- **Shortest window that satisfies a constraint** - P11, P12, P43
- **Counting subarrays (at-most-K trick)** - P20, P29, P30, P35, P45, P46
- **Complement window (keep the middle, remove the ends)** - P26, P38, P39
- **Sort first, then slide** - P07, P40
- **Monotonic deque window** - P41, P42, P44, P48
- **Counting subarrays (at-least-K via left boundary)** - P21, P36
- **Window backed by an ordered structure** - P49
- **Window of active operations** - P50

## Problems in this folder

### Easy
- `P01_MaximumAverageSubarraySlidingWindow.java` - Slide a fixed window and update the sum in O(1).
- `P02_MinimumRecolorsSlidingWindow.java` - Find the fixed window with the fewest white blocks.
- `P03_FindTheKBeautyOfANumberSlidingWindow.java` - Slide a length-k window over the digits and test each window value as a divisor.
- `P04_ContainsDuplicateIISlidingWindow.java` - Keep a set of the last k values and look for a repeat inside it.
- `P05_SubstringsOfSizeThreeWithDistinctCharactersSlidingWindow.java` - Check each window of three characters for pairwise distinct letters.
- `P06_DefuseTheBombSlidingWindow.java` - Slide a fixed-size sum around a circular array.
- `P07_MinimumDifferenceBetweenHighestAndLowestOfKScoresSlidingWindow.java` - Sort the scores; the best k scores are adjacent, so slide a window of size k.
- `P08_MaximumLengthSubstringWithTwoOccurrencesSlidingWindow.java` - Grow the window and shrink it whenever a letter appears a third time.

### Medium
- `P09_LongestSubstringWithoutRepeatingSlidingWindow.java` - Expand right, and shift left only when a repeat breaks the window.
- `P10_MaximumErasureValueSlidingWindow.java` - Keep a window of unique values and maximize its sum instead of its length.
- `P11_MinimumSizeSubarraySumSlidingWindow.java` - Positive values make expand-and-shrink logic possible.
- `P12_MinimumConsecutiveCardsToPickUpSlidingWindow.java` - The shortest window with a matching pair ends at a card and starts at its previous occurrence.
- `P13_PermutationInStringSlidingWindow.java` - Use a fixed-length window and maintain frequency counts.
- `P14_LongestRepeatingCharacterReplacementSlidingWindow.java` - Maintain a window where all but the most frequent letter can be changed within k.
- `P15_MaxConsecutiveOnesIIISlidingWindow.java` - Keep the longest window containing at most k zeros.
- `P16_FruitIntoBasketsSlidingWindow.java` - Longest window with at most two distinct values.
- `P17_FindAllAnagramsSlidingWindow.java` - Compare fixed-window letter counts against the pattern's counts.
- `P18_FindKLengthSubstringsWithNoRepeatedCharactersSlidingWindow.java` - Maintain the longest repeat-free window ending at each index and count when it reaches length k.
- `P19_LongestSubstringKDistinctSlidingWindow.java` - Shrink whenever the window holds more than k distinct characters.
- `P20_SubarrayProductLessThanKSlidingWindow.java` - Count valid subarrays ending at each right index using a running product.
- `P21_NumberOfSubstringsContainingAllThreeCharactersSlidingWindow.java` - For each right end, every start at or before the oldest of the three last-seen positions works.
- `P22_MaximumVowelsInSubstringSlidingWindow.java` - Track the vowel count of a fixed-length window.
- `P23_MaximumSumOfDistinctSubarraysSlidingWindow.java` - Fixed window with a frequency map to confirm all values are distinct.
- `P24_LongestSubarrayAfterDeletingOneSlidingWindow.java` - Longest window with at most one zero, minus the deleted element.
- `P25_LongestTurbulentSubarraySlidingWindow.java` - Extend the window while comparison signs alternate; restart it where the pattern breaks.
- `P26_MaximumPointsFromCardsSlidingWindow.java` - Minimize the middle window that is left behind.
- `P27_GrumpyBookstoreOwnerSlidingWindow.java` - Add the best rescued window to the always-satisfied total.
- `P28_MinimumSwapsToGroupAllOnesTogetherIISlidingWindow.java` - Slide a circular window whose size is the number of ones and minimize the zeros inside.
- `P29_BinarySubarraysWithSumSlidingWindow.java` - Count exactly-goal as atMost(goal) - atMost(goal - 1).
- `P30_NumberOfSubarraysWithBoundedMaximumSlidingWindow.java` - Count subarrays with max <= right and subtract those with max <= left - 1.
- `P31_LongestNiceSubarraySlidingWindow.java` - Track the bits used by the window and shrink when a new value shares one.
- `P32_LengthOfLongestSubarrayWithAtMostKFrequencySlidingWindow.java` - Shrink from the left only while the newly added value occurs more than k times.
- `P33_MaxConsecutiveOnesIISlidingWindow.java` - Find the longest window containing at most one zero.
- `P34_SubarraysOfSizeKWithAverageThresholdSlidingWindow.java` - Maintain a fixed-size window sum and compare it with k * threshold.
- `P35_CountNumberOfNiceSubarraysSlidingWindow.java` - Count exactly k odd numbers as atMost(k) - atMost(k - 1).
- `P36_CountSubarraysWhereMaxElementAppearsAtLeastKTimesSlidingWindow.java` - Advance left until the window just loses its k-th maximum; every earlier start is valid.
- `P37_GetEqualSubstringsWithinBudgetSlidingWindow.java` - Grow a window of per-index costs and shrink it when the budget is exceeded.
- `P38_MinimumOperationsToReduceXToZeroSlidingWindow.java` - Flip the problem: find the longest middle window with sum total - x.
- `P39_ReplaceTheSubstringForBalancedStringSlidingWindow.java` - Find the shortest window such that everything outside it has no letter above n / 4.
- `P40_FrequencyOfMostFrequentElementSlidingWindow.java` - Sort, then keep the longest window that can be raised to its largest value within k.
- `P41_LongestSubarrayWithAbsoluteDiffAtMostLimitSlidingWindow.java` - Maintain the window's max and min with two monotonic deques and shrink when they are too far apart.
- `P42_JumpGameVISlidingWindow.java` - dp[i] = nums[i] + max of the previous k dp values, kept in a decreasing deque.

### Hard
- `P43_MinimumWindowSubstringSlidingWindow.java` - Grow until valid, then shrink to the smallest valid range.
- `P44_MaximumOfEachWindowSlidingWindow.java` - Use a monotonic deque of candidates for each window's maximum.
- `P45_SubarraysWithKDistinctSlidingWindow.java` - Count exactly-k distinct as atMost(k) - atMost(k - 1).
- `P46_CountSubarraysWithScoreLessThanKSlidingWindow.java` - Shrink while sum * length >= k; each right end then contributes its window length.
- `P47_SubstringWithConcatenationOfAllWordsSlidingWindow.java` - Slide a window of word-sized tokens once per alignment offset.
- `P48_ConstrainedSubsequenceSumSlidingWindow.java` - dp[i] = nums[i] + max(0, best dp in the previous k positions) using a decreasing deque.
- `P49_SlidingWindowMedianSlidingWindow.java` - Split the window into a lower and an upper half held in two balanced trees of indices.
- `P50_MinimumNumberOfKConsecutiveBitFlipsSlidingWindow.java` - Scan left to right, flipping greedily, and track how many flips still cover the current index.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P09_LongestSubstringWithoutRepeatingSlidingWindow.java
java P09_LongestSubstringWithoutRepeatingSlidingWindow
```
