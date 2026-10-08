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

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Minimize a rate or capacity (feasibility by summing)** - P02, P03, P06, P11, P35
- **Minimize the maximum load** - P07, P12, P13, P21, P37
- **Maximize the minimum** - P05, P22, P23, P46, P47
- **Maximize a size or count under a budget** - P01, P08, P09, P14, P15, P32, P44
- **Minimize the time until the work is done** - P04, P10, P16, P17
- **K-th smallest by counting** - P19, P20, P40, P41
- **Counting with inclusion-exclusion** - P24, P26, P48, P49
- **Feasibility checked by greedy, DP, or graph search** - P18, P27, P30, P31, P33, P34, P38, P39, P45
- **Real-valued answers** - P36, P42, P50
- **Threshold search followed by a closed-form sum** - P25
- **Binary search on a length** - P28, P29, P43

## Problems in this folder

### Medium
- `P01_HIndexBinarySearchAnswer.java` - Guess h and check that at least h papers have h or more citations.
- `P02_KokoEatingBananasBinarySearchAnswer.java` - Binary search the minimum feasible eating speed.
- `P03_ShipPackagesWithinDDaysBinarySearchAnswer.java` - Binary search the smallest ship capacity that passes a simulation.
- `P04_MinimumDaysToMakeMBouquetsBinarySearchAnswer.java` - Search the earliest day that makes enough adjacent bouquets possible.
- `P05_MagneticForceBetweenTwoBallsBinarySearchAnswer.java` - Binary search the largest feasible minimum spacing.
- `P06_SmallestDivisorGivenThresholdBinarySearchAnswer.java` - Search the divisor; a larger divisor never increases the rounded-up sum.
- `P07_MinimizedMaximumOfProductsBinarySearchAnswer.java` - Search the per-store cap and count the stores each product needs.
- `P08_MaximumCandiesAllocatedToKChildrenBinarySearchAnswer.java` - Search the largest equal share that still serves k children.
- `P09_WoodCuttingMaximumSawHeightBinarySearchAnswer.java` - Raise the saw as high as possible while the wood cut above it still meets the demand.
- `P10_MinimumTimeToCompleteTripsBinarySearchAnswer.java` - Search the time limit and count trips completed within it.
- `P11_MinimumSpeedToArriveOnTimeBinarySearchAnswer.java` - Search the speed with rounded-up waits on every ride but the last.
- `P12_MinimumLimitOfBallsInBagBinarySearchAnswer.java` - Search the penalty and count the splits required to reach it.
- `P13_AllocateBooksBinarySearchAnswer.java` - Guess the page limit and greedily count how many students are needed under it.
- `P14_CuttingRibbonsBinarySearchAnswer.java` - Search the longest piece length that yields at least k pieces.
- `P15_MaximumNumberOfAlloysBinarySearchAnswer.java` - For each machine, bisect the number of alloys whose missing metal still fits the budget.
- `P16_MinimumTimeToRepairCarsBinarySearchAnswer.java` - Search the time and sum each mechanic's sqrt-based capacity.
- `P17_MinimumSecondsToMakeMountainHeightZeroBinarySearchAnswer.java` - Guess the time and add up how much height each worker can remove within it.
- `P18_HouseRobberIVBinarySearchAnswer.java` - Search the capability and greedily count non-adjacent robbable houses.
- `P19_KthSmallestInSortedMatrixBinarySearchAnswer.java` - Search the value and count elements <= it with a staircase walk.
- `P20_KthSmallestSubarraySumBinarySearchAnswer.java` - Guess a sum and count the subarrays with sum at most that value using a sliding window.
- `P21_MinimizeMaximumOfArrayBinarySearchAnswer.java` - Search the maximum and check that every prefix sum fits under it.
- `P22_MaximumTastinessOfCandyBasketBinarySearchAnswer.java` - Search the minimum price gap and greedily pick candies that respect it.
- `P23_MaximizeScoreOfNumbersInRangesBinarySearchAnswer.java` - Guess the minimum gap and place one number per interval greedily from left to right.
- `P24_UglyNumberIIIBinarySearchAnswer.java` - Search the value whose inclusion-exclusion count of multiples reaches n.
- `P25_SellDiminishingValuedColoredBallsBinarySearchAnswer.java` - Bisect the price floor above which every ball is sold, then add arithmetic series.
- `P26_MinimizeTheMaximumOfTwoArraysBinarySearchAnswer.java` - Guess the maximum value and count how many numbers each array can use, sharing the rest.
- `P27_MaximumNumberOfRemovableCharactersBinarySearchAnswer.java` - Search the number of removals and re-run a subsequence check.
- `P28_LongestRepeatingSubstringBinarySearchAnswer.java` - Bisect the length; a length is feasible if some substring of that length appears twice.
- `P29_MinimumSizeSubarraySumBinarySearchAnswer.java` - Bisect the window length and test whether any window of that length reaches the target.
- `P30_PathWithMinimumEffortBinarySearchAnswer.java` - Search the effort limit and test reachability with BFS.
- `P31_FindTheSafestPathInAGridBinarySearchAnswer.java` - Precompute each cell's distance to the nearest thief, then bisect the safeness a path can keep.
- `P32_MaximumSideLengthOfSquareBinarySearchAnswer.java` - Search the side length and test squares with a 2D prefix sum.
- `P33_MinimizeMaximumDifferenceOfPairsBinarySearchAnswer.java` - Search the difference limit and greedily pair sorted neighbors.
- `P34_MaximumNumberOfMarkedIndicesBinarySearchAnswer.java` - Guess the number of pairs k and match the k smallest values with the k largest in order.
- `P35_HeatersBinarySearchAnswer.java` - Search the radius and sweep houses against heaters to test coverage.
- `P36_SeparateSquaresBinarySearchAnswer.java` - Bisect the height of a horizontal line until the area below it is half of the total.

