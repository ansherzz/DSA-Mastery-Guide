# 05. Prefix Sum

## What this pattern is
Prefix sum precomputes cumulative information so any range query can be answered in O(1) or transformed into a hashmap lookup.

## When to use it
- When range sums appear repeatedly.
- When subarray properties depend on cumulative totals or remainders.
- When you can rewrite a target subarray condition using prefix differences.
- When left-to-right accumulation can answer future queries quickly.

## How to identify it in interviews
- The problem asks about many range sums or subarray totals.
- You can express sum(i..j) as prefix[j] - prefix[i-1].
- You need a cumulative running total or prefix product idea.
- There is a repeated query over a static array.

## Upgrade path / mental model
If the prompt says subarray sum equals something, think prefix sum first, then ask whether a hashmap of prefixes is needed.

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Running totals and static range queries** - P01, P02, P04, P07, P08, P09, P27, P28, P38, P39
- **Left total versus right total (split points)** - P03, P05, P06, P10, P22, P23, P36, P37
- **Prefix and suffix arrays combined** - P14, P40, P41, P48
- **Prefix sum with a hash map** - P18, P31, P33, P34, P35, P50
- **Prefix remainders (modular arithmetic)** - P13, P17, P42
- **Prefix XOR and parity masks** - P24, P25, P26, P43, P44
- **Difference array (range updates)** - P11, P12, P19, P20, P21, P30
- **2D prefix sums** - P16, P29, P47, P49
- **Prefix sums with a monotonic deque** - P46
- **Prefix products** - P15
- **Prefix extremes** - P32
- **Fenwick tree (prefix sums with updates)** - P45

## Problems in this folder

### Easy
- `P01_RunningSumPrefixSum.java` - Build cumulative totals from left to right.
- `P02_RangeSumQueryImmutablePrefixSum.java` - Precompute prefix sums so every range query is constant time.
- `P03_FindPivotIndexPrefixSum.java` - Use total sum plus a running left sum to derive the right sum instantly.
- `P04_FindHighestAltitudePrefixSum.java` - The altitude at each point is a prefix sum; take the largest.
- `P05_LeftAndRightSumDifferencesPrefixSum.java` - Derive the right sum from the total and a running left sum.
- `P06_CountPartitionsWithEvenSumDifferencePrefixSum.java` - Sweep the split point with a running left sum and derive the right sum from the total.
- `P07_MinimumStartValuePrefixSum.java` - The minimum prefix sum decides the smallest safe starting value.
- `P08_SumOfAllOddLengthSubarraysPrefixSum.java` - Use a prefix array to sum every odd-length range in O(1) each.
- `P09_SumOfVariableLengthSubarraysPrefixSum.java` - Answer each index's custom range [max(0, i - nums[i]), i] from one prefix array.
- `P10_MaximumScoreAfterSplittingStringPrefixSum.java` - Score every split from a prefix count of zeros and a suffix count of ones.
- `P11_PointsThatIntersectWithCarsPrefixSum.java` - Mark each range in a difference array and prefix-sum it to get coverage.
- `P12_MaximumPopulationYearPrefixSum.java` - Add +1 at each birth year and -1 at each death year, then prefix-sum to get the population per year.

