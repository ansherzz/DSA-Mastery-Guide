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
- `TopKFrequentElementsHeapGreedy.java` - Count frequencies, then use a heap to extract the top k.
- `MergeKSortedListsHeapGreedy.java` - A min-heap across list heads always reveals the next smallest node.
- `MeetingRoomsIIHeapGreedy.java` - Track active meeting end times in a min-heap to reuse rooms greedily.
- `TaskSchedulerHeapGreedy.java` - Greedily execute the most frequent tasks using a max-heap and cooldown cycles.
- `ReorganizeStringHeapGreedy.java` - Use the most frequent available character while holding back the last-used one.


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
