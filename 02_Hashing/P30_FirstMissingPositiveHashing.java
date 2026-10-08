import java.util.*;

/*
Problem: First Missing Positive
Pattern: 02. Hashing
Difficulty: Hard

Interview Prompt:
Given an unsorted integer array nums, return the smallest positive integer that is not present in nums.
    You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.

Why this pattern fits:
With a HashSet the problem is easy: insert everything, then test 1, 2, 3, ...
    The O(1) space requirement is met by noticing that the answer is always in [1, n + 1],
    so the array itself can serve as the hash table: value v belongs at index v - 1.

Approach:
For each index, while the value v at that index is in [1, n] and is not already at its home index v - 1,
    swap it into its home. Each swap places one value correctly, so the total work is O(n).
    Afterward, the first index i where nums[i] != i + 1 gives the answer i + 1.
    If every index is correct, the answer is n + 1.

Worked Example:
Example:
    nums = [3, 4, -1, 1]
    After placing values at their home indices: [1, -1, 3, 4].
    Index 1 does not hold 2, so the answer is 2.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P30_FirstMissingPositiveHashing {
    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            while (nums[i] >= 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int home = nums[i] - 1;
                int temp = nums[home];
                nums[home] = nums[i];
                nums[i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static void main(String[] args) {
        System.out.println(firstMissingPositive(new int[]{1, 2, 0}));
        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1}));
        System.out.println(firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
        System.out.println(firstMissingPositive(new int[]{1, 1, 2, 3}));
    }
}
