import java.util.*;

/*
Problem: Range Sum Query - Immutable
Pattern: 05. Prefix Sum
Difficulty: Easy

Interview Prompt:
Design a class that can answer many queries of the form: what is the sum of nums[left..right]?
    The array never changes after construction.

    This is the canonical 'many queries on a static array' prefix-sum problem.

Why this pattern fits:
Instead of summing each range again and again, preprocess once.
    Then each query is just a subtraction of two prefix values.

Approach:
Store a prefix array of length n + 1 where prefix[i] is the sum of the first i numbers.
    Then sumRange(left, right) = prefix[right + 1] - prefix[left].

Worked Example:
Example:
    nums = [-2, 0, 3, -5, 2, -1]
    sumRange(0, 2) = 1 and sumRange(2, 5) = -1.

Time Complexity: Construction: O(n), Query: O(1)
Space Complexity: O(n)
*/

public class RangeSumQueryImmutablePrefixSum {
    static class NumArray {
        private final int[] prefix;

        NumArray(int[] nums) {
            prefix = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                prefix[i + 1] = prefix[i] + nums[i];
            }
        }

        int sumRange(int left, int right) {
            return prefix[right + 1] - prefix[left];
        }
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        NumArray numArray = new NumArray(nums);

        System.out.println("Nums          : " + Arrays.toString(nums));
        System.out.println("sumRange(0,2) : " + numArray.sumRange(0, 2));
        System.out.println("sumRange(2,5) : " + numArray.sumRange(2, 5));
        System.out.println("sumRange(0,5) : " + numArray.sumRange(0, 5));
    }
}
