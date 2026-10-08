import java.util.*;

/*
Problem: Last Day Where You Can Still Cross (Union Find)
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
A row x col grid starts as land. On day i, cells[i - 1] = [r, c] (1-indexed) is flooded.
Return the last day on which you can still walk from the top row to the bottom row over land.

Why this pattern fits:
Union find cannot delete, and flooding removes land. Reversed in time, the same process only adds land,
which union find handles perfectly.

Approach:
Start fully flooded with two virtual nodes: TOP and BOTTOM. For day = last down to first: restore that day's cell,
union it with restored neighbors, and with TOP or BOTTOM if it lies on the first or last row.
When TOP and BOTTOM connect while restoring cells[i], crossing is possible on day i (0-indexed), which is the answer.

Worked Example:
row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]] -> restoring backwards, the column on the right is complete
once cell (1, 2) (day index 2) is restored. Answer = 2.

Time Complexity: O(row * col * alpha)
Space Complexity: O(row * col)
*/

public class P38_LastDayWhereYouCanStillCrossUnionFind {
    static class UnionFind {
        final int[] parent;
        final int[] size;
        int components;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            }
            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            components--;
            return true;
        }
    }

    public static int latestDayToCross(int row, int col, int[][] cells) {
        int top = row * col;
        int bottom = row * col + 1;
        UnionFind sets = new UnionFind(row * col + 2);
        boolean[][] land = new boolean[row][col];
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int day = cells.length - 1; day >= 0; day--) {
            int r = cells[day][0] - 1;
            int c = cells[day][1] - 1;
            land[r][c] = true;
            int cell = r * col + c;
            if (r == 0) {
                sets.union(cell, top);
            }
            if (r == row - 1) {
                sets.union(cell, bottom);
            }
            for (int[] d : directions) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (nr >= 0 && nr < row && nc >= 0 && nc < col && land[nr][nc]) {
                    sets.union(cell, nr * col + nc);
                }
            }
            if (sets.find(top) == sets.find(bottom)) {
                return day;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("2x2 -> " + latestDayToCross(2, 2, new int[][]{{1, 1}, {2, 1}, {1, 2}, {2, 2}}));
        System.out.println("3x3 -> " + latestDayToCross(3, 3,
                new int[][]{{1, 2}, {2, 1}, {3, 3}, {2, 2}, {1, 1}, {1, 3}, {2, 3}, {3, 2}, {3, 1}}));
    }
}
