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

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Queue design** - P01, P21, P38, P39
- **Queue as a stream or time window** - P02, P07, P15
- **Queue simulation** - P08, P09, P10, P11, P42, P43, P44
- **Tree level-order traversal** - P03, P04, P05, P12, P13, P14, P16, P22, P23, P24, P25, P26, P27, P35, P36, P37
- **Grid BFS from a single source** - P06, P18, P31, P32, P48
- **Multi-source BFS** - P17, P19, P28, P29, P30
- **BFS over implicit states** - P20, P33, P34, P40, P41, P46, P49
- **BFS with extra state per node** - P45, P47, P50

## Problems in this folder

### Easy
- `P01_ImplementStackUsingQueuesQueue.java` - Rotate a single queue after each push so the newest item is at the front.
- `P02_MovingAverageFromDataStreamQueue.java` - Keep the window in a queue with a running sum.
- `P03_AverageOfLevelsInBinaryTreeBFS.java` - Level-order traversal that averages each level's values.
- `P04_MinimumDepthOfBinaryTreeBFS.java` - Stop at the first leaf BFS reaches.
- `P05_MaximumDepthOfNaryTreeBFS.java` - Process the tree one level at a time and count the levels.
- `P06_FloodFillBFS.java` - Spread from the start pixel through same-colored neighbors.
- `P07_NumberOfRecentCallsQueue.java` - Queue the timestamps and drop the ones that fell out of the 3000 ms window.
- `P08_GenerateBinaryNumbersQueue.java` - Each number popped from the queue produces its two successors by appending 0 and 1.
- `P09_TimeNeededToBuyTicketsQueue.java` - Simulate the line by serving the front and re-queuing whoever still needs tickets.
- `P10_NumberOfStudentsUnableToEatLunchQueue.java` - Rotate the student queue until a full pass happens with nobody taking the top sandwich.
- `P11_ReverseFirstKElementsOfQueueQueue.java` - Move k elements through a stack to reverse them, then rotate the rest behind them.
- `P12_CousinsInBinaryTreeBFS.java` - Check siblings while expanding and compare which targets appear in each level.
- `P13_SymmetricTreeIterativeBFS.java` - Enqueue nodes in mirrored pairs and compare each pair as it is dequeued.

### Medium
- `P14_BinaryTreeLevelOrderTraversalBFS.java` - Use a queue to process the tree level by level.
- `P15_FirstNonRepeatingCharacterInAStreamQueue.java` - Queue the candidates in arrival order and discard repeated ones from the front.
- `P16_BinaryTreeLevelOrderTraversalIIBFS.java` - Run a normal level-order traversal and insert each level at the front of the result.
- `P17_RottingOrangesBFS.java` - Start BFS from every rotten orange simultaneously.
- `P18_NumberOfIslandsBFS.java` - Every new unvisited land cell launches a BFS flood fill.
- `P19_ZeroOneMatrixBFS.java` - Multi-source BFS gives the nearest zero distance in one pass.
- `P20_OpenTheLockBFS.java` - Treat combinations as graph states and use BFS for minimum moves.
- `P21_DesignCircularQueue.java` - Ring buffer on an array using a front index, a count, and modulo arithmetic.
- `P22_BinaryTreeRightSideViewBFS.java` - Take the last node of every level.
- `P23_FindBottomLeftTreeValueBFS.java` - Traverse each level from right to left; the very last node visited is the bottom-left one.
- `P24_BinaryTreeZigzagLevelOrderBFS.java` - Normal BFS, but alternate the write direction per level.
- `P25_PopulatingNextRightPointersBFS.java` - Link each node to the next node in the queue within its level.
- `P26_MaximumWidthOfBinaryTreeBFS.java` - Assign complete-tree indices and measure each level's span.
- `P27_CheckCompletenessOfABinaryTreeBFS.java` - In level order, once a missing child is seen no real node may appear after it.
- `P28_WallsAndGatesBFS.java` - Multi-source BFS from all gates fills nearest-gate distances.
- `P29_AsFarFromLandAsPossibleBFS.java` - Multi-source BFS from all land; the last ring is the farthest water.
- `P30_MapOfHighestPeakBFS.java` - Start BFS from every water cell at height 0; each cell's height is its distance to the nearest water.
- `P31_NearestExitFromEntranceInMazeBFS.java` - Grid BFS that returns at the first border cell reached.
- `P32_SurroundedRegionsBFS.java` - Mark every 'O' reachable from the border as safe, then flip all the others.
- `P33_PerfectSquaresBFS.java` - Model numbers as nodes and subtracting a square as an edge.
- `P34_MinimumOperationsToConvertNumberBFS.java` - Treat each value in [0, 1000] as a node and each +, -, ^ with an array element as an edge.
- `P35_NAryTreeLevelOrderTraversalBFS.java` - Level-order traversal where each node offers a list of children.
- `P36_FindLargestValueInEachTreeRowBFS.java` - Take one maximum per BFS level.
- `P37_DeepestLeavesSumBFS.java` - Recompute the level sum for every level; the last one computed belongs to the deepest leaves.
- `P38_DesignCircularDequeQueue.java` - Implement a double-ended queue on a ring buffer with a front index and a size.
- `P39_DesignFrontMiddleBackQueueQueue.java` - Split the queue into two balanced deques so the middle is always at their boundary.
- `P40_JumpGameIIIBFS.java` - Treat indices as nodes with two jump edges and search for a zero.
- `P41_AmountOfTimeForBinaryTreeToBeInfectedBFS.java` - Convert the tree into an undirected graph, then BFS outward from the start node.
- `P42_Dota2SenateQueue.java` - Keep one queue per party; the earlier senator bans the other and rejoins for the next round.
- `P43_FindTheWinnerOfTheCircularGameQueue.java` - Rotate k - 1 players to the back of the queue and remove the k-th, until one remains.
- `P44_RevealCardsInIncreasingOrderQueue.java` - Simulate the reveal process on positions to learn where each sorted card goes.
- `P45_MinimumJumpsToReachHomeBFS.java` - BFS over (position, whether the last jump was backward) with an upper bound on useful positions.

### Hard
- `P46_SlidingPuzzleBFS.java` - BFS over board states encoded as strings.
- `P47_ShortestPathWithObstaclesEliminationBFS.java` - BFS over (cell, eliminations left), keeping only arrivals with more eliminations remaining.
- `P48_CutOffTreesForGolfEventBFS.java` - Sort the trees by height and chain one shortest-path BFS between each consecutive pair.
- `P49_KSimilarStringsBFS.java` - BFS over strings where each move fixes the first mismatched position with a useful swap.
- `P50_RaceCarBFS.java` - BFS over (position, speed) states with pruning of positions that overshoot too far.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P14_BinaryTreeLevelOrderTraversalBFS.java
java P14_BinaryTreeLevelOrderTraversalBFS
```
