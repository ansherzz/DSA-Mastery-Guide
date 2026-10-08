# 11. Queue / BFS

## What this pattern is
Breadth-first search explores layer by layer. A queue is the right tool when the shortest number of steps or level-wise expansion matters.

## When to use it
- When distance in edges or moves matters.
- When you need level order traversal or multi-source expansion.
- When states spread outward one layer at a time.
- When the first time you reach a state is guaranteed to be optimal.

## How to identify it in interviews
- Words like minimum steps, level order, minutes, or shortest path in an unweighted graph appear.
- You can picture a wave expanding from one or more starting points.
- The search must process nodes in the order discovered.
- You need to avoid revisiting states.

## Upgrade path / mental model
Mark states visited as soon as you enqueue them to avoid duplicates.

## Problems in this folder

### Easy
- `P01_ImplementStackUsingQueuesQueue.java` - Rotate a single queue after each push so the newest item is at the front.
- `P02_MovingAverageFromDataStreamQueue.java` - Keep the window in a queue with a running sum.
- `P03_AverageOfLevelsInBinaryTreeBFS.java` - Level-order traversal that averages each level's values.
- `P04_MinimumDepthOfBinaryTreeBFS.java` - Stop at the first leaf BFS reaches.
- `P05_FloodFillBFS.java` - Spread from the start pixel through same-colored neighbors.
- `P06_NumberOfRecentCallsQueue.java` - Queue the timestamps and drop the ones that fell out of the 3000 ms window.
- `P07_TimeNeededToBuyTicketsQueue.java` - Simulate the line by serving the front and re-queuing whoever still needs tickets.
- `P08_CousinsInBinaryTreeBFS.java` - Check siblings while expanding and compare which targets appear in each level.

### Medium
- `P09_BinaryTreeLevelOrderTraversalBFS.java` - Use a queue to process the tree level by level.
- `P10_RottingOrangesBFS.java` - Start BFS from every rotten orange simultaneously.
- `P11_NumberOfIslandsBFS.java` - Every new unvisited land cell launches a BFS flood fill.
- `P12_ZeroOneMatrixBFS.java` - Multi-source BFS gives the nearest zero distance in one pass.
- `P13_OpenTheLockBFS.java` - Treat combinations as graph states and use BFS for minimum moves.
- `P14_DesignCircularQueue.java` - Ring buffer on an array using a front index, a count, and modulo arithmetic.
- `P15_BinaryTreeRightSideViewBFS.java` - Take the last node of every level.
- `P16_BinaryTreeZigzagLevelOrderBFS.java` - Normal BFS, but alternate the write direction per level.
- `P17_PopulatingNextRightPointersBFS.java` - Link each node to the next node in the queue within its level.
- `P18_MaximumWidthOfBinaryTreeBFS.java` - Assign complete-tree indices and measure each level's span.
- `P19_WallsAndGatesBFS.java` - Multi-source BFS from all gates fills nearest-gate distances.
- `P20_AsFarFromLandAsPossibleBFS.java` - Multi-source BFS from all land; the last ring is the farthest water.
- `P21_NearestExitFromEntranceInMazeBFS.java` - Grid BFS that returns at the first border cell reached.
- `P22_PerfectSquaresBFS.java` - Model numbers as nodes and subtracting a square as an edge.
- `P23_NAryTreeLevelOrderTraversalBFS.java` - Level-order traversal where each node offers a list of children.
- `P24_FindLargestValueInEachTreeRowBFS.java` - Take one maximum per BFS level.
- `P25_DesignCircularDequeQueue.java` - Implement a double-ended queue on a ring buffer with a front index and a size.
- `P26_JumpGameIIIBFS.java` - Treat indices as nodes with two jump edges and search for a zero.
- `P27_Dota2SenateQueue.java` - Keep one queue per party; the earlier senator bans the other and rejoins for the next round.
- `P28_RevealCardsInIncreasingOrderQueue.java` - Simulate the reveal process on positions to learn where each sorted card goes.

### Hard
- `P29_SlidingPuzzleBFS.java` - BFS over board states encoded as strings.
- `P30_ShortestPathWithObstaclesEliminationBFS.java` - BFS over (cell, eliminations left), keeping only arrivals with more eliminations remaining.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P09_BinaryTreeLevelOrderTraversalBFS.java
java P09_BinaryTreeLevelOrderTraversalBFS
```
