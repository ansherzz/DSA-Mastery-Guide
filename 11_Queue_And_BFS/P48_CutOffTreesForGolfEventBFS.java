import java.util.*;

/*
Problem: Cut Off Trees for Golf Event
Pattern: 11. Queue / BFS
Difficulty: Hard

Interview Prompt:
In a grid, 0 is an obstacle, 1 is walkable ground, and a value greater than 1 is a tree of that height (also walkable).
Starting at (0, 0), cut all trees in increasing height order. Return the minimum number of steps, or -1.

Why this pattern fits:
The visiting order is fixed by the heights, so the problem decomposes into independent shortest paths
between consecutive targets on an unweighted grid - one BFS each.

Approach:
Collect and sort the trees by height. From the current position, BFS to the next tree;
if it is unreachable return -1, otherwise add the distance and continue from there.

Worked Example:
forest = [[1, 2, 3], [0, 0, 4], [7, 6, 5]] -> walking the spiral 2, 3, 4, 5, 6, 7 takes 6 steps.

Time Complexity: O((rows * cols)^2)
Space Complexity: O(rows * cols)
*/

public class P48_CutOffTreesForGolfEventBFS {
    private static int distance(List<List<Integer>> forest, int sr, int sc, int tr, int tc) {
        int rows = forest.size();
        int cols = forest.get(0).size();
        boolean[][] seen = new boolean[rows][cols];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sr, sc});
        seen[sr][sc] = true;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int steps = 0;
        while (!queue.isEmpty()) {
            for (int size = queue.size(); size > 0; size--) {
                int[] cell = queue.poll();
                if (cell[0] == tr && cell[1] == tc) {
                    return steps;
                }
                for (int[] d : directions) {
                    int r = cell[0] + d[0];
                    int c = cell[1] + d[1];
                    if (r >= 0 && r < rows && c >= 0 && c < cols && !seen[r][c] && forest.get(r).get(c) != 0) {
                        seen[r][c] = true;
                        queue.add(new int[]{r, c});
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    public static int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        for (int r = 0; r < forest.size(); r++) {
            for (int c = 0; c < forest.get(0).size(); c++) {
                if (forest.get(r).get(c) > 1) {
                    trees.add(new int[]{forest.get(r).get(c), r, c});
                }
            }
        }
        trees.sort((a, b) -> a[0] - b[0]);
        int total = 0;
        int row = 0;
        int col = 0;
        for (int[] tree : trees) {
            int steps = distance(forest, row, col, tree[1], tree[2]);
            if (steps < 0) {
                return -1;
            }
            total += steps;
            row = tree[1];
            col = tree[2];
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println("[[1,2,3],[0,0,4],[7,6,5]] -> " + cutOffTree(
                Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(0, 0, 4), Arrays.asList(7, 6, 5))));
        System.out.println("[[1,2,3],[0,0,0],[7,6,5]] -> " + cutOffTree(
                Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(0, 0, 0), Arrays.asList(7, 6, 5))));
        System.out.println("[[2,3,4],[0,0,5],[8,7,6]] -> " + cutOffTree(
                Arrays.asList(Arrays.asList(2, 3, 4), Arrays.asList(0, 0, 5), Arrays.asList(8, 7, 6))));
    }
}
