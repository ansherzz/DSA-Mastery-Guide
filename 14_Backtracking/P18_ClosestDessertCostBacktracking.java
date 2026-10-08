import java.util.*;

/*
Problem: Closest Dessert Cost
Pattern: 14. Backtracking
Difficulty: Medium

Interview Prompt:
A dessert uses exactly one base and any toppings, with at most two of each topping type.
Return the dessert cost closest to the target; if two costs are equally close, return the lower one.

Why this pattern fits:
Each topping has three choices, giving a small ternary decision tree per base.
The objective is "closest to target", so every leaf total is compared against the best so far.

Approach:
For every base cost, dfs(index, total): at the end compare with the best. Otherwise recurse with
0, 1, and 2 copies of topping[index]. Prune when the total already exceeds the target by more than the best gap.

Worked Example:
bases = [2, 3], toppings = [4, 5, 100], target = 18 -> 3 + 4 + 5 + 5 = 17 is the closest.

Time Complexity: O(bases * 3^toppings)
Space Complexity: O(toppings)
*/

public class P18_ClosestDessertCostBacktracking {
    private static int best;

    private static void dfs(int[] toppings, int index, int total, int target) {
        int gap = Math.abs(total - target);
        int bestGap = Math.abs(best - target);
        if (gap < bestGap || (gap == bestGap && total < best)) {
            best = total;
        }
        if (index == toppings.length || total >= target) {
            return;
        }
        for (int copies = 0; copies <= 2; copies++) {
            dfs(toppings, index + 1, total + copies * toppings[index], target);
        }
    }

    public static int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        best = baseCosts[0];
        for (int base : baseCosts) {
            dfs(toppingCosts, 0, base, target);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[1, 7] + [3, 4], target 10 -> " + closestCost(new int[]{1, 7}, new int[]{3, 4}, 10));
        System.out.println("[2, 3] + [4, 5, 100], target 18 -> " + closestCost(new int[]{2, 3}, new int[]{4, 5, 100}, 18));
        System.out.println("[3, 10] + [2, 5], target 9 -> " + closestCost(new int[]{3, 10}, new int[]{2, 5}, 9));
    }
}
