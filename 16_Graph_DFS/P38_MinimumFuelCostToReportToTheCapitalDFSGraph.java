import java.util.*;

/*
Problem: Minimum Fuel Cost to Report to the Capital
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
Cities form a tree with the capital at city 0. One representative from every city must travel to the capital.
Each city has a car with `seats` seats, and driving one car along one road costs one liter of fuel.
Return the minimum total fuel.

Why this pattern fits:
Sharing cars is always best, so the number of cars on a road depends only on how many people must cross it:
the size of the subtree hanging below that road.

Approach:
dfs(node, parent) returns the number of representatives in the subtree.
For every node except the capital, add ceil(subtreeSize / seats) to the fuel for the road to its parent.

Worked Example:
roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2 -> total fuel 7.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P38_MinimumFuelCostToReportToTheCapitalDFSGraph {
    private static long fuel;

    private static int dfs(List<List<Integer>> graph, int node, int parent, int seats) {
        int people = 1;
        for (int neighbor : graph.get(node)) {
            if (neighbor != parent) {
                people += dfs(graph, neighbor, node, seats);
            }
        }
        if (node != 0) {
            fuel += (people + seats - 1) / seats;
        }
        return people;
    }

    public static long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] road : roads) {
            graph.get(road[0]).add(road[1]);
            graph.get(road[1]).add(road[0]);
        }
        fuel = 0;
        dfs(graph, 0, -1, seats);
        return fuel;
    }

    public static void main(String[] args) {
        System.out.println("star, seats 5 -> " + minimumFuelCost(new int[][]{{0, 1}, {0, 2}, {0, 3}}, 5));
        System.out.println("seven cities, seats 2 -> "
                + minimumFuelCost(new int[][]{{3, 1}, {3, 2}, {1, 0}, {0, 4}, {0, 5}, {4, 6}}, 2));
        System.out.println("only the capital -> " + minimumFuelCost(new int[][]{}, 1));
    }
}
