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

## Problem types covered
Every file in this folder belongs to one of these sub-patterns. Make sure you can solve at least one from each.

- **Matching brackets** - P01, P02, P30, P31, P44
- **Cancel adjacent items (undo stack)** - P06, P09, P10, P11, P12, P14, P24, P34
- **Simulation with a stack** - P08, P19, P20, P35, P36
- **Next greater or smaller (monotonic stack)** - P03, P04, P05, P13, P18, P27, P32, P33, P41, P43
- **Monotonic stack for the smallest or best subsequence** - P25, P26, P39, P40
- **Monotonic stack for contributions and areas** - P37, P38, P42, P45, P49
- **Expression evaluation** - P17, P28, P29, P46, P47, P48
- **Nested structures** - P21, P22, P23
- **Stack design** - P07, P15, P16, P50

## Problems in this folder

### Easy
- `P01_ValidParenthesesStack.java` - Use a stack to enforce the latest-opened, first-closed rule.
- `P02_RemoveOutermostParenthesesStack.java` - Track the nesting depth and keep every bracket that is not at depth zero.
- `P03_NextGreaterElementIStack.java` - Monotonic stack resolves each value when a larger one arrives.
- `P04_FinalPricesWithASpecialDiscountStack.java` - Each item waits on a stack until the first later price that is less than or equal to it arrives.
- `P05_NearestSmallerElementToTheLeftStack.java` - Keep an increasing stack of candidates; after popping larger values, the top is the answer.
- `P06_RemoveAllAdjacentDuplicatesStack.java` - Cancel a character against the last surviving one.
- `P07_ImplementQueueUsingStacksStack.java` - Two stacks reverse order twice to give FIFO behavior.
- `P08_BaseballGameStack.java` - Apply score operations that always refer to the most recent entries.
- `P09_MakeTheStringGreatStack.java` - Cancel each character against the top when they are the same letter in different cases.
- `P10_ClearDigitsStack.java` - Every digit deletes itself and the closest non-digit to its left, which is the top of the stack.
- `P11_BackspaceStringCompareStack.java` - Simulate each editor with a stack where '#' pops.
- `P12_CrawlerLogFolderStack.java` - Treat the path as a stack and track only its depth.

### Medium
- `P13_DailyTemperaturesStack.java` - Use a decreasing stack of unresolved temperature indices.
- `P14_RemovingStarsFromAStringStack.java` - A star pops the most recent surviving character.
- `P15_MinStackDesign.java` - Attach the running minimum to each pushed value.
- `P16_DesignAStackWithIncrementOperationStack.java` - Store pending increments lazily at the highest affected index and pass them down on pop.
- `P17_EvaluateReversePolishNotationStack.java` - Operators consume the latest operands, which is exactly what a stack provides.
- `P18_NextGreaterElementIIStack.java` - Walk the circular array twice with a monotonic stack of indices.
- `P19_SimplifyPathStack.java` - Push directory names and pop on "..".
- `P20_LongestAbsoluteFilePathStack.java` - The stack holds the path length at each directory depth; tabs tell how far to pop back.
- `P21_DecodeStringStack.java` - Save the count and prefix at '[' and resume them at ']'.
- `P22_ScoreOfParenthesesStack.java` - Push a fresh score for each open bracket and fold it into the parent when the bracket closes.
- `P23_ReverseSubstringsBetweenEachPairOfParenthesesStack.java` - Push the outer text at '(' and, at ')', reverse the inner text and append it to what was saved.
- `P24_AsteroidCollisionStack.java` - A left-mover fights the most recent surviving right-movers first.
- `P25_RemoveKDigitsStack.java` - Keep digits non-decreasing by popping larger digits within a removal budget.
- `P26_FindTheMostCompetitiveSubsequenceStack.java` - Pop larger values while enough elements remain to still reach length k.
- `P27_OnlineStockSpanStack.java` - Merge spans of smaller-or-equal earlier prices into the new price.
- `P28_BasicCalculatorIIStack.java` - Apply * and / to the top term immediately and sum the stack at the end.
- `P29_InfixToPostfixConversionStack.java` - Operands go straight to the output; operators wait on a stack until a weaker one arrives.
- `P30_MinimumRemoveToMakeValidParenthesesStack.java` - Track unmatched bracket indices and delete them.
- `P31_ValidParenthesisStringStack.java` - Keep one stack of open-bracket indices and one of star indices; stars rescue unmatched brackets.
- `P32_OneThreeTwoPatternStack.java` - Scan from the right, tracking the best "2" popped by a larger "3".
- `P33_MaximumWidthRampStack.java` - Stack the candidate left ends in decreasing order, then scan from the right popping every left end that fits.
- `P34_RemoveAllAdjacentDuplicatesIIStack.java` - Stack (character, run length) pairs and pop a run when it reaches k.
- `P35_ValidateStackSequencesStack.java` - Simulate the pushes and pop greedily whenever the top matches the next expected value.
- `P36_ExclusiveTimeOfFunctionsStack.java` - Simulate the call stack and charge each elapsed interval to the function currently on top.
- `P37_SumOfSubarrayMinimumsStack.java` - Count the subarrays where each element is the minimum using a monotonic stack.
- `P38_SumOfSubarrayRangesStack.java` - Sum of all subarray maximums minus sum of all subarray minimums, each by monotonic-stack contribution.
- `P39_RemoveDuplicateLettersStack.java` - Pop larger letters that still appear later to build the smallest result.
- `P40_MinimumCostTreeFromLeafValuesStack.java` - Remove each local minimum by pairing it with its smaller neighbor, using a decreasing stack.
- `P41_CarFleetStack.java` - Sort by position and stack arrival times; a slower arrival starts a new fleet.

### Hard
- `P42_LargestRectangleInHistogramStack.java` - Increasing stack gives the exact width for each popped bar.
- `P43_NumberOfVisiblePeopleInAQueueStack.java` - Scan from the right with a decreasing stack; each person sees everyone they pop plus one taller person.
- `P44_LongestValidParenthesesStack.java` - Store indices with a base marker to measure valid stretches.
- `P45_MaximalRectangleStack.java` - Turn each row into a histogram and reuse the largest-rectangle stack.
- `P46_BasicCalculatorStack.java` - Save the outer result and sign on a stack at every opening parenthesis.
- `P47_BasicCalculatorIIIStack.java` - Two stacks - values and operators - with precedence deciding when an operator is applied.
- `P48_ParsingABooleanExpressionStack.java` - Push characters until ')', then pop the operands back to '(' and replace the group with its result.
- `P49_TrappingRainWaterStack.java` - Use a decreasing stack to fill basins layer by layer as taller bars arrive.
- `P50_MaximumFrequencyStackStack.java` - Keep one stack per frequency level; pop from the stack of the current maximum frequency.

## How to run one file
The number in each file name is the suggested solving order (P01 first, P50 last).

From inside this folder:

```bash
javac FileName.java
java FileName
```

Example:

```bash
javac P01_ValidParenthesesStack.java
java P01_ValidParenthesesStack
```
