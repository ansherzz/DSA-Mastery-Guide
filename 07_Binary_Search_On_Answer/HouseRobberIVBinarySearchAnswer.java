import java.util.*;

/*
Problem: House Robber IV
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
Houses stand in a row and nums[i] is the money in house i. A robber refuses to rob two adjacent houses.
    The robber's capability is the maximum amount taken from any single house that was robbed.
    The robber must rob at least k houses.

    Return the minimum capability that still allows robbing at least k houses.

Why this pattern fits:
The task is to minimize a maximum. With a capability cap c, the robber may only touch houses with nums[i] <= c.
    A larger cap never reduces the number of houses that can be robbed, so feasibility is monotonic.

Approach:
Search c between the smallest and largest value in nums.
    For a candidate c, greedily count houses: walk left to right, rob a house whenever its value is at most c,
    and skip the next house. Taking the earliest eligible house is always optimal for the count.
    If the count reaches k, c is feasible.

Worked Example:
Example:
    nums = [2, 3, 5, 9], k = 2
    c = 5: rob house 0 (2), skip house 1, rob house 2 (5). Count = 2. Feasible.
    c = 4: rob house 0 (2), skip house 1, house 2 is too rich, house 3 is too rich. Count = 1.
    Answer = 5.

Time Complexity: O(n log(max(nums)))
Space Complexity: O(1)
*/

public class HouseRobberIVBinarySearchAnswer {
    private static boolean canRob(int[] nums, int k, int capability) {
        int robbed = 0;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] <= capability) {
                robbed++;
                i += 2;
            } else {
                i++;
            }
        }
        return robbed >= k;
    }

    public static int minCapability(int[] nums, int k) {
        int left = Integer.MAX_VALUE;
        int right = 0;
        for (int num : nums) {
            left = Math.min(left, num);
            right = Math.max(right, num);
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (canRob(nums, k, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Houses     : " + Arrays.toString(nums));
        System.out.println("k          : " + k);
        System.out.println("Capability : " + minCapability(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 3, 5, 9}, 2);
        runDemo(new int[]{2, 7, 9, 3, 1}, 2);
    }
}