### Medium
- `P13_ContinuousSubarraySumPrefixSum.java` - Equal prefix remainders imply a subarray sum divisible by k.
- `P14_ProductOfArrayExceptSelfPrefixSum.java` - Combine prefix products from the left and suffix products from the right.
- `P15_ProductOfTheLastKNumbersPrefixSum.java` - Store prefix products and divide two of them; reset the list whenever a zero arrives.
- `P16_RangeSumQuery2DPrefixSum.java` - Build a 2D prefix table and answer rectangles with inclusion-exclusion.
- `P17_SubarraySumsDivisibleByKPrefixSum.java` - Count pairs of prefixes that share the same remainder mod k.
- `P18_MaximumSizeSubarraySumEqualsKPrefixSum.java` - Store the earliest index of each prefix to get the longest sum-k subarray.
- `P19_CorporateFlightBookingsPrefixSum.java` - Apply range updates with a difference array, then prefix-sum it.
- `P20_CarPoolingPrefixSum.java` - Mark pickups and drop-offs in a difference array and sweep the running load.
- `P21_ShiftingLettersIIPrefixSum.java` - Record each range shift as +/-1 at two endpoints and resolve all shifts with one prefix pass.
- `P22_NumberOfWaysToSplitArrayPrefixSum.java` - Compare each prefix against total minus prefix.
- `P23_MinimumAverageDifferencePrefixSum.java` - Compare the average of each prefix with the average of the remaining suffix.
- `P24_XorQueriesOfSubarrayPrefixSum.java` - Prefix XOR answers range XOR queries because XOR undoes itself.
- `P25_NumberOfSubarraysWithOddSumPrefixSum.java` - A subarray sum is odd when its two bounding prefixes have different parity, so count prefixes by parity.
- `P26_CountTripletsThatCanFormTwoArraysOfEqualXORPrefixSum.java` - Equal halves mean the whole range XORs to 0, so look for equal prefix XORs.
- `P27_CountVowelStringsInRangesPrefixSum.java` - Prefix-count qualifying words to answer range count queries.
- `P28_PlatesBetweenCandlesPrefixSum.java` - Snap each query to its inner candles with nearest-candle arrays, then use a prefix count of plates.
- `P29_MatrixBlockSumPrefixSum.java` - Use a 2D prefix table with clamped corners for each cell's block.
- `P30_IncrementSubmatricesByOnePrefixSum.java` - Mark four corners per query in a 2D difference array and take a 2D prefix sum at the end.
- `P31_MaximumSubarrayPrefixSum.java` - Best subarray ending here is the current prefix minus the minimum earlier prefix.
- `P32_MaximumAbsoluteSumOfAnySubarrayPrefixSum.java` - The answer is the largest prefix sum minus the smallest prefix sum.
- `P33_SubarraySumEqualsKPrefixSum.java` - Count earlier prefixes equal to prefix - k with a hash map.
- `P34_ContiguousArrayPrefixSum.java` - Treat 0 as -1 and find the two farthest indices with the same running balance.
- `P35_LongestWellPerformingIntervalPrefixSum.java` - Score days +1 / -1 and look up the first time the running score was exactly one lower.
- `P36_MinimumPenaltyForShopPrefixSum.java` - Track the running penalty change per hour and keep the earliest minimum.
- `P37_GridGamePrefixSum.java` - For each column where robot 1 drops down, robot 2 gets the larger of a top suffix or a bottom prefix.
- `P38_KRadiusSubarrayAveragesPrefixSum.java` - Answer every centered range sum in O(1) from one prefix array.
- `P39_SumOfAbsoluteDifferencesInASortedArrayPrefixSum.java` - Split each absolute-difference sum into a left part and a right part using prefix sums.
- `P40_MaximumSumOfTwoNonOverlappingSubarraysPrefixSum.java` - Pair each right subarray with the best left subarray seen so far, in both orders.
- `P41_WaysToMakeAFairArrayPrefixSum.java` - Removing index i flips the parity of everything to its right, so combine left sums with swapped right sums.
- `P42_MakeSumDivisibleByPPrefixSum.java` - Look up the prefix remainder that makes the removed subarray match total mod p.
- `P43_LongestSubstringWithVowelsInEvenCountsPrefixSum.java` - Use a prefix XOR mask of vowel parities and the first index of each mask.
- `P44_CanMakePalindromeFromSubstringPrefixSum.java` - XOR two prefix parity masks to get the letters with odd counts in any substring.
- `P45_RangeSumQueryMutablePrefixSum.java` - A Binary Indexed Tree keeps prefix sums queryable and updatable in O(log n).

### Hard
- `P46_ShortestSubarrayWithSumAtLeastKPrefixSum.java` - Combine prefix sums with a monotonic deque of start candidates.
- `P47_NumberOfSubmatricesThatSumToTargetPrefixSum.java` - Fix two rows, collapse to column sums, and count target subarrays with prefix sums.
- `P48_MaximumSumOfThreeNonOverlappingSubarraysPrefixSum.java` - Precompute the best window to the left and to the right of every position, then fix the middle window.
- `P49_MaxSumOfRectangleNoLargerThanKPrefixSum.java` - Fix two columns, collapse rows into a 1D array, and search prefix sums in an ordered set.
- `P50_CountSubarraysWithMedianKPrefixSum.java` - Turn values into +1 / -1 around k and match balances on the two sides of k's position.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P01_RunningSumPrefixSum.java
java P01_RunningSumPrefixSum
```
