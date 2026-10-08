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
- `P01_BinarySearchClassic.java` - Standard log n search on a sorted array.
- `P02_SearchInsertPositionBinarySearch.java` - Find the first index where target can appear.
- `P03_SqrtXBinarySearch.java` - Search the numeric answer space rather than an array index.
- `P04_FirstBadVersionBinarySearch.java` - Find the first true in a good-then-bad sequence.
- `P05_GuessNumberHigherOrLowerBinarySearch.java` - Halve the range using higher/lower feedback from an API.
- `P06_ValidPerfectSquareBinarySearch.java` - Binary search the integer whose square equals the input.
- `P07_NextGreatestLetterBinarySearch.java` - Upper-bound search with wrap-around.
- `P08_CountNegativesInSortedMatrixBinarySearch.java` - Find the first negative in each sorted row.
- `P09_ArrangingCoinsBinarySearch.java` - Find the largest row count whose triangular number still fits.
- `P10_KthMissingPositiveNumberBinarySearch.java` - Binary search on the count of missing numbers before each index.
- `P11_CountOccurrencesInSortedArrayBinarySearch.java` - Subtract two lower bounds to count a target's block.
- `P12_SpecialArrayWithXElementsBinarySearch.java` - Count elements >= x with a lower bound and test each candidate x.
- `P13_MaximumCountOfPositiveAndNegativeBinarySearch.java` - Locate the end of the negatives and the start of the positives with two lower bounds.
- `P14_IntersectionOfTwoArraysBinarySearch.java` - Sort one array and binary search each distinct value of the other.
- `P15_FloorAndCeilInSortedArrayBinarySearch.java` - Record the last value passed on each side while searching for x.
- `P16_FindTheDistanceValueBetweenTwoArraysBinarySearch.java` - Check only the first element >= x - d to see whether anything lies within distance d.
- `P17_LongestSubsequenceWithLimitedSumBinarySearch.java` - Sort, prefix-sum, and upper-bound each query.

### Medium
- `P18_FindFirstAndLastPositionBinarySearch.java` - Use binary search twice to locate both boundaries.
- `P19_PeakElementBinarySearch.java` - Use slope information to know which side must contain a peak.
- `P20_SearchA2DMatrixBinarySearch.java` - Treat the matrix as one sorted list and map indices to cells.
- `P21_FindKClosestElementsBinarySearch.java` - Binary search the start of the best length-k window.
- `P22_HIndexIIBinarySearch.java` - Find the first index where citations[i] >= n - i.
- `P23_TimeBasedKeyValueStoreBinarySearch.java` - Floor search over each key's timestamp-ordered history.
- `P24_SearchInSortedArrayOfUnknownSizeBinarySearch.java` - Double the right bound until it covers the target, then search.
- `P25_PeakIndexInMountainArrayBinarySearch.java` - Use the uphill/downhill comparison as the search predicate.
- `P26_SuccessfulPairsOfSpellsAndPotionsBinarySearch.java` - Sort potions and lower-bound the first successful one per spell.
- `P27_TwoSumIISortedBinarySearch.java` - For each number, binary search its complement in the part to its right.
- `P28_MostBeautifulItemForEachQueryBinarySearch.java` - Sort by price, keep a running best beauty, and upper-bound each query.
- `P29_LongestIncreasingSubsequenceBinarySearch.java` - Keep the smallest tail per length and place each number with a lower bound.
- `P30_OnlineElectionBinarySearch.java` - Precompute the leader after every vote and find the last vote at or before the query time.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P01_BinarySearchClassic.java
java P01_BinarySearchClassic
```
