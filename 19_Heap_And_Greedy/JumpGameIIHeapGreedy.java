import java.util.*;

/*
Problem: Jump Game II
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
You are given an integer array nums and start at the first index.
    Each element is the maximum jump length from that position.
    Return the minimum number of jumps needed to reach the last index. It is guaranteed that the last index is reachable.

Why this pattern fits:
Think of the indices reachable with exactly j jumps as a contiguous range.
    From everything in the current range, the farthest reach defines the next range.
    Moving range by range is a greedy version of BFS that needs no queue.

Approach:
Track currentEnd, the last index reachable with the jumps taken so far,
    and farthest, the best reach found while scanning the current range.
    When the scan arrives at currentEnd, one more jump is required: increment the count and set currentEnd to farthest.
    Stop before the last index, because no jump is needed once you are there.

Worked Example:
Example:
    nums = [2, 3, 1, 1, 4]
    Range after 0 jumps: index 0. From it the farthest reach is index 2.
    Range after 1 jump: indices 1..2. From them the farthest reach is index 4 (from index 1).
    Range after 2 jumps includes the last index. Answer = 2.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class JumpGameIIHeapGreedy {
    public static int jump(int[] nums) {
        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        return jumps;
    }

    private static void runDemo(int[] nums) {
        System.out.println(Arrays.toString(nums) + " -> " + jump(nums) + " jumps");
    }

    public static void main(String[] args) {
        runDemo(new int[]{2, 3, 1, 1, 4});
        runDemo(new int[]{2, 3, 0, 1, 4});
        runDemo(new int[]{0});
    }
}
