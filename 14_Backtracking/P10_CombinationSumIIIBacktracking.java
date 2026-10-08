import java.util.*;

/*
Problem: Combination Sum III
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Find all valid combinations of k numbers that sum up to n, under these rules:
    - only the numbers 1 through 9 may be used
    - each number may be used at most once

    Return all valid combinations. The same combination must not appear twice.

Why this pattern fits:
There are two constraints to satisfy at once: the count of numbers and their sum.
    Backtracking tracks both as it goes and cuts a branch as soon as either one becomes impossible.

Approach:
Choose numbers in increasing order from 1 to 9.
    Stop a branch when the path already has k numbers, or when the next number is larger than the remaining sum.
    Record the path when it has exactly k numbers and the remaining sum is zero.

Worked Example:
Example:
    k = 3, n = 9
    Combinations: [1, 2, 6], [1, 3, 5], [2, 3, 4]

Time Complexity: O(C(9, k) * k)
Space Complexity: O(k) recursion depth, excluding the output
*/

public class P10_CombinationSumIIIBacktracking {
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> answer = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), answer);
        return answer;
    }

    private static void backtrack(int start, int k, int remain, List<Integer> path, List<List<Integer>> answer) {
        if (path.size() == k) {
            if (remain == 0) {
                answer.add(new ArrayList<>(path));
            }
            return;
        }

        for (int digit = start; digit <= 9; digit++) {
            if (digit > remain) {
                break;
            }
            path.add(digit);
            backtrack(digit + 1, k, remain - digit, path, answer);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 7));
        System.out.println(combinationSum3(3, 9));
        System.out.println(combinationSum3(4, 1));
    }
}
