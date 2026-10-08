import java.util.*;

/*
Problem: Water and Jug Problem
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
You are given two jugs with capacities x liters and y liters. There is an infinite water supply.
    Determine whether the total amount of water in both jugs can reach exactly target liters using these operations:
    fill either jug completely, empty either jug completely,
    or pour water from one jug into the other until the receiver is full or the giver is empty.

Why this pattern fits:
A state is the pair (water in jug 1, water in jug 2), and each operation is an edge to another state.
    Asking whether a target total is reachable is a search over this implicit graph.
    BFS explores it systematically, and a visited set prevents endless cycles of filling and emptying.

Approach:
Start from (0, 0). For each state (a, b), generate the six next states:
    fill jug 1, fill jug 2, empty jug 1, empty jug 2, pour 1 -> 2, and pour 2 -> 1.
    A pour moves min(amount in giver, free space in receiver).
    Return true when a + b equals the target. States are encoded as a single long for the visited set.
    Math note: the answer is true exactly when target <= x + y and target is a multiple of gcd(x, y).

Worked Example:
Example:
    x = 3, y = 5, target = 4
    Fill 5 -> (0, 5). Pour into 3 -> (3, 2). Empty 3 -> (0, 2). Pour -> (2, 0). Fill 5 -> (2, 5).
    Pour -> (3, 4). Empty 3 -> (0, 4). The total is 4.

Time Complexity: O(x * y) states in the worst case
Space Complexity: O(x * y)
*/

public class WaterAndJugProblemBFSGraph {
    public static boolean canMeasureWater(int x, int y, int target) {
        if (target > x + y) {
            return false;
        }

        Set<Long> visited = new HashSet<>();
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        visited.add(0L);

        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int a = state[0];
            int b = state[1];
            if (a + b == target) {
                return true;
            }

            int pourAToB = Math.min(a, y - b);
            int pourBToA = Math.min(b, x - a);
            int[][] nextStates = {
                {x, b}, {a, y}, {0, b}, {a, 0},
                {a - pourAToB, b + pourAToB},
                {a + pourBToA, b - pourBToA}};

            for (int[] next : nextStates) {
                long key = (long) next[0] * (y + 1) + next[1];
                if (visited.add(key)) {
                    queue.offer(next);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canMeasureWater(3, 5, 4));
        System.out.println(canMeasureWater(2, 6, 5));
        System.out.println(canMeasureWater(1, 2, 3));
    }
}
