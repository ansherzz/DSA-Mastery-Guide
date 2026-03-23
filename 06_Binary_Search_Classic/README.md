# 06. Binary Search - Classic Variations

## What this pattern is
Classic binary search repeatedly halves a monotonic search space. Variations answer exact lookup, boundary finding, or monotonic decisions on sorted data.

## When to use it
- When data is sorted or a monotonic condition exists.
- When you need first/last position, lower bound, upper bound, or insertion point.
- When a linear scan is too slow and comparisons guide direction.
- When you can discard half the answers after one check.

## How to identify it in interviews
- The input is sorted or nearly sorted.
- The prompt asks for log n time.
- You can define a mid index and decide which half can be ignored.
- The answer is a boundary in a monotonic structure.

## Upgrade path / mental model
State the invariant: is your search range inclusive, and what does it mean when the loop ends?

## Problems in this folder
- `BinarySearchClassic.java` - Standard log n search on a sorted array.
- `SearchInsertPositionBinarySearch.java` - Find the first index where target can appear.
- `FindFirstAndLastPositionBinarySearch.java` - Use binary search twice to locate both boundaries.
- `SqrtXBinarySearch.java` - Search the numeric answer space rather than an array index.
- `PeakElementBinarySearch.java` - Use slope information to know which side must contain a peak.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac BinarySearchClassic.java
java BinarySearchClassic
```
