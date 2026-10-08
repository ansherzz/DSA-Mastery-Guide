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
- `P01_KokoEatingBananasBinarySearchAnswer.java` - Binary search the minimum feasible eating speed.
- `P02_ShipPackagesWithinDDaysBinarySearchAnswer.java` - Binary search the smallest ship capacity that passes a simulation.
- `P03_MinimumDaysToMakeMBouquetsBinarySearchAnswer.java` - Search the earliest day that makes enough adjacent bouquets possible.
- `P04_MagneticForceBetweenTwoBallsBinarySearchAnswer.java` - Binary search the largest feasible minimum spacing.
- `P05_SmallestDivisorGivenThresholdBinarySearchAnswer.java` - Search the divisor; a larger divisor never increases the rounded-up sum.
- `P06_MinimizedMaximumOfProductsBinarySearchAnswer.java` - Search the per-store cap and count the stores each product needs.
- `P07_MaximumCandiesAllocatedToKChildrenBinarySearchAnswer.java` - Search the largest equal share that still serves k children.
- `P08_MinimumTimeToCompleteTripsBinarySearchAnswer.java` - Search the time limit and count trips completed within it.
- `P09_MinimumSpeedToArriveOnTimeBinarySearchAnswer.java` - Search the speed with rounded-up waits on every ride but the last.
- `P10_MinimumLimitOfBallsInBagBinarySearchAnswer.java` - Search the penalty and count the splits required to reach it.
- `P11_CuttingRibbonsBinarySearchAnswer.java` - Search the longest piece length that yields at least k pieces.
- `P12_MinimumTimeToRepairCarsBinarySearchAnswer.java` - Search the time and sum each mechanic's sqrt-based capacity.
- `P13_HouseRobberIVBinarySearchAnswer.java` - Search the capability and greedily count non-adjacent robbable houses.
- `P14_KthSmallestInSortedMatrixBinarySearchAnswer.java` - Search the value and count elements <= it with a staircase walk.
- `P15_MinimizeMaximumOfArrayBinarySearchAnswer.java` - Search the maximum and check that every prefix sum fits under it.
- `P16_MaximumTastinessOfCandyBasketBinarySearchAnswer.java` - Search the minimum price gap and greedily pick candies that respect it.
- `P17_UglyNumberIIIBinarySearchAnswer.java` - Search the value whose inclusion-exclusion count of multiples reaches n.
- `P18_MaximumNumberOfRemovableCharactersBinarySearchAnswer.java` - Search the number of removals and re-run a subsequence check.
- `P19_PathWithMinimumEffortBinarySearchAnswer.java` - Search the effort limit and test reachability with BFS.
- `P20_MaximumSideLengthOfSquareBinarySearchAnswer.java` - Search the side length and test squares with a 2D prefix sum.
- `P21_MinimizeMaximumDifferenceOfPairsBinarySearchAnswer.java` - Search the difference limit and greedily pair sorted neighbors.
- `P22_HeatersBinarySearchAnswer.java` - Search the radius and sweep houses against heaters to test coverage.

### Hard
- `P23_SplitArrayLargestSumBinarySearchAnswer.java` - Binary search the minimal largest allowed partition sum.
- `P24_KthSmallestInMultiplicationTableBinarySearchAnswer.java` - Search the value and count table entries <= it row by row.
- `P25_KthSmallestPairDistanceBinarySearchAnswer.java` - Search the distance and count close pairs with a sliding window.
- `P26_MinimizeMaxDistanceToGasStationBinarySearchAnswer.java` - Real-valued search on the largest allowed gap.
- `P27_MaximumRunningTimeOfNComputersBinarySearchAnswer.java` - Search the run time; each battery contributes at most that many minutes.
- `P28_DivideChocolateBinarySearchAnswer.java` - Maximize the minimum piece by greedily cutting once a piece is sweet enough.
- `P29_NthMagicalNumberBinarySearchAnswer.java` - Search the value whose count of multiples of a or b reaches n.
- `P30_MaximumAverageSubarrayIIBinarySearchAnswer.java` - Search the average over real numbers and test it with shifted prefix sums.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P01_KokoEatingBananasBinarySearchAnswer.java
java P01_KokoEatingBananasBinarySearchAnswer
```
