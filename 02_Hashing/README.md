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

### Easy
- `ValidAnagramHashing.java` - Count character frequencies and compare.
- `MajorityElementHashing.java` - Count frequencies until one crosses n/2.
- `TwoSumHashing.java` - Store value -> index and look up each complement.
- `ContainsDuplicateIIHashing.java` - Remember the last index of each value and compare the gap with k.
- `FirstUniqueCharacterHashing.java` - Count frequencies, then find the first character with count 1.
- `IsomorphicStringsHashing.java` - Keep forward and backward maps to enforce a one-to-one character mapping.
- `WordPatternHashing.java` - Enforce a bijection between pattern letters and words.
- `HappyNumberHashing.java` - Use a HashSet of seen values to detect a cycle in the digit-square process.
- `IntersectionOfTwoArraysHashing.java` - Count one array, then consume counts while scanning the other.
- `RansomNoteHashing.java` - Treat the magazine as a letter supply and consume from it.

### Medium
- `GroupAnagramsHashing.java` - Hash each word by a canonical sorted signature.
- `SubarraySumEqualsKHashing.java` - Hash prefix sums so each subarray total is found in O(1) average time.
- `LongestConsecutiveSequenceHashing.java` - Use a HashSet to detect sequence starts and extend them.
- `SortCharactersByFrequencyHashing.java` - Count characters, then bucket them by frequency.
- `FourSumIIHashing.java` - Hash all pair sums of two arrays and look up the negated sums of the other two.
- `ContiguousArrayHashing.java` - Map each running balance to its first index to find the longest balanced subarray.
- `ValidSudokuHashing.java` - Encode (group, digit) keys in one set to catch repeats in rows, columns, and boxes.
- `BrickWallHashing.java` - Count shared brick-edge positions and cut through the most common one.
- `LRUCacheHashing.java` - Combine a HashMap with a doubly linked list for O(1) get, put, and eviction.
- `RandomizedSetHashing.java` - Pair a list with a value -> index map so insert, remove, and random pick are O(1).


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
