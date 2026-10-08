import java.util.*;

/*
Problem: Two City Scheduling
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
costs[i] = [aCost, bCost] is the cost of flying person i to city A or city B.
Exactly half of the 2n people must go to each city. Return the minimum total cost.

Why this pattern fits:
Imagine sending everyone to B, then moving n people to A. Moving person i changes the cost by aCost - bCost,
so the n people with the smallest (most negative) difference should be the ones moved.

Approach:
Sort by aCost - bCost. The first n people fly to A and the remaining n to B.

Worked Example:
costs = [[10, 20], [30, 200], [400, 50], [30, 20]] -> A gets persons 0 and 1, B gets persons 2 and 3: 10 + 30 + 50 + 20 = 110.

Time Complexity: O(n log n)
Space Complexity: O(1) extra
*/

public class P10_TwoCitySchedulingHeapGreedy {
    public static int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (a, b) -> (a[0] - a[1]) - (b[0] - b[1]));
        int half = costs.length / 2;
        int total = 0;
        for (int i = 0; i < costs.length; i++) {
            total += i < half ? costs[i][0] : costs[i][1];
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("4 people -> " + twoCitySchedCost(new int[][]{{10, 20}, {30, 200}, {400, 50}, {30, 20}}));
        System.out.println("6 people -> " + twoCitySchedCost(
                new int[][]{{259, 770}, {448, 54}, {926, 667}, {184, 139}, {840, 118}, {577, 469}}));
    }
}
