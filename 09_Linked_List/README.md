# 09. Linked List

## What this pattern is
Linked list problems are pointer problems. Success comes from drawing references carefully and protecting the head of the list.

## When to use it
- When the structure only gives next pointers.
- When reversal, re-linking, middle finding, or cycle detection is involved.
- When you need O(1) extra space manipulation of nodes.
- When dummy nodes simplify head edge cases.

## How to identify it in interviews
- The problem talks about nodes and pointers rather than indices.
- Head insertion, reversal, or slow/fast traversal appears.
- You must avoid losing the rest of the list during updates.
- A dummy node or two pointers can simplify the logic.

## Upgrade path / mental model
Before reassigning any next pointer, save the next node in a temporary variable.

## Problems in this folder

### Easy
- `ReverseLinkedListPattern.java` - Reverse pointers one node at a time.
- `LinkedListCyclePattern.java` - Use slow and fast pointers to detect a loop without extra space.
- `MergeTwoSortedListsPattern.java` - Use a dummy head and always attach the smaller node.
- `MiddleOfLinkedListPattern.java` - Slow and fast pointers find the middle in one pass.
- `PalindromeLinkedListPattern.java` - Find the middle, reverse the second half, compare, then restore.
- `IntersectionOfTwoLinkedListsPattern.java` - Switch each pointer to the other list so both travel the same distance.
- `RemoveDuplicatesFromSortedListPattern.java` - Bypass adjacent nodes that repeat the current value.
- `RemoveLinkedListElementsPattern.java` - Use a dummy head so deleting the first node is not a special case.
- `ConvertBinaryNumberInLinkedListToIntegerPattern.java` - Build the value head-first by doubling and adding each bit.

### Medium
- `RemoveNthNodeFromEndPattern.java` - Maintain a fixed pointer gap so the slow pointer lands before the target node.
- `ReorderListPattern.java` - Find middle, reverse second half, then weave the two lists.
- `LinkedListCycleIIPattern.java` - After slow and fast meet, restart one pointer from the head to find the cycle start.
- `AddTwoNumbersPattern.java` - Add digit by digit with a carry and build the result behind a dummy head.
- `OddEvenLinkedListPattern.java` - Leapfrog two chains for odd and even positions, then join them.
- `SwapNodesInPairsPattern.java` - Rewire three links per pair using a pointer before the pair.
- `RotateListPattern.java` - Close the list into a ring and cut it at the new tail.
- `PartitionListPattern.java` - Build two chains with dummy heads and concatenate them.
- `ReverseLinkedListIIPattern.java` - Reverse a section by repeatedly moving the next node to the section front.
- `CopyListWithRandomPointerPattern.java` - Interleave copies with originals to set random pointers without a map.
- `SortListPattern.java` - Merge sort: split at the middle and merge sorted halves by relinking.
- `DeleteNodeInALinkedListPattern.java` - Without the head, copy the next node into the current one and unlink the next node.
- `MergeNodesInBetweenZerosPattern.java` - Sum each zero-delimited group and write the totals back into the existing nodes.
- `RemoveDuplicatesFromSortedListIIPattern.java` - Use a dummy head and skip whole runs of repeated values.
- `AddTwoNumbersIIPattern.java` - Use stacks to add most-significant-first lists without reversing them.
- `DeleteTheMiddleNodePattern.java` - Give the fast pointer a head start so slow stops just before the middle.
- `MaximumTwinSumPattern.java` - Find the middle, reverse the second half, and pair it with the first half.
- `SwappingNodesInALinkedListPattern.java` - Locate the k-th node from each end with a fixed-gap pair of pointers.
- `InsertionSortListPattern.java` - Splice each node into a sorted list that grows behind a dummy head.
- `DesignLinkedListPattern.java` - Implement get, insert, and delete with a dummy head and a size counter.

### Hard
- `ReverseNodesInKGroupPattern.java` - Confirm a full group, reverse it in place, and reconnect both ends.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac ReverseLinkedListPattern.java
java ReverseLinkedListPattern
```
