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

## Problems in this folder

### Easy
- `P01_KthLargestElementInStreamHeapGreedy.java` - Min-heap capped at size k whose top is the kth largest.
- `P02_LastStoneWeightHeapGreedy.java` - Max-heap simulation of repeatedly smashing the two heaviest stones.
- `P03_AssignCookiesHeapGreedy.java` - Sort both lists and give each child the smallest cookie that satisfies them.
- `P04_LemonadeChangeHeapGreedy.java` - Prefer giving a 10 as change to keep the more flexible 5s.
- `P05_MaximumUnitsOnATruckHeapGreedy.java` - Fill every slot with the richest box type still available.
- `P06_CanPlaceFlowersHeapGreedy.java` - Plant in the first legal plot each time; it never blocks a better arrangement.

### Medium
- `P07_TopKFrequentElementsHeapGreedy.java` - Count frequencies, then use a heap to extract the top k.
- `P08_MeetingRoomsIIHeapGreedy.java` - Track active meeting end times in a min-heap to reuse rooms greedily.
- `P09_TaskSchedulerHeapGreedy.java` - Greedily execute the most frequent tasks using a max-heap and cooldown cycles.
- `P10_ReorganizeStringHeapGreedy.java` - Use the most frequent available character while holding back the last-used one.
- `P11_KClosestPointsToOriginHeapGreedy.java` - Max-heap of size k ordered by squared distance.
- `P12_KthLargestElementInArrayHeapGreedy.java` - Keep the k largest values in a min-heap.
- `P13_JumpGameHeapGreedy.java` - Track the farthest reachable index in one pass.
- `P14_JumpGameIIHeapGreedy.java` - Count jumps by expanding reachable ranges like BFS levels.
- `P15_GasStationHeapGreedy.java` - Reset the start whenever the tank goes negative and check the total surplus.
- `P16_MergeIntervalsHeapGreedy.java` - Sort by start and extend or close the current interval.
- `P17_NonOverlappingIntervalsHeapGreedy.java` - Sort by end and always keep the interval that finishes first.
- `P18_PartitionLabelsHeapGreedy.java` - Extend each part to the last occurrence of every letter inside it.
- `P19_BestTimeToBuyAndSellStockIIHeapGreedy.java` - Collect every upward day-to-day price move.
- `P20_MinimumNumberOfArrowsToBurstBalloonsHeapGreedy.java` - Sort by end and shoot at the end of the first balloon not yet burst.
- `P21_FurthestBuildingYouCanReachHeapGreedy.java` - Give every climb a ladder, then pay bricks for the smallest one when ladders run out.
- `P22_SingleThreadedCPUHeapGreedy.java` - Simulate time with a min-heap of available tasks keyed by processing time and index.
- `P23_FindKPairsWithSmallestSumsHeapGreedy.java` - K-way merge the sorted pair lists with a min-heap of list heads.
- `P24_QueueReconstructionByHeightHeapGreedy.java` - Place the tallest people first and insert each person at index k.

### Hard
- `P25_MergeKSortedListsHeapGreedy.java` - A min-heap across list heads always reveals the next smallest node.
- `P26_FindMedianFromDataStreamHeapGreedy.java` - Balance a max-heap of the lower half against a min-heap of the upper half.
- `P27_IPOHeapGreedy.java` - Unlock projects by capital and repeatedly take the most profitable one.
- `P28_CandyHeapGreedy.java` - Two greedy passes, one for each neighbor rule.
- `P29_CourseScheduleIIIHeapGreedy.java` - Take courses by deadline and drop the longest one whenever the schedule overflows.
- `P30_MinimumNumberOfRefuelingStopsHeapGreedy.java` - Keep passed stations in a max-heap and refuel retroactively only when out of range.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P07_TopKFrequentElementsHeapGreedy.java
java P07_TopKFrequentElementsHeapGreedy
```
