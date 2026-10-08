import java.util.*;

/*
Problem: Wood Cutting - Maximum Saw Height
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
A saw blade set at height H cuts every tree taller than H down to H, and the cut-off parts are collected.
Given the tree heights and the amount of wood needed, return the maximum H that still yields at least that much wood.

Why this pattern fits:
Raising the blade never increases the wood collected, so feasibility is monotonic in H.

Approach:
Search H in [0, tallest tree]. For a candidate, sum max(0, height - H) over all trees.
If the total is at least the requirement, H is feasible and we try higher.

Worked Example:
heights = [20, 15, 10, 17], need = 7 -> at H = 15 we collect 5 + 2 = 7. At H = 16 only 5. Answer = 15.

Time Complexity: O(n log maxHeight)
Space Complexity: O(1)
*/

public class P09_WoodCuttingMaximumSawHeightBinarySearchAnswer {
    public static int maxSawHeight(int[] heights, long need) {
        int low = 0;
        int high = Arrays.stream(heights).max().getAsInt();
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            long wood = 0;
            for (int h : heights) {
                wood += Math.max(0, h - mid);
            }
            if (wood >= need) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("[20, 15, 10, 17], need 7 -> " + maxSawHeight(new int[]{20, 15, 10, 17}, 7));
        System.out.println("[4, 42, 40, 26, 46], need 20 -> " + maxSawHeight(new int[]{4, 42, 40, 26, 46}, 20));
    }
}
