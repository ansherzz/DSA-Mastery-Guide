import java.util.*;

/*
Problem: Partition to K Equal Sum Subsets
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
Given an integer array nums and an integer k, return true if it is possible to divide the array
    into k non-empty subsets whose sums are all equal. Every element must be used exactly once.

Why this pattern fits:
There is no greedy rule that always works, so the assignment of numbers to subsets must be searched.
    Backtracking fills one subset at a time and relies on strong pruning to stay fast.

Approach:
If the total is not divisible by k, the answer is false. Otherwise each subset must sum to total / k.
    Sort in descending order so large numbers are placed first and dead ends appear early.
    Fill one bucket at a time: pick unused numbers that still fit, and when the bucket reaches the target,
    start the next bucket from the beginning of the array.
    Skip a number equal to the previous one if that previous one was just rejected, since it would fail the same way.

Worked Example:
Example:
    nums = [4, 3, 2, 3, 5, 2, 1], k = 4
    Total = 20, so each subset must sum to 5.
    One valid split: [5], [4, 1], [3, 2], [3, 2]. Answer = true.

Time Complexity: O(k * 2^n) in the worst case
Space Complexity: O(n)
*/

public class P16_PartitionToKEqualSumSubsetsBacktracking {
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (total % k != 0) {
            return false;
        }

        int target = total / k;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        for (int i = 0, j = sorted.length - 1; i < j; i++, j--) {
            int temp = sorted[i];
            sorted[i] = sorted[j];
            sorted[j] = temp;
        }
        if (sorted[0] > target) {
            return false;
        }

        return fill(sorted, new boolean[sorted.length], 0, k, 0, target);
    }

    private static boolean fill(int[] nums, boolean[] used, int start, int bucketsLeft, int currentSum, int target) {
        if (bucketsLeft == 1) {
            return true;
        }
        if (currentSum == target) {
            return fill(nums, used, 0, bucketsLeft - 1, 0, target);
        }

        for (int i = start; i < nums.length; i++) {
            if (used[i] || currentSum + nums[i] > target) {
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            if (fill(nums, used, i + 1, bucketsLeft, currentSum + nums[i], target)) {
                return true;
            }
            used[i] = false;

            if (currentSum == 0) {
                break;
            }
        }
        return false;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Nums = " + Arrays.toString(nums) + ", k = " + k + " -> " + canPartitionKSubsets(nums, k));
    }

    public static void main(String[] args) {
        runDemo(new int[]{4, 3, 2, 3, 5, 2, 1}, 4);
        runDemo(new int[]{1, 2, 3, 4}, 3);
        runDemo(new int[]{2, 2, 2, 2, 3, 4, 5}, 4);
    }
}
