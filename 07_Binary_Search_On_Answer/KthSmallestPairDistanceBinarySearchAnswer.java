import java.util.*;

/*
Problem: Find K-th Smallest Pair Distance
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
The distance of a pair of integers a and b is the absolute difference |a - b|.
    Given an integer array nums and an integer k, return the kth smallest distance among all pairs
    nums[i] and nums[j] with i < j.

Why this pattern fits:
There are O(n^2) pairs, too many to list and sort for large inputs.
    Instead, guess a distance d and count how many pairs have distance <= d.
    The count grows with d, so the kth smallest distance is the smallest d whose count reaches k.

Approach:
Sort the array. Search d between 0 and (max - min).
    Count pairs with distance <= d using a sliding window on the sorted array:
    for each right index, advance left until nums[right] - nums[left] <= d, then add (right - left) pairs.

Worked Example:
Example:
    nums = [1, 3, 1], k = 1
    Sorted: [1, 1, 3]. Pair distances: 0, 2, 2.
    Count of pairs with distance <= 0 is 1, which already reaches k. Answer = 0.

Time Complexity: O(n log n + n log(max - min))
Space Complexity: O(n) for the sorted copy
*/

public class KthSmallestPairDistanceBinarySearchAnswer {
    private static int countPairsWithin(int[] sorted, int distance) {
        int count = 0;
        int left = 0;
        for (int right = 0; right < sorted.length; right++) {
            while (sorted[right] - sorted[left] > distance) {
                left++;
            }
            count += right - left;
        }
        return count;
    }

    public static int smallestDistancePair(int[] nums, int k) {
        int[] sorted = nums.clone();
        Arrays.sort(sorted);

        int left = 0;
        int right = sorted[sorted.length - 1] - sorted[0];

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (countPairsWithin(sorted, mid) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static void runDemo(int[] nums, int k) {
        System.out.println("Nums   : " + Arrays.toString(nums));
        System.out.println("k      : " + k);
        System.out.println("Answer : " + smallestDistancePair(nums, k));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 3, 1}, 1);
        runDemo(new int[]{1, 1, 1}, 2);
        runDemo(new int[]{1, 6, 1}, 3);
    }
}
