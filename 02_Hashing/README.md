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
- `P01_ValidAnagramHashing.java` - Count character frequencies and compare.
- `P02_MajorityElementHashing.java` - Count frequencies until one crosses n/2.
- `P03_TwoSumHashing.java` - Store value -> index and look up each complement.
- `P04_ContainsDuplicateIIHashing.java` - Remember the last index of each value and compare the gap with k.
- `P05_FirstUniqueCharacterHashing.java` - Count frequencies, then find the first character with count 1.
- `P06_IsomorphicStringsHashing.java` - Keep forward and backward maps to enforce a one-to-one character mapping.
- `P07_WordPatternHashing.java` - Enforce a bijection between pattern letters and words.
- `P08_HappyNumberHashing.java` - Use a HashSet of seen values to detect a cycle in the digit-square process.
- `P09_IntersectionOfTwoArraysHashing.java` - Count one array, then consume counts while scanning the other.
- `P10_RansomNoteHashing.java` - Treat the magazine as a letter supply and consume from it.
- `P11_ContainsDuplicateHashing.java` - Add values to a HashSet and stop at the first one already present.
- `P12_JewelsAndStonesHashing.java` - Load the jewel types into a set and test every stone against it.
- `P13_FindTheDifferenceHashing.java` - Count the letters of one string and consume them with the other to expose the extra letter.
- `P14_LongestPalindromeHashing.java` - Count letters, use every pair, and allow one odd letter in the center.
- `P15_MinimumIndexSumOfTwoListsHashing.java` - Map each string to its index so common strings and their index sums are found in one scan.
- `P16_DesignHashMapHashing.java` - Build a hash table from scratch with bucket arrays and separate chaining.

### Medium
- `P17_GroupAnagramsHashing.java` - Hash each word by a canonical sorted signature.
- `P18_SubarraySumEqualsKHashing.java` - Hash prefix sums so each subarray total is found in O(1) average time.
- `P19_LongestConsecutiveSequenceHashing.java` - Use a HashSet to detect sequence starts and extend them.
- `P20_SortCharactersByFrequencyHashing.java` - Count characters, then bucket them by frequency.
- `P21_FourSumIIHashing.java` - Hash all pair sums of two arrays and look up the negated sums of the other two.
- `P22_ContiguousArrayHashing.java` - Map each running balance to its first index to find the longest balanced subarray.
- `P23_ValidSudokuHashing.java` - Encode (group, digit) keys in one set to catch repeats in rows, columns, and boxes.
- `P24_BrickWallHashing.java` - Count shared brick-edge positions and cut through the most common one.
- `P25_LRUCacheHashing.java` - Combine a HashMap with a doubly linked list for O(1) get, put, and eviction.
- `P26_RandomizedSetHashing.java` - Pair a list with a value -> index map so insert, remove, and random pick are O(1).
- `P27_TopKFrequentWordsHashing.java` - Count words, then order by frequency with alphabetical tie-breaking.
- `P28_PairsOfSongsDivisibleBy60Hashing.java` - Bucket durations by remainder mod 60 and look up the complementary remainder.
- `P29_EncodeAndDecodeTinyURLHashing.java` - Use two maps and a base-62 counter to map URLs to short codes and back.

### Hard
- `P30_FirstMissingPositiveHashing.java` - Use the array itself as the hash table by placing value v at index v - 1.


## How to run one file
The number in each file name is the suggested solving order (P01 first, P30 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P01_ValidAnagramHashing.java
java P01_ValidAnagramHashing
```
