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
- `ValidPalindromeTwoPointers.java` - Move inward from both ends and compare only useful characters.
- `ContainerWithMostWaterTwoPointers.java` - Use two ends and always move the shorter wall.
- `ThreeSumTwoPointers.java` - Sort once, then solve each fixed-element case with two pointers.
- `RemoveDuplicatesFromSortedArrayTwoPointers.java` - Exploit sorted order to compress duplicates in-place.
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
