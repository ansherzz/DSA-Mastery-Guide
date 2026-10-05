import java.util.*;

/*
Problem: Combinations
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given two integers n and k, return all possible combinations of k numbers chosen from the range [1, n].
    The order of numbers inside a combination does not matter, and the combinations may be returned in any order.

Why this pattern fits:
A combination is a sequence of choices where each choice must be larger than the previous one.
    Backtracking explores the choice tree and stops at depth k. Always moving forward avoids counting
    the same set in a different order.

Approach:
Build the combination in increasing order, starting the next choice from (last chosen + 1).
    When the path has k numbers, record a copy.
    Prune branches that cannot be completed: if fewer numbers remain than are still needed, stop the loop early.

Worked Example:
Example:
    n = 4, k = 2
    Combinations: [1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]

Time Complexity: O(k * C(n, k))
Space Complexity: O(k) recursion depth, excluding the output
*/

public class CombinationsBacktracking {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> answer = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), answer);
        return answer;
    }

    private static void backtrack(int start, int n, int k, List<Integer> path, List<List<Integer>> answer) {
        if (path.size() == k) {
            answer.add(new ArrayList<>(path));
            return;
        }

        int stillNeeded = k - path.size();
        for (int value = start; value <= n - stillNeeded + 1; value++) {
            path.add(value);
            backtrack(value + 1, n, k, path, answer);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combine(4, 2));
        System.out.println(combine(1, 1));
        System.out.println(combine(5, 3).size() + " combinations of 3 numbers from 1..5");
    }
}
