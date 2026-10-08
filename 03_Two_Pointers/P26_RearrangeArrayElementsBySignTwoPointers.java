import java.util.*;

/*
Problem: Rearrange Array Elements by Sign
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
nums has an equal number of positive and negative integers.
Rearrange it so signs alternate starting with a positive, while keeping the relative order within each sign.

Why this pattern fits:
Each value's destination is known from its sign alone, so two write pointers place everything in one pass.

Approach:
Create the result array. posIndex starts at 0 and negIndex at 1.
Write each positive at posIndex and each negative at negIndex, advancing the used pointer by 2.

Worked Example:
nums = [3, 1, -2, -5, 2, -4] -> [3, -2, 1, -5, 2, -4].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P26_RearrangeArrayElementsBySignTwoPointers {
    public static int[] rearrangeArray(int[] nums) {
        int[] result = new int[nums.length];
        int posIndex = 0;
        int negIndex = 1;
        for (int value : nums) {
            if (value > 0) {
                result[posIndex] = value;
                posIndex += 2;
            } else {
                result[negIndex] = value;
                negIndex += 2;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[3, 1, -2, -5, 2, -4] -> " + Arrays.toString(rearrangeArray(new int[]{3, 1, -2, -5, 2, -4})));
        System.out.println("[-1, 1] -> " + Arrays.toString(rearrangeArray(new int[]{-1, 1})));
    }
}