### Hard
- `P37_SplitArrayLargestSumBinarySearchAnswer.java` - Binary search the minimal largest allowed partition sum.
- `P38_SwimInRisingWaterBinarySearchAnswer.java` - Bisect the water level and run a flood fill restricted to cells at or below that level.
- `P39_LastDayWhereYouCanStillCrossBinarySearchAnswer.java` - Bisect the day; for each candidate flood the first `day` cells and BFS from the top row.
- `P40_KthSmallestInMultiplicationTableBinarySearchAnswer.java` - Search the value and count table entries <= it row by row.
- `P41_KthSmallestPairDistanceBinarySearchAnswer.java` - Search the distance and count close pairs with a sliding window.
- `P42_MinimizeMaxDistanceToGasStationBinarySearchAnswer.java` - Real-valued search on the largest allowed gap.
- `P43_MaximumNumberOfRobotsWithinBudgetBinarySearchAnswer.java` - Bisect the number of consecutive robots; check each size with a window sum and a window maximum.
- `P44_MaximumRunningTimeOfNComputersBinarySearchAnswer.java` - Search the run time; each battery contributes at most that many minutes.
- `P45_MaximumNumberOfTasksYouCanAssignBinarySearchAnswer.java` - Guess k, then match the k easiest tasks to the k strongest workers, hardest task first.
- `P46_DivideChocolateBinarySearchAnswer.java` - Maximize the minimum piece by greedily cutting once a piece is sweet enough.
- `P47_MaximizeTheMinimumPoweredCityBinarySearchAnswer.java` - Guess the minimum power and add stations greedily as far right as possible, tracked by a difference array.
- `P48_NthMagicalNumberBinarySearchAnswer.java` - Search the value whose count of multiples of a or b reaches n.
- `P49_PreimageSizeOfFactorialZeroesFunctionBinarySearchAnswer.java` - Bisect the smallest x whose factorial has at least k trailing zeros; the answer is 5 or 0.
- `P50_MaximumAverageSubarrayIIBinarySearchAnswer.java` - Search the average over real numbers and test it with shifted prefix sums.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P02_KokoEatingBananasBinarySearchAnswer.java
java P02_KokoEatingBananasBinarySearchAnswer
```
