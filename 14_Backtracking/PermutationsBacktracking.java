import java.util.*;

/*
Problem: Permutations
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given an array of distinct integers, return all possible permutations.

    Unlike subsets, order matters, so you must track which elements are already used.

Why this pattern fits:
A permutation chooses one unused element for each position.
    That makes backtracking with a used[] array a natural fit.

Approach:
Build the current permutation one element at a time.
    At each step, try every value that has not yet been used in the current path.

Worked Example:
Example:
    nums = [1, 2, 3]
    The output contains all 6 orderings.

Time Complexity: O(n * n!)
Space Complexity: O(n)
*/

public class PermutationsBacktracking {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), answer);
        return answer;
    }

    private static void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> answer) {
        if (path.size() == nums.length) {
            answer.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            backtrack(nums, used, path, answer);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
    }
}
