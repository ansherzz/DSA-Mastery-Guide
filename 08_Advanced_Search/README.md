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
- `FixedPointAdvanced.java` - Binary search on the hidden monotonic quantity arr[i] - i.

### Medium
- `SearchInRotatedSortedArrayAdvanced.java` - Find the sorted half first, then decide which side can contain the target.
- `FindMinimumInRotatedSortedArrayAdvanced.java` - Search for the rotation point by comparing mid with right.
- `SingleElementInSortedArrayAdvanced.java` - Exploit the even-odd pair alignment around the single element.
- `SearchInRotatedSortedArrayIIAdvanced.java` - Handle duplicates by shrinking ambiguous boundaries before applying rotated search logic.
- `SearchA2DMatrixIIAdvanced.java` - Staircase search from the top-right corner eliminates a row or column per step.
- `FindPeakElementIIAdvanced.java` - Binary search columns using each column's maximum to pick a direction.
- `KthElementOfTwoSortedArraysAdvanced.java` - Binary search the partition point that splits off the k smallest elements.
- `MedianOfRowWiseSortedMatrixAdvanced.java` - Search the value and count with an upper bound in every row.
- `SearchInNearlySortedArrayAdvanced.java` - Check mid and both neighbors, then skip two positions.
- `CountCompleteTreeNodesAdvanced.java` - Binary search the last existing position on the bottom level.
- `MissingElementInSortedArrayAdvanced.java` - Search on the derived count of numbers missing before each index.
- `RandomPickWithWeightAdvanced.java` - Lower-bound a random target in the prefix sums of the weights.
- `SnapshotArrayAdvanced.java` - Keep per-index change history and floor-search it by snap id.
- `FindRightIntervalAdvanced.java` - Sort starts with their indices and lower-bound each interval's end.
- `MaximumValueAtGivenIndexAdvanced.java` - Search the peak value using a closed-form minimum array sum.
- `SearchSuggestionsSystemAdvanced.java` - Sort products and lower-bound each typed prefix.
- `FindTheDuplicateNumberAdvanced.java` - Search the value range and count elements <= mid to apply the pigeonhole principle.
- `ExponentialSearchAdvanced.java` - Gallop to bracket the target, then binary search inside the bracket.
- `LeftmostColumnWithAtLeastAOneAdvanced.java` - Lower-bound each row inside a search range that shrinks as better columns are found.
- `MyCalendarAdvanced.java` - Use an ordered map to check only the previous and next events for overlap.
- `SumOfMutatedArrayClosestToTargetAdvanced.java` - Find the first cap whose sum reaches the target, then compare it with its left neighbor.
- `KthSmallestPrimeFractionAdvanced.java` - Search a real threshold and count fractions below it with a two-pointer sweep.
- `MinimumAbsoluteSumDifferenceAdvanced.java` - Find the closest replacement for each index with a lower bound on a sorted copy.

### Hard
- `MedianOfTwoSortedArraysAdvanced.java` - Binary search the partition of the smaller array until both sides are valid.
- `FindMinimumInRotatedSortedArrayIIAdvanced.java` - Handle duplicates by shrinking the right end when mid and right are equal.
- `FindInMountainArrayAdvanced.java` - Locate the peak, then search the ascending and descending halves.
- `RussianDollEnvelopesAdvanced.java` - Sort by width, then run binary-search LIS on heights.
- `MinimumCostToMakeArrayEqualAdvanced.java` - Search the minimum of a convex cost function by comparing neighboring values.
- `MaximumProfitInJobSchedulingAdvanced.java` - Sort by end time and binary search the last compatible job inside the DP.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac SearchInRotatedSortedArrayAdvanced.java
java SearchInRotatedSortedArrayAdvanced
```
