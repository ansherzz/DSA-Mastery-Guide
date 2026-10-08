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

## Problems in this folder

### Easy
- `P01_FixedPointAdvanced.java` - Binary search on the hidden monotonic quantity arr[i] - i.

### Medium
- `P02_SearchInRotatedSortedArrayAdvanced.java` - Find the sorted half first, then decide which side can contain the target.
- `P03_FindMinimumInRotatedSortedArrayAdvanced.java` - Search for the rotation point by comparing mid with right.
- `P04_SingleElementInSortedArrayAdvanced.java` - Exploit the even-odd pair alignment around the single element.
- `P05_SearchInRotatedSortedArrayIIAdvanced.java` - Handle duplicates by shrinking ambiguous boundaries before applying rotated search logic.
- `P06_SearchA2DMatrixIIAdvanced.java` - Staircase search from the top-right corner eliminates a row or column per step.
- `P07_FindPeakElementIIAdvanced.java` - Binary search columns using each column's maximum to pick a direction.
- `P08_KthElementOfTwoSortedArraysAdvanced.java` - Binary search the partition point that splits off the k smallest elements.
- `P09_MedianOfRowWiseSortedMatrixAdvanced.java` - Search the value and count with an upper bound in every row.
- `P10_SearchInNearlySortedArrayAdvanced.java` - Check mid and both neighbors, then skip two positions.
- `P11_CountCompleteTreeNodesAdvanced.java` - Binary search the last existing position on the bottom level.
- `P12_MissingElementInSortedArrayAdvanced.java` - Search on the derived count of numbers missing before each index.
- `P13_RandomPickWithWeightAdvanced.java` - Lower-bound a random target in the prefix sums of the weights.
- `P14_SnapshotArrayAdvanced.java` - Keep per-index change history and floor-search it by snap id.
- `P15_FindRightIntervalAdvanced.java` - Sort starts with their indices and lower-bound each interval's end.
- `P16_MaximumValueAtGivenIndexAdvanced.java` - Search the peak value using a closed-form minimum array sum.
- `P17_SearchSuggestionsSystemAdvanced.java` - Sort products and lower-bound each typed prefix.
- `P18_FindTheDuplicateNumberAdvanced.java` - Search the value range and count elements <= mid to apply the pigeonhole principle.
- `P19_ExponentialSearchAdvanced.java` - Gallop to bracket the target, then binary search inside the bracket.
- `P20_LeftmostColumnWithAtLeastAOneAdvanced.java` - Lower-bound each row inside a search range that shrinks as better columns are found.
- `P21_MyCalendarAdvanced.java` - Use an ordered map to check only the previous and next events for overlap.
- `P22_SumOfMutatedArrayClosestToTargetAdvanced.java` - Find the first cap whose sum reaches the target, then compare it with its left neighbor.
- `P23_KthSmallestPrimeFractionAdvanced.java` - Search a real threshold and count fractions below it with a two-pointer sweep.
- `P24_MinimumAbsoluteSumDifferenceAdvanced.java` - Find the closest replacement for each index with a lower bound on a sorted copy.

### Hard
- `P25_MedianOfTwoSortedArraysAdvanced.java` - Binary search the partition of the smaller array until both sides are valid.
- `P26_FindMinimumInRotatedSortedArrayIIAdvanced.java` - Handle duplicates by shrinking the right end when mid and right are equal.
- `P27_FindInMountainArrayAdvanced.java` - Locate the peak, then search the ascending and descending halves.
- `P28_RussianDollEnvelopesAdvanced.java` - Sort by width, then run binary-search LIS on heights.
- `P29_MinimumCostToMakeArrayEqualAdvanced.java` - Search the minimum of a convex cost function by comparing neighboring values.
- `P30_MaximumProfitInJobSchedulingAdvanced.java` - Sort by end time and binary search the last compatible job inside the DP.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P02_SearchInRotatedSortedArrayAdvanced.java
java P02_SearchInRotatedSortedArrayAdvanced
```
