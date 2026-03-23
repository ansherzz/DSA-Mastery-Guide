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
- `SearchInRotatedSortedArrayAdvanced.java` - Find the sorted half first, then decide which side can contain the target.
- `FindMinimumInRotatedSortedArrayAdvanced.java` - Search for the rotation point by comparing mid with right.
- `SingleElementInSortedArrayAdvanced.java` - Exploit the even-odd pair alignment around the single element.
- `MedianOfTwoSortedArraysAdvanced.java` - Binary search the partition of the smaller array until both sides are valid.
- `SearchInRotatedSortedArrayIIAdvanced.java` - Handle duplicates by shrinking ambiguous boundaries before applying rotated search logic.


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
