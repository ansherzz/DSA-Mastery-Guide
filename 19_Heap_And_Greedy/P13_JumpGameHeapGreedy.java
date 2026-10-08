import java.util.*;

/*
Problem: Jump Game
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
You are given an integer array nums and start at the first index.
    Each element is the maximum jump length from that position.
    Return true if you can reach the last index.

Why this pattern fits:
You do not need to decide which jump to take. It is enough to know the farthest index reachable so far.
    If you can reach index i, you can also reach every index before it,
    so a single number summarizes all possible paths. That is a greedy invariant.

Approach:
Scan left to right while tracking farthest, the largest index reachable using the positions seen so far.
    If the current index is beyond farthest, it cannot be reached and the answer is false.
    Otherwise update farthest with i + nums[i]. If the scan completes, the last index is reachable.

Worked Example:
Example:
    nums = [2, 3, 1, 1, 4]
    farthest becomes 2, then 4 at index 1, which already covers the last index. Answer = true.

    nums = [3, 2, 1, 0, 4]
    farthest stays at 3 through indices 0..3, and index 4 is beyond it. Answer = false.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P13_JumpGameHeapGreedy {
    public static boolean canJump(int[] nums) {
        int farthest = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > farthest) {
                return false;
            }
            farthest = Math.max(farthest, i + nums[i]);
        }
        return true;
    }

    private static void runDemo(int[] nums) {
        System.out.println(Arrays.toString(nums) + " -> " + canJump(nums));
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 3, 1, 1, 4});
        runDemo(new int[]{3, 2, 1, 0, 4});
        runDemo(new int[]{0});
    }
}
