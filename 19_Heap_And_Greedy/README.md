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
- `KthLargestElementInStreamHeapGreedy.java` - Min-heap capped at size k whose top is the kth largest.
- `LastStoneWeightHeapGreedy.java` - Max-heap simulation of repeatedly smashing the two heaviest stones.
- `AssignCookiesHeapGreedy.java` - Sort both lists and give each child the smallest cookie that satisfies them.
- `LemonadeChangeHeapGreedy.java` - Prefer giving a 10 as change to keep the more flexible 5s.

### Medium
- `TopKFrequentElementsHeapGreedy.java` - Count frequencies, then use a heap to extract the top k.
- `MeetingRoomsIIHeapGreedy.java` - Track active meeting end times in a min-heap to reuse rooms greedily.
- `TaskSchedulerHeapGreedy.java` - Greedily execute the most frequent tasks using a max-heap and cooldown cycles.
- `ReorganizeStringHeapGreedy.java` - Use the most frequent available character while holding back the last-used one.
- `KClosestPointsToOriginHeapGreedy.java` - Max-heap of size k ordered by squared distance.
- `KthLargestElementInArrayHeapGreedy.java` - Keep the k largest values in a min-heap.
- `JumpGameHeapGreedy.java` - Track the farthest reachable index in one pass.
- `JumpGameIIHeapGreedy.java` - Count jumps by expanding reachable ranges like BFS levels.
- `GasStationHeapGreedy.java` - Reset the start whenever the tank goes negative and check the total surplus.
- `MergeIntervalsHeapGreedy.java` - Sort by start and extend or close the current interval.
- `NonOverlappingIntervalsHeapGreedy.java` - Sort by end and always keep the interval that finishes first.
- `PartitionLabelsHeapGreedy.java` - Extend each part to the last occurrence of every letter inside it.

### Hard
- `MergeKSortedListsHeapGreedy.java` - A min-heap across list heads always reveals the next smallest node.
- `FindMedianFromDataStreamHeapGreedy.java` - Balance a max-heap of the lower half against a min-heap of the upper half.
- `IPOHeapGreedy.java` - Unlock projects by capital and repeatedly take the most profitable one.
- `CandyHeapGreedy.java` - Two greedy passes, one for each neighbor rule.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac TopKFrequentElementsHeapGreedy.java
java TopKFrequentElementsHeapGreedy
```
