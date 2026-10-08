import java.util.*;

/*
Problem: 132 Pattern
Pattern: 10. Stack
Difficulty: Medium

Interview Prompt:
Given an integer array nums, a 132 pattern is a subsequence nums[i], nums[j], nums[k] with i < j < k
    such that nums[i] < nums[k] < nums[j].
    Return true if the array contains a 132 pattern.

Why this pattern fits:
Checking all triples is O(n^3). Scanning from the right with a monotonic stack lets you maintain
    the best possible "2" value: the largest number that has some larger number to its left within the scanned part.
    Then any earlier number smaller than that "2" completes the pattern.

Approach:
Scan from right to left. The stack holds candidates for the "3" in decreasing order from bottom to top.
    When the current value is larger than the top, pop those smaller values;
    each popped value could serve as the "2" (it lies to the right of a larger value), so keep the largest one.
    If the current value is smaller than the best "2" found so far, it is a valid "1".

Worked Example:
Example:
    nums = [3, 1, 4, 2]
    Scanning from the right: 2 is pushed. 4 pops 2, so the best "2" becomes 2. Then 1 < 2 completes the pattern
    with the triple (1, 4, 2). Answer = true.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P20_OneThreeTwoPatternStack {
    public static boolean find132pattern(int[] nums) {
        Deque<Integer> stack = new ArrayDeque<>();
        int middle = Integer.MIN_VALUE;

        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < middle) {
                return true;
            }
            while (!stack.isEmpty() && stack.peek() < nums[i]) {
                middle = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Nums : " + Arrays.toString(nums) + " -> 132 pattern? " + find132pattern(nums));
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 4});
        runDemo(new int[]{3, 1, 4, 2});
        runDemo(new int[]{-1, 3, 2, 0});
    }
}
