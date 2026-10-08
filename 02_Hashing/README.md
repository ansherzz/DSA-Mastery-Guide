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
- `ContainsDuplicateHashing.java` - Add values to a HashSet and stop at the first one already present.
- `JewelsAndStonesHashing.java` - Load the jewel types into a set and test every stone against it.
- `FindTheDifferenceHashing.java` - Count the letters of one string and consume them with the other to expose the extra letter.
- `LongestPalindromeHashing.java` - Count letters, use every pair, and allow one odd letter in the center.
- `MinimumIndexSumOfTwoListsHashing.java` - Map each string to its index so common strings and their index sums are found in one scan.
- `DesignHashMapHashing.java` - Build a hash table from scratch with bucket arrays and separate chaining.

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
- `TopKFrequentWordsHashing.java` - Count words, then order by frequency with alphabetical tie-breaking.
- `PairsOfSongsDivisibleBy60Hashing.java` - Bucket durations by remainder mod 60 and look up the complementary remainder.
- `EncodeAndDecodeTinyURLHashing.java` - Use two maps and a base-62 counter to map URLs to short codes and back.

### Hard
- `FirstMissingPositiveHashing.java` - Use the array itself as the hash table by placing value v at index v - 1.


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
