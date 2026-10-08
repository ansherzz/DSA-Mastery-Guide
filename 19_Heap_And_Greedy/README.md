# 19. Heap & Greedy

## What this pattern is
Heap problems repeatedly need the current best candidate, while greedy problems make locally optimal decisions backed by a proof or invariant. Many interview problems combine both.

## When to use it
- When you need top k, smallest or largest available, or next event in order.
- When scheduling, resource allocation, or interval processing appears.
- When a priority queue naturally surfaces the next best choice.
- When a local choice can be justified to lead to a global optimum.

## How to identify it in interviews
- Words like top k, next meeting to end, highest frequency, or always choose the best appear.
- You repeatedly extract min or max while processing data.
- A sorted order of events or priorities matters.
- A greedy invariant can be stated clearly.

## Upgrade path / mental model
Ask two questions: do I need a heap for dynamic best choice, and what invariant makes the greedy choice safe?

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Top K with a heap** - P01, P09, P18, P19
- **Repeatedly take the largest or smallest** - P02, P03, P07, P11
- **Greedy matching after sorting** - P04, P05, P37
- **Greedy simulation with a local choice** - P06, P08, P16, P29, P30
- **Reach and jump greedy** - P21, P22, P23, P49
- **Interval scheduling** - P25, P26, P27, P28, P31
- **Heap of resources over time** - P12, P13, P33, P34, P48
- **Frequency scheduling with a heap** - P14, P15, P17
- **K-way merge** - P35, P36, P40, P41
- **Two heaps** - P39, P42, P43
- **Regret greedy (heap of past choices)** - P32, P46, P47
- **Two-pass greedy** - P45
- **Greedy by sorting on a difference** - P10
- **Huffman-style merging** - P20
- **Working backwards greedily** - P24
- **Greedy with a custom comparator** - P38
- **Sort by a ratio and keep the best K in a heap** - P44
- **Heap-ordered boundary expansion** - P50

## Problems in this folder

### Easy
- `P01_KthLargestElementInStreamHeapGreedy.java` - Min-heap capped at size k whose top is the kth largest.
- `P02_LastStoneWeightHeapGreedy.java` - Max-heap simulation of repeatedly smashing the two heaviest stones.
- `P03_MaximizeSumOfArrayAfterKNegationsHeapGreedy.java` - Always negate the current smallest element, which a min-heap hands over each time.
- `P04_AssignCookiesHeapGreedy.java` - Sort both lists and give each child the smallest cookie that satisfies them.
- `P05_LargestPerimeterTriangleHeapGreedy.java` - Sort and test consecutive triples from the largest side downward.
- `P06_LemonadeChangeHeapGreedy.java` - Prefer giving a 10 as change to keep the more flexible 5s.
- `P07_MaximumUnitsOnATruckHeapGreedy.java` - Fill every slot with the richest box type still available.
- `P08_CanPlaceFlowersHeapGreedy.java` - Plant in the first legal plot each time; it never blocks a better arrangement.

