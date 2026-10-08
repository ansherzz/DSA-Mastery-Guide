import java.util.*;

/*
Problem: Minimize the Maximum Difference of Pairs
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
You are given an integer array nums and an integer p.
    Find p pairs of indices such that no index appears in more than one pair
    and the maximum difference |nums[i] - nums[j]| among all the pairs is minimized.
    Return that minimized maximum difference. For p = 0 the answer is 0.

Why this pattern fits:
"Minimize the maximum" with a countable feasibility check is the signature of this pattern.
    If p pairs can be formed with every difference <= D, they can also be formed for any larger D.

Approach:
Sort the array, since the closest partner of any value is one of its neighbors.
    To test a limit D, scan left to right: if nums[i + 1] - nums[i] <= D, pair them and skip both;
    otherwise skip only nums[i]. Pairing greedily at the earliest opportunity never reduces the number of pairs.
    Binary search D between 0 and (max - min) for the smallest value that yields at least p pairs.

Worked Example:
Example:
    nums = [10, 1, 2, 7, 1, 3], p = 2
    Sorted: [1, 1, 2, 3, 7, 10]. With D = 1: pair (1, 1) and (2, 3). With D = 0 only (1, 1) is possible. Answer = 1.

Time Complexity: O(n log n + n log(value range))
Space Complexity: O(1) besides the sort
*/

public class P33_MinimizeMaximumDifferenceOfPairsBinarySearchAnswer {
    public static int minimizeMax(int[] nums, int p) {
        Arrays.sort(nums);
        int low = 0;
        int high = nums[nums.length - 1] - nums[0];

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (countPairs(nums, mid) >= p) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private static int countPairs(int[] nums, int limit) {
        int pairs = 0;
        int i = 0;
        while (i + 1 < nums.length) {
            if (nums[i + 1] - nums[i] <= limit) {
                pairs++;
                i += 2;
            } else {
                i++;
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        System.out.println(minimizeMax(new int[]{10, 1, 2, 7, 1, 3}, 2));
        System.out.println(minimizeMax(new int[]{4, 2, 1, 2}, 1));
        System.out.println(minimizeMax(new int[]{3, 4, 2, 3, 2, 1, 2}, 3));
    }
}
