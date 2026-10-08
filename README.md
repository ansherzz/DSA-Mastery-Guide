# DSA Pattern Mastery in Java

This bundle is a study pack built around 20 core DSA patterns.

## What is included
- 20 pattern folders
- 1 README inside each folder explaining:
  - what the pattern is
  - when to use it
  - how to identify it in interviews
  - a mental model / upgrade path
  - the problem types (sub-patterns) the folder covers, with the files that belong to each
  - the runnable Java files in that folder, grouped from Easy to Medium to Hard
- 50 interview-level problems for every pattern (1,000 in total), each solved in its own runnable Java program
- every Java file includes:
  - a full interview-style problem statement in comments
  - the difficulty level (Easy, Medium, or Hard)
  - why the pattern fits
  - the approach
  - time and space complexity
  - a `main` method with sample runs

## Folder order
1. Array & String Foundations - Brute Force Thinking
2. Hashing
3. Two Pointers
4. Sliding Window
5. Prefix Sum
6. Binary Search - Classic Variations
7. Binary Search on Answer
8. Advanced Search
9. Linked List
10. Stack
11. Queue / BFS
12. Trees & Recursion - DFS
13. BST
14. Backtracking
15. Divide & Conquer
16. Graph DFS
17. Graph BFS
18. Union Find
19. Heap & Greedy
20. Dynamic Programming

## How to study this pack
1. Read the folder README first.
2. Work through the files in numbered order (P01 to P50); each README lists them in the same order, from easy to hard.
3. Solve the problem on your own.
4. Then read the commented Java solution.
5. Run the file and inspect the sample output.
6. After finishing a folder, summarize:
   - how to identify the pattern
   - what invariant made the solution work
   - what edge cases were important

## How to run a single file
From the folder containing the file:

```bash
javac FileName.java
java FileName
```

The number in each file name is the suggested solving order inside its folder (P01 first, P50 last).

Example:

```bash
cd 03_Two_Pointers
javac P23_ThreeSumTwoPointers.java
java P23_ThreeSumTwoPointers
```

## How to compile everything
From the project root:

```bash
bash compile_all.sh
```

The script compiles each Java file independently so you can verify the full pack quickly.

## Suggested routine
- Spend one day per folder if you want a 20-day pattern sprint: do the Easy problems and a few Medium ones first, then come back for the rest.
- Revisit folders 3, 4, 7, 12, 16, 19, and 20 for revision.
- After each 5-day block, mix problems from earlier folders without looking at the README first.
