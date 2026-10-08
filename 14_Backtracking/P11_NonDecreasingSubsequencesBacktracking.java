import java.util.*;

/*
Problem: Non-decreasing Subsequences
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Return all different non-decreasing subsequences of nums with at least two elements.

Why this pattern fits:
It is subset generation with an ordering constraint. The input cannot be sorted (order matters),
so duplicates are avoided by never starting two branches with the same value at the same recursion level.

Approach:
dfs(start): record the path if it has two or more elements. For i from start, skip nums[i] if it is smaller
than the path's last value or was already used at this level; otherwise append, recurse with i + 1, and remove.

Worked Example:
nums = [4, 6, 7, 7] -> [4,6], [4,6,7], [4,6,7,7], [4,7], [4,7,7], [6,7], [6,7,7], [7,7].

Time Complexity: O(n * 2^n)
Space Complexity: O(n)
*/

public class P11_NonDecreasingSubsequencesBacktracking {
    private static void dfs(int[] nums, int start, List<Integer> path, List<List<Integer>> result) {
        if (path.size() >= 2) {
            result.add(new ArrayList<>(path));
        }
        Set<Integer> usedAtThisLevel = new HashSet<>();
        for (int i = start; i < nums.length; i++) {
            boolean tooSmall = !path.isEmpty() && nums[i] < path.get(path.size() - 1);
            if (tooSmall || !usedAtThisLevel.add(nums[i])) {
                continue;
            }
            path.add(nums[i]);
            dfs(nums, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }

    public static List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[4, 6, 7, 7] -> " + findSubsequences(new int[]{4, 6, 7, 7}));
    }
}
