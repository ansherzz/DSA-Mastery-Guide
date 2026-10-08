import java.util.*;

/*
Problem: Sum of Mutated Array Closest to Target
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Given an integer array arr and a target value, return the integer value such that when all integers
    larger than value in the array are changed to be equal to value, the sum of the array gets
    as close as possible (in absolute difference) to target.
    In case of a tie, return the minimum such integer. The answer is not necessarily a number from arr.

Why this pattern fits:
The mutated sum never decreases as value grows, so the first value whose sum reaches the target can be binary searched.
    The twist is that the best answer is "closest", so both sides of that boundary must be compared.

Approach:
Define mutatedSum(v) = sum of min(x, v) over the array.
    Binary search for the smallest v in [0, max(arr)] with mutatedSum(v) >= target.
    The closest sum is produced either by that v or by v - 1.
    Compare the two distances and prefer v - 1 on a tie, since the smaller value wins ties.

Worked Example:
Example:
    arr = [4, 9, 3], target = 10
    value = 3 gives [3, 3, 3] with sum 9. value = 4 gives [4, 4, 3] with sum 11.
    Both are at distance 1, so return the smaller value, 3.

Time Complexity: O(n log(max value))
Space Complexity: O(1)
*/

public class SumOfMutatedArrayClosestToTargetAdvanced {
    public static int findBestValue(int[] arr, int target) {
        int low = 0;
        int high = 0;
        for (int num : arr) {
            high = Math.max(high, num);
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (mutatedSum(arr, mid) >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        if (low > 0 && Math.abs(mutatedSum(arr, low - 1) - target) <= Math.abs(mutatedSum(arr, low) - target)) {
            return low - 1;
        }
        return low;
    }

    private static long mutatedSum(int[] arr, int value) {
        long sum = 0;
        for (int num : arr) {
            sum += Math.min(num, value);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(findBestValue(new int[]{4, 9, 3}, 10));
        System.out.println(findBestValue(new int[]{2, 3, 5}, 10));
        System.out.println(findBestValue(new int[]{60864, 25176, 27249, 21296, 20204}, 56803));
    }
}
