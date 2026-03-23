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
- `ReverseLinkedListPattern.java` - Reverse pointers one node at a time.
- `LinkedListCyclePattern.java` - Use slow and fast pointers to detect a loop without extra space.
- `MergeTwoSortedListsPattern.java` - Use a dummy head and always attach the smaller node.
- `RemoveNthNodeFromEndPattern.java` - Maintain a fixed pointer gap so the slow pointer lands before the target node.
- `ReorderListPattern.java` - Find middle, reverse second half, then weave the two lists.


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
