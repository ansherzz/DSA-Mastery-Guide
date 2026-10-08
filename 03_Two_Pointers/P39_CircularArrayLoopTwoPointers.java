import java.util.*;

/*
Problem: Circular Array Loop
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
nums is a circular array of non-zero integers; from index i you jump nums[i] steps (forward if positive,
backward if negative). Return true if there is a cycle of length greater than 1 whose jumps all go in the same direction.

Why this pattern fits:
Every index has exactly one outgoing jump, so the array is a functional graph, the same shape as a linked list.
Floyd's fast/slow pointers find a loop in O(1) space.

Approach:
For each unvisited start, advance slow by one jump and fast by two while every index touched has the same sign.
If they meet, the loop is valid unless it is a single self-jump.
Afterwards set every index on that path to 0 so it is never explored again.

Worked Example:
nums = [2, -1, 1, 2, 2] -> 0 -> 2 -> 3 -> 0 is a forward cycle of length 3. Answer = true.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P39_CircularArrayLoopTwoPointers {
    private static int next(int[] nums, int i) {
        int n = nums.length;
        return ((i + nums[i]) % n + n) % n;
    }

    public static boolean circularArrayLoop(int[] nums) {
        for (int start = 0; start < nums.length; start++) {
            if (nums[start] == 0) {
                continue;
            }
            int sign = nums[start];
            int slow = start;
            int fast = next(nums, start);
            while (nums[fast] * sign > 0 && nums[next(nums, fast)] * sign > 0) {
                if (slow == fast) {
                    if (slow == next(nums, slow)) {
                        break;
                    }
                    return true;
                }
                slow = next(nums, slow);
                fast = next(nums, next(nums, fast));
            }
            int index = start;
            while (nums[index] * sign > 0) {
                int following = next(nums, index);
                nums[index] = 0;
                index = following;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println("[2, -1, 1, 2, 2] -> " + circularArrayLoop(new int[]{2, -1, 1, 2, 2}));
        System.out.println("[-1, -2, -3, -4, -5, 6] -> " + circularArrayLoop(new int[]{-1, -2, -3, -4, -5, 6}));
        System.out.println("[1, -1, 5, 1, 4] -> " + circularArrayLoop(new int[]{1, -1, 5, 1, 4}));
    }
}
