# 02. Hashing

## What this pattern is
Hashing stores information you want to query in O(1) average time: frequencies, existence, last seen positions, grouping keys, or prefix counts.

## When to use it
- When you need fast lookup of values, frequencies, or complements.
- When the question involves duplicates, anagrams, counts, or grouping.
- When you want to trade extra memory for speed.
- When prefix information needs a map from state to count or index.

## How to identify it in interviews
- Words like frequency, duplicate, count, seen before, group by, or complement appear.
- You repeatedly search whether something already exists.
- Order is less important than quick membership or counting.
- You can transform the problem into a lookup table.

## Upgrade path / mental model
Once you see repeated lookups or frequency counting, reach for HashMap, HashSet, or a custom key based on counts.

## Problems in this folder
- `ValidAnagramHashing.java` - Count character frequencies and compare.
- `GroupAnagramsHashing.java` - Hash each word by a canonical sorted signature.
- `MajorityElementHashing.java` - Count frequencies until one crosses n/2.
- `SubarraySumEqualsKHashing.java` - Hash prefix sums so each subarray total is found in O(1) average time.
- `LongestConsecutiveSequenceHashing.java` - Use a HashSet to detect sequence starts and extend them.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac ValidAnagramHashing.java
java ValidAnagramHashing
```
