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
- `ValidParenthesesStack.java` - Use a stack to enforce the latest-opened, first-closed rule.
- `DailyTemperaturesStack.java` - Use a decreasing stack of unresolved temperature indices.
- `LargestRectangleInHistogramStack.java` - Increasing stack gives the exact width for each popped bar.
- `MinStackDesign.java` - Attach the running minimum to each pushed value.
- `EvaluateReversePolishNotationStack.java` - Operators consume the latest operands, which is exactly what a stack provides.


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
