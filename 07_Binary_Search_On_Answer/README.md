# 07. Binary Search on Answer

## What this pattern is
Sometimes you do not binary search an index; you binary search the value of the answer. The trick is proving a yes/no feasibility check is monotonic.

## When to use it
- When the prompt asks for minimum possible maximum, maximum possible minimum, or smallest feasible value.
- When you can write a helper that checks if a candidate answer works.
- When increasing the answer only helps or only hurts feasibility.
- When brute force over answer values is too slow.

## How to identify it in interviews
- Words like minimum speed, smallest capacity, or largest minimum appear.
- A candidate answer can be validated greedily in linear time.
- If x works, then every larger value works, or the reverse.
- The actual answer is numeric even though the array is not the search target.

## Upgrade path / mental model
Before coding, say the monotonic rule out loud: if this value works, what happens to larger or smaller values?

## Problems in this folder
- `KokoEatingBananasBinarySearchAnswer.java` - Binary search the minimum feasible eating speed.
- `ShipPackagesWithinDDaysBinarySearchAnswer.java` - Binary search the smallest ship capacity that passes a simulation.
- `SplitArrayLargestSumBinarySearchAnswer.java` - Binary search the minimal largest allowed partition sum.
- `MinimumDaysToMakeMBouquetsBinarySearchAnswer.java` - Search the earliest day that makes enough adjacent bouquets possible.
- `MagneticForceBetweenTwoBallsBinarySearchAnswer.java` - Binary search the largest feasible minimum spacing.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac KokoEatingBananasBinarySearchAnswer.java
java KokoEatingBananasBinarySearchAnswer
```
