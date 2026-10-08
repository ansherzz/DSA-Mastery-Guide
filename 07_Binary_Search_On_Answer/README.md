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

### Medium
- `KokoEatingBananasBinarySearchAnswer.java` - Binary search the minimum feasible eating speed.
- `ShipPackagesWithinDDaysBinarySearchAnswer.java` - Binary search the smallest ship capacity that passes a simulation.
- `MinimumDaysToMakeMBouquetsBinarySearchAnswer.java` - Search the earliest day that makes enough adjacent bouquets possible.
- `MagneticForceBetweenTwoBallsBinarySearchAnswer.java` - Binary search the largest feasible minimum spacing.
- `SmallestDivisorGivenThresholdBinarySearchAnswer.java` - Search the divisor; a larger divisor never increases the rounded-up sum.
- `MinimizedMaximumOfProductsBinarySearchAnswer.java` - Search the per-store cap and count the stores each product needs.
- `MaximumCandiesAllocatedToKChildrenBinarySearchAnswer.java` - Search the largest equal share that still serves k children.
- `MinimumTimeToCompleteTripsBinarySearchAnswer.java` - Search the time limit and count trips completed within it.
- `MinimumSpeedToArriveOnTimeBinarySearchAnswer.java` - Search the speed with rounded-up waits on every ride but the last.
- `MinimumLimitOfBallsInBagBinarySearchAnswer.java` - Search the penalty and count the splits required to reach it.
- `CuttingRibbonsBinarySearchAnswer.java` - Search the longest piece length that yields at least k pieces.
- `MinimumTimeToRepairCarsBinarySearchAnswer.java` - Search the time and sum each mechanic's sqrt-based capacity.
- `HouseRobberIVBinarySearchAnswer.java` - Search the capability and greedily count non-adjacent robbable houses.
- `KthSmallestInSortedMatrixBinarySearchAnswer.java` - Search the value and count elements <= it with a staircase walk.
- `MinimizeMaximumOfArrayBinarySearchAnswer.java` - Search the maximum and check that every prefix sum fits under it.
- `MaximumTastinessOfCandyBasketBinarySearchAnswer.java` - Search the minimum price gap and greedily pick candies that respect it.
- `UglyNumberIIIBinarySearchAnswer.java` - Search the value whose inclusion-exclusion count of multiples reaches n.
- `MaximumNumberOfRemovableCharactersBinarySearchAnswer.java` - Search the number of removals and re-run a subsequence check.
- `PathWithMinimumEffortBinarySearchAnswer.java` - Search the effort limit and test reachability with BFS.
- `MaximumSideLengthOfSquareBinarySearchAnswer.java` - Search the side length and test squares with a 2D prefix sum.
- `MinimizeMaximumDifferenceOfPairsBinarySearchAnswer.java` - Search the difference limit and greedily pair sorted neighbors.
- `HeatersBinarySearchAnswer.java` - Search the radius and sweep houses against heaters to test coverage.

### Hard
- `SplitArrayLargestSumBinarySearchAnswer.java` - Binary search the minimal largest allowed partition sum.
- `KthSmallestInMultiplicationTableBinarySearchAnswer.java` - Search the value and count table entries <= it row by row.
- `KthSmallestPairDistanceBinarySearchAnswer.java` - Search the distance and count close pairs with a sliding window.
- `MinimizeMaxDistanceToGasStationBinarySearchAnswer.java` - Real-valued search on the largest allowed gap.
- `MaximumRunningTimeOfNComputersBinarySearchAnswer.java` - Search the run time; each battery contributes at most that many minutes.
- `DivideChocolateBinarySearchAnswer.java` - Maximize the minimum piece by greedily cutting once a piece is sweet enough.
- `NthMagicalNumberBinarySearchAnswer.java` - Search the value whose count of multiples of a or b reaches n.
- `MaximumAverageSubarrayIIBinarySearchAnswer.java` - Search the average over real numbers and test it with shifted prefix sums.


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
