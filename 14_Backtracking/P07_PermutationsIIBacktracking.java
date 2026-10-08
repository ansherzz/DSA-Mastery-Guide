import java.util.*;

/*
Problem: Permutations II
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given a collection of numbers that might contain duplicates, return all possible unique permutations in any order.

Why this pattern fits:
Permutations are built position by position, trying every unused number in each position.
    Duplicates make different index choices produce the same arrangement,
    so the search needs a rule that lets only one of the equal choices proceed.

Approach:
Sort the array and keep a used[] array.
    At each position, try every unused index. Skip index i when nums[i] equals nums[i - 1]
    and the earlier copy is not currently used. This forces equal values to be used in left-to-right order,
    so each distinct arrangement is generated exactly once.

Worked Example:
Example:
    nums = [1, 1, 2]
    Unique permutations: [1, 1, 2], [1, 2, 1], [2, 1, 1]
    Starting with the second 1 while the first 1 is unused is skipped, because it would repeat the same results.

Time Complexity: O(n * n!) in the worst case
Space Complexity: O(n) recursion depth, excluding the output
*/

public class P07_PermutationsIIBacktracking {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        List<List<Integer>> answer = new ArrayList<>();
        backtrack(sorted, new boolean[sorted.length], new ArrayList<>(), answer);
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
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
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
        System.out.println(permuteUnique(new int[]{1, 1, 2}));
        System.out.println(permuteUnique(new int[]{2, 2, 1, 1}).size() + " unique permutations of [2, 2, 1, 1]");
    }
}
