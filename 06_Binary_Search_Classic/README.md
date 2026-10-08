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

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Exact match search** - P01, P20, P21, P22, P30, P31, P38, P43
- **Lower bound and upper bound** - P02, P03, P08, P15, P16, P18, P19, P23, P27
- **First true in a monotonic predicate** - P05, P06, P17, P33, P34
- **Integer math by bisection** - P04, P07, P11, P12
- **Counting with one binary search per row or query** - P09, P10, P25, P40, P41, P42
- **Search on an index property** - P13, P14, P28, P39
- **Sort or prefix first, then answer queries** - P26, P44, P45, P46
- **Search in timestamped data** - P35, P48
- **Closest elements** - P24, P32
- **Patience sorting (tails array)** - P47, P49, P50
- **Bisection on real numbers** - P29
- **Search in index lists** - P36, P37

## Problems in this folder

### Easy
- `P01_BinarySearchClassic.java` - Standard log n search on a sorted array.
- `P02_SearchInsertPositionBinarySearch.java` - Find the first index where target can appear.
- `P03_ImplementLowerAndUpperBoundBinarySearch.java` - The two templates every other boundary search is built from.
- `P04_SqrtXBinarySearch.java` - Search the numeric answer space rather than an array index.
- `P05_FirstBadVersionBinarySearch.java` - Find the first true in a good-then-bad sequence.
- `P06_GuessNumberHigherOrLowerBinarySearch.java` - Halve the range using higher/lower feedback from an API.
- `P07_ValidPerfectSquareBinarySearch.java` - Binary search the integer whose square equals the input.
- `P08_NextGreatestLetterBinarySearch.java` - Upper-bound search with wrap-around.
- `P09_CountNegativesInSortedMatrixBinarySearch.java` - Find the first negative in each sorted row.
- `P10_TheKWeakestRowsInAMatrixBinarySearch.java` - Binary search each row for its first 0 to count soldiers, then sort rows by strength.
- `P11_ArrangingCoinsBinarySearch.java` - Find the largest row count whose triangular number still fits.
- `P12_NthRootOfANumberBinarySearch.java` - Bisect on the candidate root and compare candidate^n against m with an overflow guard.
- `P13_KthMissingPositiveNumberBinarySearch.java` - Binary search on the count of missing numbers before each index.
- `P14_MissingNumberInArithmeticProgressionBinarySearch.java` - Find the first index whose value no longer equals first + index * step.
- `P15_CountOccurrencesInSortedArrayBinarySearch.java` - Subtract two lower bounds to count a target's block.
- `P16_FindTargetIndicesAfterSortingArrayBinarySearch.java` - After sorting, the target occupies the index range [lowerBound, upperBound).
- `P17_SpecialArrayWithXElementsBinarySearch.java` - Count elements >= x with a lower bound and test each candidate x.
- `P18_MaximumCountOfPositiveAndNegativeBinarySearch.java` - Locate the end of the negatives and the start of the positives with two lower bounds.
- `P19_CheckIfANumberIsMajorityElementInASortedArrayBinarySearch.java` - Find the first occurrence and check whether the element n / 2 positions later is still the target.
- `P20_IntersectionOfTwoArraysBinarySearch.java` - Sort one array and binary search each distinct value of the other.
- `P21_FairCandySwapBinarySearch.java` - Derive the exact box Bob must give for each of Alice's boxes and binary search for it.
- `P22_MinimumCommonValueBinarySearch.java` - Walk the first sorted array in order and binary search each value in the second.
- `P23_FloorAndCeilInSortedArrayBinarySearch.java` - Record the last value passed on each side while searching for x.
- `P24_ClosestNumberInSortedArrayBinarySearch.java` - Find the insert position; the closest value is one of its two neighbors.
- `P25_FindTheDistanceValueBetweenTwoArraysBinarySearch.java` - Check only the first element >= x - d to see whether anything lies within distance d.
- `P26_LongestSubsequenceWithLimitedSumBinarySearch.java` - Sort, prefix-sum, and upper-bound each query.

### Medium
- `P27_FindFirstAndLastPositionBinarySearch.java` - Use binary search twice to locate both boundaries.
- `P28_PeakElementBinarySearch.java` - Use slope information to know which side must contain a peak.
- `P29_SquareRootWithPrecisionBinarySearch.java` - Halve a real interval a fixed number of times instead of looping until low meets high.
- `P30_SearchA2DMatrixBinarySearch.java` - Treat the matrix as one sorted list and map indices to cells.
- `P31_FindSmallestCommonElementInAllRowsBinarySearch.java` - Take candidates from the first row in order and binary search each one in every other row.
- `P32_FindKClosestElementsBinarySearch.java` - Binary search the start of the best length-k window.
- `P33_HIndexIIBinarySearch.java` - Find the first index where citations[i] >= n - i.
- `P34_FindPositiveIntegerSolutionForAGivenEquationBinarySearch.java` - For each x, binary search the y where the increasing hidden function hits z.
- `P35_TimeBasedKeyValueStoreBinarySearch.java` - Floor search over each key's timestamp-ordered history.
- `P36_RangeFrequencyQueriesBinarySearch.java` - Store the sorted positions of every value and count positions inside [left, right] with two bounds.
- `P37_NumberOfMatchingSubsequencesBinarySearch.java` - For each word, jump through s by binary searching the next occurrence of each letter.
- `P38_SearchInSortedArrayOfUnknownSizeBinarySearch.java` - Double the right bound until it covers the target, then search.
- `P39_PeakIndexInMountainArrayBinarySearch.java` - Use the uphill/downhill comparison as the search predicate.
- `P40_SuccessfulPairsOfSpellsAndPotionsBinarySearch.java` - Sort potions and lower-bound the first successful one per spell.
- `P41_CountTheNumberOfFairPairsBinarySearch.java` - Sort, then for each element count partners to its right inside [lower - x, upper - x] with two bounds.
- `P42_MaximumDistanceBetweenAPairOfValuesBinarySearch.java` - For each i, binary search the last j in the non-increasing second array with nums2[j] >= nums1[i].
- `P43_TwoSumIISortedBinarySearch.java` - For each number, binary search its complement in the part to its right.
- `P44_MostBeautifulItemForEachQueryBinarySearch.java` - Sort by price, keep a running best beauty, and upper-bound each query.
- `P45_CompareStringsByFrequencyOfTheSmallestCharacterBinarySearch.java` - Reduce every string to one number, sort the word numbers, and upper-bound each query.
- `P46_MinimumOperationsToMakeAllArrayElementsEqualBinarySearch.java` - Sort with prefix sums; a binary search splits the array into "raise these" and "lower these".
- `P47_LongestIncreasingSubsequenceBinarySearch.java` - Keep the smallest tail per length and place each number with a lower bound.
- `P48_OnlineElectionBinarySearch.java` - Precompute the leader after every vote and find the last vote at or before the query time.

### Hard
- `P49_FindTheLongestValidObstacleCourseAtEachPositionBinarySearch.java` - Non-decreasing LIS ending at every index, using an upper-bound search on the tails array.
- `P50_MinimumNumberOfRemovalsToMakeMountainArrayBinarySearch.java` - Compute the LIS ending at each index from both directions and pick the best peak.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

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
