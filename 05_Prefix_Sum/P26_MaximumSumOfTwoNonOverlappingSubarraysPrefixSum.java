import java.util.*;

/*
Problem: Maximum Sum of Two Non-Overlapping Subarrays
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given an integer array nums and two integers firstLen and secondLen,
    return the maximum sum of elements in two non-overlapping subarrays with lengths firstLen and secondLen.
    The subarray of length firstLen may occur before or after the subarray of length secondLen.

Why this pattern fits:
Both subarrays have fixed lengths, so each candidate is a range sum that a prefix array answers in O(1).
    The remaining work is pairing each right-hand subarray with the best left-hand subarray seen so far.

Approach:
Solve the ordered version: a subarray of length leftLen entirely before a subarray of length rightLen.
    Slide the right subarray's end across the array. Just before each position,
    update bestLeft with the newest left subarray that ends where the right one begins.
    Combine bestLeft with the current right sum.
    Run the ordered version twice with the lengths swapped and take the larger result.

Worked Example:
Example:
    nums = [0, 6, 5, 2, 2, 5, 1, 9, 4], firstLen = 1, secondLen = 2
    Choose [9] and [6, 5]. Answer = 20.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P26_MaximumSumOfTwoNonOverlappingSubarraysPrefixSum {
    public static int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {
        int[] prefix = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }
        return Math.max(ordered(prefix, firstLen, secondLen), ordered(prefix, secondLen, firstLen));
    }

    private static int ordered(int[] prefix, int leftLen, int rightLen) {
        int bestLeft = 0;
        int best = 0;

        for (int end = leftLen + rightLen; end < prefix.length; end++) {
            int split = end - rightLen;
            bestLeft = Math.max(bestLeft, prefix[split] - prefix[split - leftLen]);
            best = Math.max(best, bestLeft + prefix[end] - prefix[split]);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(maxSumTwoNoOverlap(new int[]{0, 6, 5, 2, 2, 5, 1, 9, 4}, 1, 2));
        System.out.println(maxSumTwoNoOverlap(new int[]{3, 8, 1, 3, 2, 1, 8, 9, 0}, 3, 2));
        System.out.println(maxSumTwoNoOverlap(new int[]{2, 1, 5, 6, 0, 9, 5, 0, 3, 8}, 4, 3));
    }
}
