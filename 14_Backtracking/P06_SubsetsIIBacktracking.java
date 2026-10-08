import java.util.*;

/*
Problem: Subsets II
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given an integer array that may contain duplicates, return all possible subsets (the power set).
    The solution set must not contain duplicate subsets. The subsets may be returned in any order.

Why this pattern fits:
This is the Subsets problem with one extra rule. The backtracking tree is the same,
    but identical values would create identical branches. Sorting places duplicates next to each other
    so the repeated branches can be skipped at the moment they would be created.

Approach:
Sort the array. Record the current path at every node of the recursion.
    In the loop that chooses the next element, skip a value if it equals the previous value at the same depth
    (i > start and nums[i] == nums[i - 1]). Choosing the first copy already covers every subset the second copy could start.

Worked Example:
Example:
    nums = [1, 2, 2]
    Subsets: [], [1], [1, 2], [1, 2, 2], [2], [2, 2]
    The second [1, 2] and the second [2] are skipped.

Time Complexity: O(n * 2^n)
Space Complexity: O(n) recursion depth, excluding the output
*/

public class P06_SubsetsIIBacktracking {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        List<List<Integer>> answer = new ArrayList<>();
        backtrack(0, sorted, new ArrayList<>(), answer);
        return answer;
    }

    private static void backtrack(int start, int[] nums, List<Integer> path, List<List<Integer>> answer) {
        answer.add(new ArrayList<>(path));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backtrack(i + 1, nums, path, answer);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(subsetsWithDup(new int[]{1, 2, 2}));
        System.out.println(subsetsWithDup(new int[]{0}));
    }
}
