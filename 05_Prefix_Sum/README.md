# 05. Prefix Sum

## What this pattern is
Prefix sum precomputes cumulative information so any range query can be answered in O(1) or transformed into a hashmap lookup.

## When to use it
- When range sums appear repeatedly.
- When subarray properties depend on cumulative totals or remainders.
- When you can rewrite a target subarray condition using prefix differences.
- When left-to-right accumulation can answer future queries quickly.

## How to identify it in interviews
- The problem asks about many range sums or subarray totals.
- You can express sum(i..j) as prefix[j] - prefix[i-1].
- You need a cumulative running total or prefix product idea.
- There is a repeated query over a static array.

## Upgrade path / mental model
If the prompt says subarray sum equals something, think prefix sum first, then ask whether a hashmap of prefixes is needed.

## Problems in this folder
- `RunningSumPrefixSum.java` - Build cumulative totals from left to right.
- `RangeSumQueryImmutablePrefixSum.java` - Precompute prefix sums so every range query is constant time.
- `ContinuousSubarraySumPrefixSum.java` - Equal prefix remainders imply a subarray sum divisible by k.
- `ProductOfArrayExceptSelfPrefixSum.java` - Combine prefix products from the left and suffix products from the right.
- `FindPivotIndexPrefixSum.java` - Use total sum plus a running left sum to derive the right sum instantly.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac RunningSumPrefixSum.java
java RunningSumPrefixSum
```
