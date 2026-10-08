# 10. Stack

## What this pattern is
A stack models 'last in, first out'. It is perfect for nested structure, monotonic processing, and deferred work that resolves when a new value appears.

## When to use it
- When parentheses or nested delimiters appear.
- When you need previous/next greater or smaller element.
- When the input should be processed with reversals or expression evaluation.
- When a monotonic stack can maintain candidate answers.

## How to identify it in interviews
- The current element resolves unfinished work from the past.
- You see nested symbols or matching pairs.
- The phrase next greater, previous smaller, or histogram appears.
- You naturally want to push and pop as conditions change.

## Upgrade path / mental model
Ask what should stay on the stack: raw values, indices, or pairs with metadata.

## Problems in this folder

### Easy
- `ValidParenthesesStack.java` - Use a stack to enforce the latest-opened, first-closed rule.
- `NextGreaterElementIStack.java` - Monotonic stack resolves each value when a larger one arrives.
- `RemoveAllAdjacentDuplicatesStack.java` - Cancel a character against the last surviving one.
- `ImplementQueueUsingStacksStack.java` - Two stacks reverse order twice to give FIFO behavior.
- `BaseballGameStack.java` - Apply score operations that always refer to the most recent entries.
- `MakeTheStringGreatStack.java` - Cancel each character against the top when they are the same letter in different cases.
- `BackspaceStringCompareStack.java` - Simulate each editor with a stack where '#' pops.
- `CrawlerLogFolderStack.java` - Treat the path as a stack and track only its depth.

### Medium
- `DailyTemperaturesStack.java` - Use a decreasing stack of unresolved temperature indices.
- `MinStackDesign.java` - Attach the running minimum to each pushed value.
- `EvaluateReversePolishNotationStack.java` - Operators consume the latest operands, which is exactly what a stack provides.
- `NextGreaterElementIIStack.java` - Walk the circular array twice with a monotonic stack of indices.
- `SimplifyPathStack.java` - Push directory names and pop on "..".
- `DecodeStringStack.java` - Save the count and prefix at '[' and resume them at ']'.
- `AsteroidCollisionStack.java` - A left-mover fights the most recent surviving right-movers first.
- `RemoveKDigitsStack.java` - Keep digits non-decreasing by popping larger digits within a removal budget.
- `OnlineStockSpanStack.java` - Merge spans of smaller-or-equal earlier prices into the new price.
- `BasicCalculatorIIStack.java` - Apply * and / to the top term immediately and sum the stack at the end.
- `MinimumRemoveToMakeValidParenthesesStack.java` - Track unmatched bracket indices and delete them.
- `OneThreeTwoPatternStack.java` - Scan from the right, tracking the best "2" popped by a larger "3".
- `RemoveAllAdjacentDuplicatesIIStack.java` - Stack (character, run length) pairs and pop a run when it reaches k.
- `ValidateStackSequencesStack.java` - Simulate the pushes and pop greedily whenever the top matches the next expected value.
- `SumOfSubarrayMinimumsStack.java` - Count the subarrays where each element is the minimum using a monotonic stack.
- `RemoveDuplicateLettersStack.java` - Pop larger letters that still appear later to build the smallest result.
- `CarFleetStack.java` - Sort by position and stack arrival times; a slower arrival starts a new fleet.

### Hard
- `LargestRectangleInHistogramStack.java` - Increasing stack gives the exact width for each popped bar.
- `LongestValidParenthesesStack.java` - Store indices with a base marker to measure valid stretches.
- `MaximalRectangleStack.java` - Turn each row into a histogram and reuse the largest-rectangle stack.
- `BasicCalculatorStack.java` - Save the outer result and sign on a stack at every opening parenthesis.
- `TrappingRainWaterStack.java` - Use a decreasing stack to fill basins layer by layer as taller bars arrive.


## How to run one file
From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac ValidParenthesesStack.java
java ValidParenthesesStack
```
