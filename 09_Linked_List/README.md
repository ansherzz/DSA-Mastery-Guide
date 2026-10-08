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

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Reversal (whole list, sublist, groups)** - P01, P02, P20, P25, P26, P35, P48
- **Fast and slow pointers** - P03, P05, P15, P16, P39
- **Two pointers with a fixed gap** - P06, P13, P21, P42
- **Dummy head deletion and filtering** - P09, P10, P11, P30, P34
- **Merging and sorting** - P04, P29, P43, P49
- **Split, reverse the second half, and combine** - P07, P14, P40
- **Rewiring nodes into sublists** - P19, P22, P23, P24, P31, P32
- **Arithmetic on digit lists** - P12, P17, P18, P37, P38
- **Aligning two lists** - P08
- **Extra pointers and cloning** - P27, P28
- **Linked list design** - P45, P46, P47, P50
- **Single-pass traversal with state** - P33
- **Prefix sums on a list** - P36
- **Stack-assisted traversal** - P41
- **Circular lists** - P44

## Problems in this folder

### Easy
- `P01_ReverseLinkedListPattern.java` - Reverse pointers one node at a time.
- `P02_ReverseADoublyLinkedListPattern.java` - Swap the prev and next pointers of every node; the last node visited becomes the head.
- `P03_LinkedListCyclePattern.java` - Use slow and fast pointers to detect a loop without extra space.
- `P04_MergeTwoSortedListsPattern.java` - Use a dummy head and always attach the smaller node.
- `P05_MiddleOfLinkedListPattern.java` - Slow and fast pointers find the middle in one pass.
- `P06_KthNodeFromEndOfLinkedListPattern.java` - Move a lead pointer k steps ahead, then advance both until the lead falls off the end.
- `P07_PalindromeLinkedListPattern.java` - Find the middle, reverse the second half, compare, then restore.
- `P08_IntersectionOfTwoLinkedListsPattern.java` - Switch each pointer to the other list so both travel the same distance.
- `P09_RemoveDuplicatesFromSortedListPattern.java` - Bypass adjacent nodes that repeat the current value.
- `P10_RemoveLinkedListElementsPattern.java` - Use a dummy head so deleting the first node is not a special case.
- `P11_DeleteNNodesAfterMNodesPattern.java` - Alternate between walking m nodes to keep and unlinking the next n nodes.
- `P12_ConvertBinaryNumberInLinkedListToIntegerPattern.java` - Build the value head-first by doubling and adding each bit.

### Medium
- `P13_RemoveNthNodeFromEndPattern.java` - Maintain a fixed pointer gap so the slow pointer lands before the target node.
- `P14_ReorderListPattern.java` - Find middle, reverse second half, then weave the two lists.
- `P15_LinkedListCycleIIPattern.java` - After slow and fast meet, restart one pointer from the head to find the cycle start.
- `P16_LengthOfLinkedListCyclePattern.java` - Detect the meeting point with fast and slow pointers, then walk once around the loop to count it.
- `P17_AddTwoNumbersPattern.java` - Add digit by digit with a carry and build the result behind a dummy head.
- `P18_PlusOneLinkedListPattern.java` - Find the rightmost digit that is not 9, increment it, and zero everything after it.
- `P19_OddEvenLinkedListPattern.java` - Leapfrog two chains for odd and even positions, then join them.
- `P20_SwapNodesInPairsPattern.java` - Rewire three links per pair using a pointer before the pair.
- `P21_RotateListPattern.java` - Close the list into a ring and cut it at the new tail.
- `P22_PartitionListPattern.java` - Build two chains with dummy heads and concatenate them.
- `P23_MergeInBetweenLinkedListsPattern.java` - Locate the node before index a and the node after index b, then splice list2 between them.
- `P24_SplitLinkedListInPartsPattern.java` - Compute each part's size from the length, then cut the list after that many nodes.
- `P25_ReverseLinkedListIIPattern.java` - Reverse a section by repeatedly moving the next node to the section front.
- `P26_ReverseNodesInEvenLengthGroupsPattern.java` - Measure each group (sizes 1, 2, 3, ...) and reverse it in place only when its actual length is even.
- `P27_CopyListWithRandomPointerPattern.java` - Interleave copies with originals to set random pointers without a map.
- `P28_FlattenAMultilevelDoublyLinkedListPattern.java` - Whenever a node has a child, splice the whole child list between the node and its next.
- `P29_SortListPattern.java` - Merge sort: split at the middle and merge sorted halves by relinking.
- `P30_DeleteNodeInALinkedListPattern.java` - Without the head, copy the next node into the current one and unlink the next node.
- `P31_MergeNodesInBetweenZerosPattern.java` - Sum each zero-delimited group and write the totals back into the existing nodes.
- `P32_InsertGreatestCommonDivisorsInLinkedListPattern.java` - Between every adjacent pair, insert a new node holding their GCD.
- `P33_MinAndMaxNodesBetweenCriticalPointsPattern.java` - Track the first and the previous critical index while scanning with a three-node window.
- `P34_RemoveDuplicatesFromSortedListIIPattern.java` - Use a dummy head and skip whole runs of repeated values.
- `P35_RemoveNodesFromLinkedListPattern.java` - Reverse the list, keep nodes that are at least the running maximum, and reverse back.
- `P36_RemoveZeroSumConsecutiveNodesPattern.java` - Map each prefix sum to its last node; linking a node to the last node with the same prefix skips a zero-sum run.
- `P37_AddTwoNumbersIIPattern.java` - Use stacks to add most-significant-first lists without reversing them.
- `P38_DoubleANumberRepresentedAsALinkedListPattern.java` - Each digit becomes (2 * digit) % 10 plus a carry that depends only on the next digit.
- `P39_DeleteTheMiddleNodePattern.java` - Give the fast pointer a head start so slow stops just before the middle.
- `P40_MaximumTwinSumPattern.java` - Find the middle, reverse the second half, and pair it with the first half.
- `P41_NextGreaterNodeInLinkedListPattern.java` - Copy values to an array and resolve next-greater answers with a monotonic stack of indices.
- `P42_SwappingNodesInALinkedListPattern.java` - Locate the k-th node from each end with a fixed-gap pair of pointers.
- `P43_InsertionSortListPattern.java` - Splice each node into a sorted list that grows behind a dummy head.
- `P44_InsertIntoASortedCircularLinkedListPattern.java` - Walk the ring looking for either an ordinary gap or the wrap-around point between max and min.
- `P45_DesignLinkedListPattern.java` - Implement get, insert, and delete with a dummy head and a size counter.
- `P46_DesignBrowserHistoryPattern.java` - A doubly linked list of pages with a pointer to the current one; visiting cuts off the forward chain.
- `P47_LRUCacheDoublyLinkedListPattern.java` - A hash map finds nodes in O(1); a doubly linked list with sentinels keeps them in recency order.

### Hard
- `P48_ReverseNodesInKGroupPattern.java` - Confirm a full group, reverse it in place, and reconnect both ends.
- `P49_MergeKSortedListsPattern.java` - Merge lists in pairs, doubling the merged span each round, reusing the two-list merge.
- `P50_AllOOneDataStructurePattern.java` - A doubly linked list of count buckets in increasing order, each holding the keys with that count.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P01_ReverseLinkedListPattern.java
java P01_ReverseLinkedListPattern
```
