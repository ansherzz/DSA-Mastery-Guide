import java.util.*;

/*
Problem: Get Equal Substrings Within Budget
Pattern: 04. Sliding Window
Difficulty: Medium

Interview Prompt:
You are given two strings s and t of the same length and an integer maxCost.
    Changing the i-th character of s to the i-th character of t costs |s[i] - t[i]|,
    the absolute difference of their ASCII values.
    Return the maximum length of a substring of s that can be changed to match the corresponding
    substring of t with a total cost of at most maxCost.

Why this pattern fits:
Each index has a fixed non-negative cost, and the task is the longest contiguous block whose total cost
    stays within a budget. Non-negative costs make the window sum monotonic.

Approach:
Expand the window to the right, adding the cost of the new index.
    While the total cost exceeds maxCost, remove the leftmost index's cost and move left forward.
    Record the window length after each step.

Worked Example:
Example:
    s = "abcd", t = "bcdf", maxCost = 3
    Costs per index: 1, 1, 1, 2. The window "abc" costs 3; including 'd' would cost 5. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P37_GetEqualSubstringsWithinBudgetSlidingWindow {
    public static int equalSubstring(String s, String t, int maxCost) {
        int left = 0;
        int cost = 0;
        int best = 0;

        for (int right = 0; right < s.length(); right++) {
            cost += Math.abs(s.charAt(right) - t.charAt(right));
            while (cost > maxCost) {
                cost -= Math.abs(s.charAt(left) - t.charAt(left));
                left++;
            }
            best = Math.max(best, right - left + 1);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(equalSubstring("abcd", "bcdf", 3));
        System.out.println(equalSubstring("abcd", "cdef", 3));
        System.out.println(equalSubstring("abcd", "acde", 0));
    }
}
