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

### Easy
- `BinarySearchClassic.java` - Standard log n search on a sorted array.
- `SearchInsertPositionBinarySearch.java` - Find the first index where target can appear.
- `SqrtXBinarySearch.java` - Search the numeric answer space rather than an array index.
- `FirstBadVersionBinarySearch.java` - Find the first true in a good-then-bad sequence.
- `GuessNumberHigherOrLowerBinarySearch.java` - Halve the range using higher/lower feedback from an API.
- `ValidPerfectSquareBinarySearch.java` - Binary search the integer whose square equals the input.
- `NextGreatestLetterBinarySearch.java` - Upper-bound search with wrap-around.
- `CountNegativesInSortedMatrixBinarySearch.java` - Find the first negative in each sorted row.
- `ArrangingCoinsBinarySearch.java` - Find the largest row count whose triangular number still fits.
- `KthMissingPositiveNumberBinarySearch.java` - Binary search on the count of missing numbers before each index.
- `CountOccurrencesInSortedArrayBinarySearch.java` - Subtract two lower bounds to count a target's block.

### Medium
- `FindFirstAndLastPositionBinarySearch.java` - Use binary search twice to locate both boundaries.
- `PeakElementBinarySearch.java` - Use slope information to know which side must contain a peak.
- `SearchA2DMatrixBinarySearch.java` - Treat the matrix as one sorted list and map indices to cells.
- `FindKClosestElementsBinarySearch.java` - Binary search the start of the best length-k window.
- `HIndexIIBinarySearch.java` - Find the first index where citations[i] >= n - i.
- `TimeBasedKeyValueStoreBinarySearch.java` - Floor search over each key's timestamp-ordered history.
- `SearchInSortedArrayOfUnknownSizeBinarySearch.java` - Double the right bound until it covers the target, then search.
- `PeakIndexInMountainArrayBinarySearch.java` - Use the uphill/downhill comparison as the search predicate.
- `SuccessfulPairsOfSpellsAndPotionsBinarySearch.java` - Sort potions and lower-bound the first successful one per spell.


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
