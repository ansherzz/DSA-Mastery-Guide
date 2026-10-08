import java.util.*;

/*
Problem: Sort Array By Parity
Pattern: 03. Two Pointers
Difficulty: Easy

Interview Prompt:
Given an integer array nums, move all the even integers to the beginning of the array
    followed by all the odd integers. Return any array that satisfies this condition.

Why this pattern fits:
This is a two-way partition. Pointers starting at opposite ends can each skip elements
    that are already on the correct side and swap the pair that is not.

Approach:
Keep left at the start and right at the end.
    If nums[left] is even, it is already in place, so move left forward.
    If nums[right] is odd, it is already in place, so move right backward.
    Otherwise nums[left] is odd and nums[right] is even: swap them and move both pointers.

Worked Example:
Example:
    nums = [3, 1, 2, 4]
    Swap 3 and 4 -> [4, 1, 2, 3]. Swap 1 and 2 -> [4, 2, 1, 3].

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P16_SortArrayByParityTwoPointers {
    public static int[] sortArrayByParity(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            if (nums[left] % 2 == 0) {
                left++;
            } else if (nums[right] % 2 == 1) {
                right--;
            } else {
                int temp = nums[left];
                nums[left++] = nums[right];
                nums[right--] = temp;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortArrayByParity(new int[]{3, 1, 2, 4})));
        System.out.println(Arrays.toString(sortArrayByParity(new int[]{0})));
        System.out.println(Arrays.toString(sortArrayByParity(new int[]{1, 3, 5, 2})));
    }
}
