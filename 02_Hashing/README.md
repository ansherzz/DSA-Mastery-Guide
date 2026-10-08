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

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Frequency counting** - P01, P02, P03, P07, P08, P13, P14, P17, P18, P19, P20, P27, P31, P33, P44
- **Complement and pair lookup** - P04, P05, P29, P34, P35, P46
- **Set membership and deduplication** - P11, P12, P15, P16, P30, P38
- **Index map (value to position)** - P06, P21, P22, P43
- **Bijection between two alphabets** - P09, P10
- **Canonical key grouping** - P25, P26
- **Prefix state stored in a map** - P28, P36, P39
- **Hash-based data structure design** - P23, P24, P41, P42, P47, P49, P50
- **Array used as its own hash table** - P48
- **Frequency buckets** - P32
- **Rolling hash of substrings** - P37
- **Ordered map counting** - P40, P45

## Problems in this folder

### Easy
- `P01_ValidAnagramHashing.java` - Count character frequencies and compare.
- `P02_MajorityElementHashing.java` - Count frequencies until one crosses n/2.
- `P03_NumberOfGoodPairsHashing.java` - Each new value pairs with every earlier copy of itself, so add its running count.
- `P04_TwoSumHashing.java` - Store value -> index and look up each complement.
- `P05_CheckIfNAndItsDoubleExistHashing.java` - For each value, look up both its double and its half among the values already seen.
- `P06_ContainsDuplicateIIHashing.java` - Remember the last index of each value and compare the gap with k.
- `P07_FirstUniqueCharacterHashing.java` - Count frequencies, then find the first character with count 1.
- `P08_UniqueNumberOfOccurrencesHashing.java` - Count values, then put the counts themselves into a set to detect a repeated count.
- `P09_IsomorphicStringsHashing.java` - Keep forward and backward maps to enforce a one-to-one character mapping.
- `P10_WordPatternHashing.java` - Enforce a bijection between pattern letters and words.
- `P11_HappyNumberHashing.java` - Use a HashSet of seen values to detect a cycle in the digit-square process.
- `P12_IntersectionOfTwoArraysHashing.java` - Count one array, then consume counts while scanning the other.
- `P13_IntersectionOfTwoArraysIIHashing.java` - Count one array, then consume those counts while scanning the other.
- `P14_RansomNoteHashing.java` - Treat the magazine as a letter supply and consume from it.
- `P15_ContainsDuplicateHashing.java` - Add values to a HashSet and stop at the first one already present.
- `P16_JewelsAndStonesHashing.java` - Load the jewel types into a set and test every stone against it.
- `P17_MaximumNumberOfBalloonsHashing.java` - Count letters and take the bottleneck ratio against the recipe for "balloon".
- `P18_FindTheDifferenceHashing.java` - Count the letters of one string and consume them with the other to expose the extra letter.
- `P19_LongestPalindromeHashing.java` - Count letters, use every pair, and allow one odd letter in the center.
- `P20_FindCommonCharactersHashing.java` - Keep the minimum count of each letter across all words.
- `P21_MinimumIndexSumOfTwoListsHashing.java` - Map each string to its index so common strings and their index sums are found in one scan.
- `P22_VerifyingAnAlienDictionaryHashing.java` - Map each letter to its rank in the alien alphabet, then compare adjacent words.
- `P23_DesignHashSetHashing.java` - Hash each key to a bucket and resolve collisions with a small list per bucket.
- `P24_DesignHashMapHashing.java` - Build a hash table from scratch with bucket arrays and separate chaining.

### Medium
- `P25_GroupAnagramsHashing.java` - Hash each word by a canonical sorted signature.
- `P26_GroupShiftedStringsHashing.java` - Use the sequence of letter gaps relative to the first letter as the group key.
- `P27_MinimumStepsToMakeTwoStringsAnagramHashing.java` - Subtract one frequency table from the other and sum the surplus letters.
- `P28_SubarraySumEqualsKHashing.java` - Hash prefix sums so each subarray total is found in O(1) average time.
- `P29_MaxNumberOfKSumPairsHashing.java` - Match each number with a waiting complement, consuming both when a pair forms.
- `P30_LongestConsecutiveSequenceHashing.java` - Use a HashSet to detect sequence starts and extend them.
- `P31_SortCharactersByFrequencyHashing.java` - Count characters, then bucket them by frequency.
- `P32_TopKFrequentElementsBucketHashing.java` - Count with a map, then index buckets by frequency to avoid sorting.
- `P33_DetermineIfTwoStringsAreCloseHashing.java` - Compare the set of letters used and the sorted multiset of frequencies.
- `P34_FourSumIIHashing.java` - Hash all pair sums of two arrays and look up the negated sums of the other two.
- `P35_CountNumberOfBadPairsHashing.java` - Rewrite the pair condition so each side depends on one index, then count equal keys.
- `P36_ContiguousArrayHashing.java` - Map each running balance to its first index to find the longest balanced subarray.
- `P37_RepeatedDNASequencesHashing.java` - Encode each 10-letter window as a 20-bit rolling integer and track seen codes in a set.
- `P38_ValidSudokuHashing.java` - Encode (group, digit) keys in one set to catch repeats in rows, columns, and boxes.
- `P39_BrickWallHashing.java` - Count shared brick-edge positions and cut through the most common one.
- `P40_HandOfStraightsHashing.java` - Repeatedly start a group at the smallest remaining card using a sorted count map.
- `P41_LRUCacheHashing.java` - Combine a HashMap with a doubly linked list for O(1) get, put, and eviction.
- `P42_RandomizedSetHashing.java` - Pair a list with a value -> index map so insert, remove, and random pick are O(1).
- `P43_FractionToRecurringDecimalHashing.java` - Remember where each remainder first appeared; a repeated remainder marks the start of the cycle.
- `P44_TopKFrequentWordsHashing.java` - Count words, then order by frequency with alphabetical tie-breaking.
- `P45_ArrayOfDoubledPairsHashing.java` - Process values in sorted order and pair each one with its required partner using counts.
- `P46_PairsOfSongsDivisibleBy60Hashing.java` - Bucket durations by remainder mod 60 and look up the complementary remainder.
- `P47_EncodeAndDecodeTinyURLHashing.java` - Use two maps and a base-62 counter to map URLs to short codes and back.

### Hard
- `P48_FirstMissingPositiveHashing.java` - Use the array itself as the hash table by placing value v at index v - 1.
- `P49_RandomizedCollectionHashing.java` - Combine a list of values with a map from value to the set of its indices; delete by swapping with the last slot.
- `P50_LFUCacheHashing.java` - Keep key->value, key->frequency, and frequency->ordered keys maps plus the current minimum frequency.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

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
