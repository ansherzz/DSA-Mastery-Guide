# 03. Two Pointers

## What this pattern is
Two pointers move through data from one or both ends while maintaining a useful relationship, often reducing an O(n^2) search to O(n).

## When to use it
- When the array is sorted or can be processed from both ends.
- When you compare pairs, shrink a search space, or remove duplicates in-place.
- When you need to validate mirrored characters in strings.
- When the best move depends on relative order or boundaries.

## How to identify it in interviews
- The data is sorted or symmetry matters.
- You can improve by moving left or right based on a comparison.
- The prompt asks for pairs, triplets, or in-place compression.
- One pass with two moving indices feels natural.

## Upgrade path / mental model
Always ask what condition tells you which pointer should move. That rule is the heart of the pattern.

## Problems in this folder

### Easy
- `ValidPalindromeTwoPointers.java` - Move inward from both ends and compare only useful characters.
- `RemoveDuplicatesFromSortedArrayTwoPointers.java` - Exploit sorted order to compress duplicates in-place.
- `RemoveElementTwoPointers.java` - Use read and write pointers to overwrite unwanted values in-place.
- `SquaresOfSortedArrayTwoPointers.java` - Fill the result from the back using the larger absolute value at either end.
- `ReverseVowelsTwoPointers.java` - Move inward from both ends and swap only the vowels.
- `IsSubsequenceTwoPointers.java` - Advance through both strings, matching characters greedily in order.
- `ValidPalindromeIITwoPointers.java` - At the first mismatch, try skipping either side once.
- `BackspaceStringCompareTwoPointers.java` - Walk both strings backward while resolving pending backspaces.

### Medium
- `ContainerWithMostWaterTwoPointers.java` - Use two ends and always move the shorter wall.
- `ThreeSumTwoPointers.java` - Sort once, then solve each fixed-element case with two pointers.
- `TwoSumIISortedTwoPointers.java` - Use sorted order to move the pointer that fixes the sum.
- `SortColorsTwoPointers.java` - Dutch National Flag partition with low, mid, and high pointers.
- `ThreeSumClosestTwoPointers.java` - Sort, fix one value, and track the nearest sum with two pointers.
- `FourSumTwoPointers.java` - Fix two values and solve the remaining pair with two pointers while skipping duplicates.
- `BoatsToSavePeopleTwoPointers.java` - Pair the lightest with the heaviest whenever they fit.
- `IntervalListIntersectionsTwoPointers.java` - Intersect the current pair and advance the interval that ends first.
- `NextPermutationTwoPointers.java` - Find the pivot from the right, swap with its successor, and reverse the suffix.
- `RemoveDuplicatesFromSortedArrayIITwoPointers.java` - Keep at most two copies by comparing against write - 2.
- `FindTheDuplicateNumberTwoPointers.java` - Treat values as next pointers and find the cycle entrance with slow and fast pointers.

### Hard
- `TrappingRainWaterTwoPointers.java` - Track best walls from both sides and process the limiting side.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac ValidPalindromeTwoPointers.java
java ValidPalindromeTwoPointers
```
