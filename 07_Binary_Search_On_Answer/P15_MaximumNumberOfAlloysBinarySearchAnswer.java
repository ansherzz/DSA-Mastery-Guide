import java.util.*;

/*
Problem: Maximum Number of Alloys
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
There are n metal types and k machines. Machine i needs composition[i][j] units of metal j per alloy.
You own stock[j] units of metal j and can buy more at cost[j] each, within a total budget.
All alloys must be made by the same machine. Return the maximum number of alloys.

Why this pattern fits:
For a fixed machine, the money needed grows with the number of alloys, so the largest affordable count can be bisected.

Approach:
For each machine, binary search the count x. The cost of x alloys is the sum over metals of
max(0, x * need - stock) * price. Keep the best count over all machines.

Worked Example:
composition = [[1, 1, 1], [1, 1, 10]], stock = [0, 0, 0], cost = [1, 2, 3], budget = 15
Machine 0 spends 6 per alloy, so it makes 2. Answer = 2.

Time Complexity: O(k * n * log(maxAnswer))
Space Complexity: O(1)
*/

public class P15_MaximumNumberOfAlloysBinarySearchAnswer {
    public static int maxNumberOfAlloys(int budget, int[][] composition, int[] stock, int[] cost) {
        int best = 0;
        for (int[] machine : composition) {
            long low = 0;
            long high = 2_000_000_000L;
            while (low < high) {
                long mid = low + (high - low + 1) / 2;
                long spend = 0;
                for (int j = 0; j < stock.length && spend <= budget; j++) {
                    spend += Math.max(0, mid * machine[j] - stock[j]) * cost[j];
                }
                if (spend <= budget) {
                    low = mid;
                } else {
                    high = mid - 1;
                }
            }
            best = (int) Math.max(best, low);
        }
        return best;
    }

    public static void main(String[] args) {
        int[][] composition = {{1, 1, 1}, {1, 1, 10}};
        System.out.println("stock [0, 0, 0] -> " + maxNumberOfAlloys(15, composition, new int[]{0, 0, 0}, new int[]{1, 2, 3}));
        System.out.println("stock [0, 0, 100] -> " + maxNumberOfAlloys(15, composition, new int[]{0, 0, 100}, new int[]{1, 2, 3}));
    }
}
