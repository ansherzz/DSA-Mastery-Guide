import java.util.*;

/*
Problem: Combination Sum
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given an array of distinct candidate numbers and a target, return all unique combinations where
    the chosen numbers sum to the target. You may reuse the same candidate multiple times.

    This is a backtracking problem with pruning and controlled reuse.

Why this pattern fits:
Each recursive decision chooses how to grow the current sum.
    Reuse is allowed, so the same index can be chosen again in deeper calls.

Approach:
Sort is not strictly necessary here, but it helps pruning.
    Recurse with a remaining target. Stop when it becomes negative, and record the path when it reaches zero.

Worked Example:
Example:
    candidates = [2, 3, 6, 7], target = 7
    Valid combinations are [2,2,3] and [7].

Time Complexity: Exponential in the number of combinations
Space Complexity: O(target/minCandidate) recursion depth in the worst case
*/

public class P07_CombinationSumBacktracking {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> answer = new ArrayList<>();
        backtrack(0, target, candidates, new ArrayList<>(), answer);
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
            path.add(candidates[i]);
            backtrack(i, remain - candidates[i], candidates, path, answer);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
    }
}
