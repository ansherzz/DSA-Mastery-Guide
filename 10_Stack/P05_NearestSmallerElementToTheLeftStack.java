import java.util.*;

/*
Problem: Nearest Smaller Element to the Left
Pattern: 10. Stack
Difficulty: Easy

Interview Prompt:
For each element of an array, find the nearest element to its left that is strictly smaller.
Use -1 when there is none.

Why this pattern fits:
This is the "previous smaller element" template, the mirror image of next greater element
and the building block for histogram and subarray-minimum problems.

Approach:
Scan left to right with a stack of values. Pop everything that is >= the current value
(they can never be the answer for later elements either). The top, if any, is the answer. Push the current value.

Worked Example:
nums = [4, 5, 2, 10, 8] -> 4: none, 5: 4, 2: none, 10: 2, 8: 2. Answer = [-1, 4, -1, 2, 2].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P05_NearestSmallerElementToTheLeftStack {
    public static int[] previousSmaller(int[] nums) {
        int[] result = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && stack.peek() >= nums[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[4, 5, 2, 10, 8] -> " + Arrays.toString(previousSmaller(new int[]{4, 5, 2, 10, 8})));
        System.out.println("[3, 2, 1] -> " + Arrays.toString(previousSmaller(new int[]{3, 2, 1})));
    }
}
