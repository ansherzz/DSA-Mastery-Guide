# 08. Advanced Search

## What this pattern is
Advanced search problems apply binary-search thinking in rotated arrays, partitioned arrays, or structures where the sorted property is partially hidden.

## When to use it
- When sorted order exists but is shifted, partitioned, or implicit.
- When you need to search or find boundaries in a rotated array.
- When the answer depends on choosing the right partition.
- When classic binary search must be adapted instead of copied.

## How to identify it in interviews
- The prompt mentions rotated sorted arrays, duplicates, or two sorted arrays.
- At least one half is guaranteed to be sorted at each step.
- You need to reason about partitions rather than direct equality only.
- The search logic is still O(log n), but the comparison rules change.

## Upgrade path / mental model
When the structure looks unusual, first identify what remains ordered after every split.

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Rotated sorted arrays** - P02, P06, P07, P09, P38
- **Parity and index-pattern search** - P01, P04, P05, P08, P14, P15, P18
- **2D matrix search** - P10, P11, P13, P30
- **Partitioning two sorted arrays** - P12, P37
- **Hidden structure or interactive API** - P16, P17, P28, P29, P39
- **Prefix sums plus binary search (weighted pick)** - P19, P20
- **Binary search inside a data structure design** - P21, P31, P49, P50
- **Sorted intervals and events lookup** - P22, P23, P46, P47, P48
- **Binary search on the value range** - P24, P27, P32, P33, P34, P44
- **Sort, then nearest-value lookup** - P25, P26, P35, P36
- **LIS with binary search** - P40, P41, P42
- **Convex cost (ternary-style) search** - P03, P43, P45

## Problems in this folder

### Easy
- `P01_FixedPointAdvanced.java` - Binary search on the hidden monotonic quantity arr[i] - i.
- `P02_RotationCountInRotatedSortedArrayAdvanced.java` - The number of rotations equals the index of the minimum element.
- `P03_MaximumInBitonicArrayAdvanced.java` - Compare mid with its right neighbor to decide which slope you are on.
- `P04_ElementAppearingMoreThan25PercentInSortedArrayAdvanced.java` - The answer must sit at index n/4, n/2, or 3n/4; verify each candidate with a lower-bound search.
- `P05_SmallestMissingNumberInSortedArrayAdvanced.java` - Find the first index where nums[i] is no longer equal to i.

