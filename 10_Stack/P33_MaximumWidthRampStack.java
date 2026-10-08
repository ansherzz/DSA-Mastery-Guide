import java.util.*;

/*
Problem: Maximum Width Ramp
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
A ramp is a pair (i, j) with i < j and nums[i] <= nums[j]; its width is j - i.
Return the maximum width of a ramp, or 0 if there is none.

Why this pattern fits:
A useful left end must be smaller than everything before it, so the candidates form a decreasing stack.
Scanning j from the right, once a left end is matched it can never do better, so it is popped for good.

Approach:
Pass 1: push index i when nums[i] is smaller than the value at the stack top.
Pass 2: for j from n - 1 down, while nums[stack top] <= nums[j], record j - top and pop.

Worked Example:
nums = [6, 0, 8, 2, 1, 5] -> the best ramp is (1, 5): 0 <= 5 with width 4.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P33_MaximumWidthRampStack {
    public static int maxWidthRamp(int[] nums) {
        Deque<Integer> candidates = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            if (candidates.isEmpty() || nums[candidates.peek()] > nums[i]) {
                candidates.push(i);
            }
        }
        int best = 0;
        for (int j = nums.length - 1; j >= 0; j--) {
            while (!candidates.isEmpty() && nums[candidates.peek()] <= nums[j]) {
                best = Math.max(best, j - candidates.pop());
            }
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[6, 0, 8, 2, 1, 5] -> " + maxWidthRamp(new int[]{6, 0, 8, 2, 1, 5}));
        System.out.println("[9, 8, 1, 0, 1, 9, 4, 0, 4, 1] -> " + maxWidthRamp(new int[]{9, 8, 1, 0, 1, 9, 4, 0, 4, 1}));
    }
}
