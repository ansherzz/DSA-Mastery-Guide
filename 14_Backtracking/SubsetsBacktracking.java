import java.util.*;

/*
Problem: Subsets
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given an array of distinct integers, return all possible subsets (the power set).

    Each element gives you a binary choice: include it or skip it.

Why this pattern fits:
This is one of the cleanest backtracking problems.
    It teaches how to build a path, recurse, and undo the last choice.

Approach:
Backtrack using an index.
    At each step, either include the current element and continue, or skip to the next choice.
    A common implementation adds the current path to the answer on every recursive call.

Worked Example:
Example:
    nums = [1, 2, 3]
    The output contains [], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3].

Time Complexity: O(n * 2^n)
Space Complexity: O(n) recursion depth, excluding output
*/

public class SubsetsBacktracking {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), answer);
        return answer;
    }

    private static void backtrack(int start, int[] nums, List<Integer> path, List<List<Integer>> answer) {
        answer.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(i + 1, nums, path, answer);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }
}
