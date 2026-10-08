import java.util.*;

/*
Problem: Combination Sum II
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given a collection of candidate numbers (which may contain duplicates) and a target,
    find all unique combinations of candidates that sum to the target.
    Each number in candidates may be used at most once in a combination.
    The solution set must not contain duplicate combinations.

Why this pattern fits:
This combines two backtracking rules: each element is used once (so recurse from i + 1),
    and duplicate values must not create duplicate combinations (so skip equal siblings).
    Sorting enables both the duplicate skip and early termination when a value exceeds the remainder.

Approach:
Sort the candidates.
    In the choice loop, break when the candidate is larger than the remaining target,
    and skip a candidate equal to the previous one at the same depth.
    Recurse with i + 1 so the same element is not reused.

Worked Example:
Example:
    candidates = [10, 1, 2, 7, 6, 1, 5], target = 8
    Sorted: [1, 1, 2, 5, 6, 7, 10]
    Combinations: [1, 1, 6], [1, 2, 5], [1, 7], [2, 6]

Time Complexity: O(2^n) in the worst case
Space Complexity: O(n) recursion depth, excluding the output
*/

public class P09_CombinationSumIIBacktracking {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int[] sorted = candidates.clone();
        Arrays.sort(sorted);

        List<List<Integer>> answer = new ArrayList<>();
        backtrack(0, target, sorted, new ArrayList<>(), answer);
        return answer;
    }

    private static void backtrack(int start, int remain, int[] candidates, List<Integer> path, List<List<Integer>> answer) {
        if (remain == 0) {
            answer.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remain) {
                break;
            }
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            path.add(candidates[i]);
            backtrack(i + 1, remain - candidates[i], candidates, path, answer);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
        System.out.println(combinationSum2(new int[]{2, 5, 2, 1, 2}, 5));
    }
}
