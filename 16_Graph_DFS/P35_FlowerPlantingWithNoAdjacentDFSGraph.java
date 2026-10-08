import java.util.*;

/*
Problem: Flower Planting With No Adjacent
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
There are n gardens and paths between some of them; each garden has at most 3 paths.
Choose one of 4 flower types for every garden so that gardens joined by a path have different types.

Why this pattern fits:
It is graph coloring with a guarantee: degree at most 3 and 4 colors.
That guarantee turns the usually hard coloring problem into a simple neighbor scan with no backtracking.

Approach:
Build adjacency lists. For each garden in order, mark the colors already used by its neighbors
and assign the smallest unused color.

Worked Example:
Three mutually connected gardens get types 1, 2, 3.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

public class P35_FlowerPlantingWithNoAdjacentDFSGraph {
    public static int[] gardenNoAdj(int n, int[][] paths) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] path : paths) {
            graph.get(path[0] - 1).add(path[1] - 1);
            graph.get(path[1] - 1).add(path[0] - 1);
        }
        int[] flower = new int[n];
        for (int garden = 0; garden < n; garden++) {
            boolean[] used = new boolean[5];
            for (int neighbor : graph.get(garden)) {
                used[flower[neighbor]] = true;
            }
            for (int type = 1; type <= 4; type++) {
                if (!used[type]) {
                    flower[garden] = type;
                    break;
                }
            }
        }
        return flower;
    }

    public static void main(String[] args) {
        System.out.println("triangle -> " + Arrays.toString(gardenNoAdj(3, new int[][]{{1, 2}, {2, 3}, {3, 1}})));
        System.out.println("two pairs -> " + Arrays.toString(gardenNoAdj(4, new int[][]{{1, 2}, {3, 4}})));
        System.out.println("complete graph on 4 -> " + Arrays.toString(
                gardenNoAdj(4, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 1}, {1, 3}, {2, 4}})));
    }
}
