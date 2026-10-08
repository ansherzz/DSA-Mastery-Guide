import java.util.*;

/*
Problem: Sort Array By Parity II
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Half of the integers in nums are even and half are odd.
Rearrange the array so that nums[i] is even when i is even and odd when i is odd. Any valid answer works.

Why this pattern fits:
Every misplaced value at an even index has a matching misplaced value at an odd index.
Two independent pointers, one per index parity, find and swap such pairs in place.

Approach:
Scan even indices with i. When nums[i] is odd, advance j over odd indices until nums[j] is even, then swap.

Worked Example:
nums = [4, 2, 5, 7]
Index 2 holds 5 (odd). The odd-index pointer finds 2 at index 1. Swap -> [4, 5, 2, 7].

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P18_SortArrayByParityIITwoPointers {
    public static int[] sortArrayByParityII(int[] nums) {
        int j = 1;
        for (int i = 0; i < nums.length; i += 2) {
            if (nums[i] % 2 != 0) {
                while (nums[j] % 2 != 0) {
                    j += 2;
                }
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println("[4, 2, 5, 7] -> " + Arrays.toString(sortArrayByParityII(new int[]{4, 2, 5, 7})));
        System.out.println("[2, 3] -> " + Arrays.toString(sortArrayByParityII(new int[]{2, 3})));
    }
}