### Medium
- `P09_TopKFrequentElementsHeapGreedy.java` - Count frequencies, then use a heap to extract the top k.
- `P10_TwoCitySchedulingHeapGreedy.java` - Sort people by how much cheaper city A is than city B; send the first half to A.
- `P11_MinimumOperationsToHalveArraySumHeapGreedy.java` - Halving the current largest number always removes the most, so use a max-heap.
- `P12_MeetingRoomsIIHeapGreedy.java` - Track active meeting end times in a min-heap to reuse rooms greedily.
- `P13_SeatReservationManagerHeapGreedy.java` - Keep returned seats in a min-heap and hand out fresh seat numbers only when the heap is empty.
- `P14_TaskSchedulerHeapGreedy.java` - Greedily execute the most frequent tasks using a max-heap and cooldown cycles.
- `P15_ReorganizeStringHeapGreedy.java` - Use the most frequent available character while holding back the last-used one.
- `P16_MinimumDeletionsToMakeCharacterFrequenciesUniqueHeapGreedy.java` - Process frequencies from high to low, lowering each one just below the previous kept frequency.
- `P17_LongestHappyStringHeapGreedy.java` - Always append the letter with the most copies left, unless it would create three in a row.
- `P18_KClosestPointsToOriginHeapGreedy.java` - Max-heap of size k ordered by squared distance.
- `P19_KthLargestElementInArrayHeapGreedy.java` - Keep the k largest values in a min-heap.
- `P20_MinimumCostToConnectSticksHeapGreedy.java` - Always join the two shortest sticks; a min-heap supplies them after every merge.
- `P21_JumpGameHeapGreedy.java` - Track the farthest reachable index in one pass.
- `P22_JumpGameIIHeapGreedy.java` - Count jumps by expanding reachable ranges like BFS levels.
- `P23_GasStationHeapGreedy.java` - Reset the start whenever the tank goes negative and check the total surplus.
- `P24_BrokenCalculatorHeapGreedy.java` - Turn the target back into the start: halve it when even, add one when odd.
- `P25_MergeIntervalsHeapGreedy.java` - Sort by start and extend or close the current interval.
- `P26_InsertIntervalHeapGreedy.java` - Copy intervals that end before the new one, merge everything that overlaps it, then copy the rest.
- `P27_NonOverlappingIntervalsHeapGreedy.java` - Sort by end and always keep the interval that finishes first.
- `P28_PartitionLabelsHeapGreedy.java` - Extend each part to the last occurrence of every letter inside it.
- `P29_BestTimeToBuyAndSellStockIIHeapGreedy.java` - Collect every upward day-to-day price move.
- `P30_WiggleSubsequenceHeapGreedy.java` - Count the direction changes: keep one element for every switch between rising and falling.
- `P31_MinimumNumberOfArrowsToBurstBalloonsHeapGreedy.java` - Sort by end and shoot at the end of the first balloon not yet burst.
- `P32_FurthestBuildingYouCanReachHeapGreedy.java` - Give every climb a ladder, then pay bricks for the smallest one when ladders run out.
- `P33_SingleThreadedCPUHeapGreedy.java` - Simulate time with a min-heap of available tasks keyed by processing time and index.
- `P34_MaximumNumberOfEventsThatCanBeAttendedHeapGreedy.java` - Sweep the days; each day attend the open event that ends soonest.
- `P35_FindKPairsWithSmallestSumsHeapGreedy.java` - K-way merge the sorted pair lists with a min-heap of list heads.
- `P36_KthSmallestElementInSortedMatrixHeapGreedy.java` - Treat each row as a sorted list and pop from a heap of row heads k times.
- `P37_QueueReconstructionByHeightHeapGreedy.java` - Place the tallest people first and insert each person at index k.
- `P38_LargestNumberHeapGreedy.java` - Order two numbers by which concatenation is larger: a before b when "ab" > "ba".
- `P39_TotalCostToHireKWorkersHeapGreedy.java` - Keep one min-heap for the first candidates and one for the last candidates, refilling from the middle.

### Hard
- `P40_MergeKSortedListsHeapGreedy.java` - A min-heap across list heads always reveals the next smallest node.
- `P41_SmallestRangeCoveringElementsFromKListsHeapGreedy.java` - Keep one element per list in a min-heap; the range is [heap minimum, current maximum], advanced from the minimum's list.
- `P42_FindMedianFromDataStreamHeapGreedy.java` - Balance a max-heap of the lower half against a min-heap of the upper half.
- `P43_IPOHeapGreedy.java` - Unlock projects by capital and repeatedly take the most profitable one.
- `P44_MinimumCostToHireKWorkersHeapGreedy.java` - Sort by wage / quality; for each ratio as the pay rate, keep the k smallest qualities seen so far.
- `P45_CandyHeapGreedy.java` - Two greedy passes, one for each neighbor rule.
- `P46_CourseScheduleIIIHeapGreedy.java` - Take courses by deadline and drop the longest one whenever the schedule overflows.
- `P47_MinimumNumberOfRefuelingStopsHeapGreedy.java` - Keep passed stations in a max-heap and refuel retroactively only when out of range.
- `P48_MeetingRoomsIIIHeapGreedy.java` - One heap holds free room numbers and another holds busy rooms ordered by the time they become free.
- `P49_MinimumNumberOfTapsToOpenToWaterAGardenHeapGreedy.java` - Convert taps to "farthest reach from each start", then cover [0, n] exactly like Jump Game II.
- `P50_TrappingRainWaterIIHeapGreedy.java` - Start from the border and always expand inward from the lowest wall seen so far.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P09_TopKFrequentElementsHeapGreedy.java
java P09_TopKFrequentElementsHeapGreedy
```
