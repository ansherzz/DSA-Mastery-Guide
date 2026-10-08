import java.util.*;

/*
Problem: Majority Element using Divide and Conquer
Pattern: 15. Divide & Conquer
Difficulty: Easy

Interview Prompt:
Given an array of size n, return the majority element: the value that appears more than n / 2 times.
    You may assume a majority element always exists.

    Solve it with divide and conquer rather than a hash map.

Why this pattern fits:
If a value is the majority of the whole array, it must be the majority of at least one of the two halves.
    So the answer is one of at most two candidates, one from each half, and a linear count decides between them.

Approach:
Base case: a single element is its own majority.
    Recursively find the majority of the left half and of the right half.
    If they agree, that value is the majority of the whole range.
    Otherwise count both candidates in the current range and return the one that appears more often.

Worked Example:
Example:
    nums = [2, 2, 1, 1, 1, 2, 2]
    Left half [2, 2, 1, 1] returns 2 (a tie between 2 and 1 resolved by counting),
    right half [1, 2, 2] returns 2. Both agree, so the answer is 2.

Time Complexity: O(n log n)
Space Complexity: O(log n) recursion stack
*/

public class P03_MajorityElementDivideConquer {
    public static int majorityElement(int[] nums) {
        return majority(nums, 0, nums.length - 1);
    }

    private static int majority(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int mid = left + (right - left) / 2;
        int leftMajority = majority(nums, left, mid);
        int rightMajority = majority(nums, mid + 1, right);
        if (leftMajority == rightMajority) {
            return leftMajority;
        }

        int leftCount = count(nums, leftMajority, left, right);
        int rightCount = count(nums, rightMajority, left, right);
        return leftCount >= rightCount ? leftMajority : rightMajority;
    }

    private static int count(int[] nums, int value, int left, int right) {
        int occurrences = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == value) {
                occurrences++;
            }
        }
        return occurrences;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums = " + Arrays.toString(nums) + " -> majority " + majorityElement(nums));
    }

    public static void main(String[] args) {
        runDemo(new int[]{3, 2, 3});
        runDemo(new int[]{2, 2, 1, 1, 1, 2, 2});
        runDemo(new int[]{6, 5, 5});
    }
}
