import java.util.*;

/*
Problem: Find the Duplicate Number
Pattern: 03. Two Pointers
Difficulty: Medium

Interview Prompt:
Given an array of n + 1 integers where every value is in the range [1, n],
    exactly one value is repeated (possibly more than twice). Return that repeated value.

    You must not modify the array and must use only constant extra space.

Why this pattern fits:
Treat each index as a node and nums[i] as a pointer to the next node.
    Because two indices point to the same value, the walk starting from index 0 enters a cycle,
    and the cycle entrance is the duplicate. Slow and fast pointers find that entrance.

Approach:
Phase 1: move slow one step and fast two steps until they meet inside the cycle.
    Phase 2: restart one pointer from index 0 and move both one step at a time.
    The node where they meet again is the start of the cycle, which is the duplicated value.

Worked Example:
Example:
    nums = [1, 3, 4, 2, 2]
    Walk: 0 -> 1 -> 3 -> 2 -> 4 -> 2 -> 4 -> ...
    The cycle is 2 -> 4 -> 2, and it is entered at 2. Answer = 2.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P38_FindTheDuplicateNumberTwoPointers {
    public static int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    private static void runDemo(int[] nums) {
        System.out.println("Array    : " + Arrays.toString(nums));
        System.out.println("Duplicate: " + findDuplicate(nums));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 3, 4, 2, 2});
        runDemo(new int[]{3, 1, 3, 4, 2});
        runDemo(new int[]{2, 2, 2, 2, 2});
    }
}
