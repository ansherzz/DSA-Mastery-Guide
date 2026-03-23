# 04. Sliding Window

## What this pattern is
Sliding window maintains a contiguous range while expanding and shrinking it to satisfy a condition. It is ideal for substring and subarray problems.

## When to use it
- When the answer is about a contiguous window.
- When you need longest, shortest, max, or min segment under a constraint.
- When you can update state by adding one element and removing one element.
- When a window condition becomes valid or invalid as you move.

## How to identify it in interviews
- The prompt mentions substring, subarray, contiguous, window, or segment.
- You want the longest or shortest range that satisfies a condition.
- A brute-force approach would try every range.
- You can maintain counts while moving left and right.

## Upgrade path / mental model
Decide the window invariant first: what must be true before you record an answer or before you shrink?

## Problems in this folder
- `LongestSubstringWithoutRepeatingSlidingWindow.java` - Expand right, and shift left only when a repeat breaks the window.
- `MinimumSizeSubarraySumSlidingWindow.java` - Positive values make expand-and-shrink logic possible.
- `PermutationInStringSlidingWindow.java` - Use a fixed-length window and maintain frequency counts.
- `LongestRepeatingCharacterReplacementSlidingWindow.java` - Maintain a window where all but the most frequent letter can be changed within k.
- `MinimumWindowSubstringSlidingWindow.java` - Grow until valid, then shrink to the smallest valid range.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac LongestSubstringWithoutRepeatingSlidingWindow.java
java LongestSubstringWithoutRepeatingSlidingWindow
```
