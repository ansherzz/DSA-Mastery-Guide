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

## Problems in this folder

### Easy
- `P01_RunningSumPrefixSum.java` - Build cumulative totals from left to right.
- `P02_RangeSumQueryImmutablePrefixSum.java` - Precompute prefix sums so every range query is constant time.
- `P03_FindPivotIndexPrefixSum.java` - Use total sum plus a running left sum to derive the right sum instantly.
- `P04_FindHighestAltitudePrefixSum.java` - The altitude at each point is a prefix sum; take the largest.
- `P05_LeftAndRightSumDifferencesPrefixSum.java` - Derive the right sum from the total and a running left sum.
- `P06_MinimumStartValuePrefixSum.java` - The minimum prefix sum decides the smallest safe starting value.
- `P07_SumOfAllOddLengthSubarraysPrefixSum.java` - Use a prefix array to sum every odd-length range in O(1) each.
- `P08_MaximumScoreAfterSplittingStringPrefixSum.java` - Score every split from a prefix count of zeros and a suffix count of ones.
- `P09_PointsThatIntersectWithCarsPrefixSum.java` - Mark each range in a difference array and prefix-sum it to get coverage.

### Medium
- `P10_ContinuousSubarraySumPrefixSum.java` - Equal prefix remainders imply a subarray sum divisible by k.
- `P11_ProductOfArrayExceptSelfPrefixSum.java` - Combine prefix products from the left and suffix products from the right.
- `P12_RangeSumQuery2DPrefixSum.java` - Build a 2D prefix table and answer rectangles with inclusion-exclusion.
- `P13_SubarraySumsDivisibleByKPrefixSum.java` - Count pairs of prefixes that share the same remainder mod k.
- `P14_MaximumSizeSubarraySumEqualsKPrefixSum.java` - Store the earliest index of each prefix to get the longest sum-k subarray.
- `P15_CorporateFlightBookingsPrefixSum.java` - Apply range updates with a difference array, then prefix-sum it.
- `P16_CarPoolingPrefixSum.java` - Mark pickups and drop-offs in a difference array and sweep the running load.
- `P17_NumberOfWaysToSplitArrayPrefixSum.java` - Compare each prefix against total minus prefix.
- `P18_XorQueriesOfSubarrayPrefixSum.java` - Prefix XOR answers range XOR queries because XOR undoes itself.
- `P19_CountVowelStringsInRangesPrefixSum.java` - Prefix-count qualifying words to answer range count queries.
- `P20_MatrixBlockSumPrefixSum.java` - Use a 2D prefix table with clamped corners for each cell's block.
- `P21_MaximumSubarrayPrefixSum.java` - Best subarray ending here is the current prefix minus the minimum earlier prefix.
- `P22_SubarraySumEqualsKPrefixSum.java` - Count earlier prefixes equal to prefix - k with a hash map.
- `P23_ContiguousArrayPrefixSum.java` - Treat 0 as -1 and find the two farthest indices with the same running balance.
- `P24_MinimumPenaltyForShopPrefixSum.java` - Track the running penalty change per hour and keep the earliest minimum.
- `P25_KRadiusSubarrayAveragesPrefixSum.java` - Answer every centered range sum in O(1) from one prefix array.
- `P26_MaximumSumOfTwoNonOverlappingSubarraysPrefixSum.java` - Pair each right subarray with the best left subarray seen so far, in both orders.
- `P27_MakeSumDivisibleByPPrefixSum.java` - Look up the prefix remainder that makes the removed subarray match total mod p.
- `P28_LongestSubstringWithVowelsInEvenCountsPrefixSum.java` - Use a prefix XOR mask of vowel parities and the first index of each mask.

### Hard
- `P29_ShortestSubarrayWithSumAtLeastKPrefixSum.java` - Combine prefix sums with a monotonic deque of start candidates.
- `P30_NumberOfSubmatricesThatSumToTargetPrefixSum.java` - Fix two rows, collapse to column sums, and count target subarrays with prefix sums.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

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