### Medium
- `P06_SearchInRotatedSortedArrayAdvanced.java` - Find the sorted half first, then decide which side can contain the target.
- `P07_FindMinimumInRotatedSortedArrayAdvanced.java` - Search for the rotation point by comparing mid with right.
- `P08_SingleElementInSortedArrayAdvanced.java` - Exploit the even-odd pair alignment around the single element.
- `P09_SearchInRotatedSortedArrayIIAdvanced.java` - Handle duplicates by shrinking ambiguous boundaries before applying rotated search logic.
- `P10_SearchA2DMatrixIIAdvanced.java` - Staircase search from the top-right corner eliminates a row or column per step.
- `P11_FindPeakElementIIAdvanced.java` - Binary search columns using each column's maximum to pick a direction.
- `P12_KthElementOfTwoSortedArraysAdvanced.java` - Binary search the partition point that splits off the k smallest elements.
- `P13_MedianOfRowWiseSortedMatrixAdvanced.java` - Search the value and count with an upper bound in every row.
- `P14_SearchInNearlySortedArrayAdvanced.java` - Check mid and both neighbors, then skip two positions.
- `P15_SparseSearchAdvanced.java` - When mid lands on an empty string, slide to the nearest non-empty one before comparing.
- `P16_CountCompleteTreeNodesAdvanced.java` - Binary search the last existing position on the bottom level.
- `P17_KthSymbolInGrammarAdvanced.java` - Halve the row: the second half is the bitwise complement of the first half.
- `P18_MissingElementInSortedArrayAdvanced.java` - Search on the derived count of numbers missing before each index.
- `P19_RandomPickWithWeightAdvanced.java` - Lower-bound a random target in the prefix sums of the weights.
- `P20_RandomPointInNonOverlappingRectanglesAdvanced.java` - Weight each rectangle by its number of integer points, pick a global point rank, and locate it by binary search.
- `P21_SnapshotArrayAdvanced.java` - Keep per-index change history and floor-search it by snap id.
- `P22_FindRightIntervalAdvanced.java` - Sort starts with their indices and lower-bound each interval's end.
- `P23_TwoBestNonOverlappingEventsAdvanced.java` - Sort by start, keep a suffix maximum of values, and binary search the first event starting after each end.
- `P24_MaximumValueAtGivenIndexAdvanced.java` - Search the peak value using a closed-form minimum array sum.
- `P25_SearchSuggestionsSystemAdvanced.java` - Sort products and lower-bound each typed prefix.
- `P26_ShortestDistanceToTargetColorAdvanced.java` - Keep the sorted positions of each color and check the neighbors of the query index in that list.
- `P27_FindTheDuplicateNumberAdvanced.java` - Search the value range and count elements <= mid to apply the pigeonhole principle.
- `P28_ExponentialSearchAdvanced.java` - Gallop to bracket the target, then binary search inside the bracket.
- `P29_FindTheIndexOfTheLargeIntegerAdvanced.java` - Compare the sums of two equal halves through the API and keep the heavier half.
- `P30_LeftmostColumnWithAtLeastAOneAdvanced.java` - Lower-bound each row inside a search range that shrinks as better columns are found.
- `P31_MyCalendarAdvanced.java` - Use an ordered map to check only the previous and next events for overlap.
- `P32_SumOfMutatedArrayClosestToTargetAdvanced.java` - Find the first cap whose sum reaches the target, then compare it with its left neighbor.
- `P33_NthDigitAdvanced.java` - Skip whole blocks of 1-digit, 2-digit, 3-digit numbers, then index into the right number.
- `P34_KthSmallestPrimeFractionAdvanced.java` - Search a real threshold and count fractions below it with a two-pointer sweep.
- `P35_MinimumAbsoluteSumDifferenceAdvanced.java` - Find the closest replacement for each index with a lower bound on a sorted copy.
- `P36_MinimumAbsoluteDifferenceBetweenElementsWithConstraintAdvanced.java` - Insert elements that are at least x indices behind into an ordered set and query floor and ceiling.

### Hard
- `P37_MedianOfTwoSortedArraysAdvanced.java` - Binary search the partition of the smaller array until both sides are valid.
- `P38_FindMinimumInRotatedSortedArrayIIAdvanced.java` - Handle duplicates by shrinking the right end when mid and right are equal.
- `P39_FindInMountainArrayAdvanced.java` - Locate the peak, then search the ascending and descending halves.
- `P40_RussianDollEnvelopesAdvanced.java` - Sort by width, then run binary-search LIS on heights.
- `P41_MinimumOperationsToMakeTheArrayKIncreasingAdvanced.java` - Split into k interleaved chains and keep the longest non-decreasing subsequence of each.
- `P42_MinimumOperationsToMakeASubsequenceAdvanced.java` - Map arr to positions in target; the longest common subsequence becomes a longest increasing subsequence.
- `P43_MinimumCostToMakeArrayEqualAdvanced.java` - Search the minimum of a convex cost function by comparing neighboring values.
- `P44_SmallestGoodBaseAdvanced.java` - Try every possible count of 1-digits from most to fewest and bisect the base for each.
- `P45_BestPositionForAServiceCentreAdvanced.java` - The total distance is convex in x and in y, so nest one ternary search inside another.
- `P46_MaximumProfitInJobSchedulingAdvanced.java` - Sort by end time and binary search the last compatible job inside the DP.
- `P47_MaximumNumberOfEventsThatCanBeAttendedIIAdvanced.java` - Sort by start; DP over (event, picks left) with binary search for the next compatible event.
- `P48_NumberOfFlowersInFullBloomAdvanced.java` - Flowers blooming at time t = (starts <= t) - (ends < t), each counted with a binary search.
- `P49_RangeModuleAdvanced.java` - Keep disjoint intervals in an ordered map and use floor / lower lookups to find the ones a call touches.
- `P50_DataStreamAsDisjointIntervalsAdvanced.java` - Store intervals in an ordered map and merge a new number with its left and right neighbors.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P06_SearchInRotatedSortedArrayAdvanced.java
java P06_SearchInRotatedSortedArrayAdvanced
```
