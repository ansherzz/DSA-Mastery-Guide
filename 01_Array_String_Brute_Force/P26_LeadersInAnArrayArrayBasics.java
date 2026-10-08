import java.util.*;

/*
Problem: Leaders in an Array
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
An element is a leader if it is strictly greater than every element to its right.
The last element is always a leader. Return all leaders in their original order.

Why this pattern fits:
Brute force compares each element with everything to its right, O(n^2).
The "maximum of everything to my right" can be carried along a single right-to-left scan.

Approach:
Walk from the end keeping `maxRight`.
If the current value is greater than `maxRight`, it is a leader and becomes the new maximum.
Reverse the collected leaders to restore the original order.

Worked Example:
nums = [16, 17, 4, 3, 5, 2]
From the right: 2 is a leader, 5 > 2, 3 and 4 are not, 17 > 5, 16 is not. Answer = [17, 5, 2].

Time Complexity: O(n)
Space Complexity: O(1) extra
*/

public class P26_LeadersInAnArrayArrayBasics {
    public static List<Integer> leaders(int[] nums) {
        List<Integer> result = new ArrayList<>();
        int maxRight = Integer.MIN_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > maxRight) {
                result.add(nums[i]);
                maxRight = nums[i];
            }
        }
        Collections.reverse(result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("[16, 17, 4, 3, 5, 2] -> " + leaders(new int[]{16, 17, 4, 3, 5, 2}));
        System.out.println("[3, 2, 1] -> " + leaders(new int[]{3, 2, 1}));
    }
}
