import java.util.*;

/*
Problem: Second Largest Element
Pattern: 01. Array & String Foundations - Brute Force Thinking
Difficulty: Easy

Interview Prompt:
Given an integer array, return the second largest distinct value.
If there is no second distinct value, return -1.

Why this pattern fits:
The brute force idea is to sort and walk back from the end.
A single scan that remembers the best two values gives the same answer without sorting.

Approach:
Keep `first` and `second`.
A value greater than `first` pushes the old first down to second.
A value strictly between them replaces `second`. Equal values are ignored so duplicates do not count.

Worked Example:
nums = [12, 35, 1, 10, 34, 1]
first becomes 35 and second ends as 34. Answer = 34.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P05_SecondLargestElementArrayBasics {
    public static int secondLargest(int[] nums) {
        long first = Long.MIN_VALUE;
        long second = Long.MIN_VALUE;
        for (int value : nums) {
            if (value > first) {
                second = first;
                first = value;
            } else if (value < first && value > second) {
                second = value;
            }
        }
        return second == Long.MIN_VALUE ? -1 : (int) second;
    }

    public static void main(String[] args) {
        System.out.println("[12, 35, 1, 10, 34, 1] -> " + secondLargest(new int[]{12, 35, 1, 10, 34, 1}));
        System.out.println("[10, 10, 10] -> " + secondLargest(new int[]{10, 10, 10}));
        System.out.println("[5, 9] -> " + secondLargest(new int[]{5, 9}));
    }
}
