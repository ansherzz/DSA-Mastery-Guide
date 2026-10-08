import java.util.*;

/*
Problem: Find the Most Competitive Subsequence
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Return the lexicographically smallest subsequence of nums of size k.

Why this pattern fits:
To make a sequence small, a smaller value should replace earlier larger ones whenever possible.
The only limit is that enough elements must remain to fill k positions - a monotonic stack with a removal budget.

Approach:
For each value: while the stack top is larger and (stack size - 1 + elements remaining) >= k, pop.
Push the value if the stack has fewer than k elements.

Worked Example:
nums = [2, 4, 3, 3, 5, 4, 9, 6], k = 4 -> 4 is replaced by 3, later 5 by 4. Answer = [2, 3, 3, 4].

Time Complexity: O(n)
Space Complexity: O(k)
*/

public class P26_FindTheMostCompetitiveSubsequenceStack {
    public static int[] mostCompetitive(int[] nums, int k) {
        int[] stack = new int[k];
        int size = 0;
        for (int i = 0; i < nums.length; i++) {
            int remaining = nums.length - i;
            while (size > 0 && stack[size - 1] > nums[i] && size - 1 + remaining >= k) {
                size--;
            }
            if (size < k) {
                stack[size++] = nums[i];
            }
        }
        return stack;
    }

    public static void main(String[] args) {
        System.out.println("[3, 5, 2, 6], k = 2 -> " + Arrays.toString(mostCompetitive(new int[]{3, 5, 2, 6}, 2)));
        System.out.println("[2, 4, 3, 3, 5, 4, 9, 6], k = 4 -> "
                + Arrays.toString(mostCompetitive(new int[]{2, 4, 3, 3, 5, 4, 9, 6}, 4)));
    }
}
