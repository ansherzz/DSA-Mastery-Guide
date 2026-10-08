import java.util.*;

/*
Problem: Factor Combinations
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Numbers can be regarded as the product of their factors. For example, 8 = 2 x 2 x 2 = 2 x 4.
    Given an integer n, return all possible combinations of its factors.
    Factors must be in the range [2, n - 1], and each combination should be listed once.

Why this pattern fits:
This is a combination search where the "remaining target" shrinks by division instead of subtraction.
    As in Combination Sum, forcing the factors to be non-decreasing lists each combination exactly once.

Approach:
Recurse with the remaining value and the smallest factor allowed.
    Try each factor f from that minimum while f * f <= remaining.
    If f divides remaining, then [chosen factors..., f, remaining / f] is a complete combination, so record it.
    Then keep f in the path and recurse on remaining / f with minimum f, to split the quotient further.
    The bound f * f <= remaining guarantees the quotient is at least f, which keeps every combination sorted.

Worked Example:
Example:
    n = 12
    f = 2: record [2, 6], then split 6 with minimum 2 -> record [2, 2, 3].
    f = 3: record [3, 4]. Result: [[2, 6], [2, 2, 3], [3, 4]].

Time Complexity: roughly O(sqrt(n)) per recursion level; the number of combinations is small in practice
Space Complexity: O(log n) recursion depth
*/

public class P34_FactorCombinationsBacktracking {
    public static List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(n, 2, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int remaining, int minFactor, List<Integer> path, List<List<Integer>> result) {
        for (int factor = minFactor; (long) factor * factor <= remaining; factor++) {
            if (remaining % factor != 0) {
                continue;
            }

            path.add(factor);
            List<Integer> combination = new ArrayList<>(path);
            combination.add(remaining / factor);
            result.add(combination);

            backtrack(remaining / factor, factor, path, result);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(getFactors(12));
        System.out.println(getFactors(32));
        System.out.println(getFactors(37));
        System.out.println(getFactors(1));
    }
}
