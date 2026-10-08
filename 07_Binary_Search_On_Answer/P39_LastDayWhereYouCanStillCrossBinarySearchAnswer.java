import java.util.*;

/*
Problem: Last Day Where You Can Still Cross
Pattern: 07. Binary Search on Answer
Difficulty: Hard

Interview Prompt:
A row x col grid starts as all land. On day i, cells[i - 1] = [r, c] (1-indexed) becomes water.
Return the last day on which it is still possible to walk from the top row to the bottom row on land.

Why this pattern fits:
Flooding only removes land, so once crossing becomes impossible it stays impossible.
The last possible day is the boundary of a monotonic predicate.

Approach:
Search the day in [0, number of cells]. For a candidate, mark the first `day` cells as water
and run a multi-source BFS from every land cell of the top row.

Worked Example:
row = 2, col = 2, cells = [[1, 1], [2, 1], [1, 2], [2, 2]] -> after day 2 the left column is water
but the right column still connects top to bottom; after day 3 it does not. Answer = 2.

Time Complexity: O(row * col * log(row * col))
Space Complexity: O(row * col)
*/

public class P39_LastDayWhereYouCanStillCrossBinarySearchAnswer {
    private static boolean canCross(int row, int col, int[][] cells, int day) {
        boolean[][] blocked = new boolean[row][col];
        for (int i = 0; i < day; i++) {
            blocked[cells[i][0] - 1][cells[i][1] - 1] = true;
        }
        Deque<int[]> queue = new ArrayDeque<>();
        for (int c = 0; c < col; c++) {
            if (!blocked[0][c]) {
                blocked[0][c] = true;
                queue.add(new int[]{0, c});
            }
        }
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            if (cell[0] == row - 1) {
                return true;
            }
            for (int[] d : directions) {
                int r = cell[0] + d[0];
                int c = cell[1] + d[1];
                if (r >= 0 && r < row && c >= 0 && c < col && !blocked[r][c]) {
                    blocked[r][c] = true;
                    queue.add(new int[]{r, c});
                }
            }
        }
        return false;
    }

    public static int latestDayToCross(int row, int col, int[][] cells) {
        int low = 0;
        int high = cells.length;
        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (canCross(row, col, cells, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("2x2 -> " + latestDayToCross(2, 2, new int[][]{{1, 1}, {2, 1}, {1, 2}, {2, 2}}));
        System.out.println("3x3 -> " + latestDayToCross(3, 3,
                new int[][]{{1, 2}, {2, 1}, {3, 3}, {2, 2}, {1, 1}, {1, 3}, {2, 3}, {3, 2}, {3, 1}}));
    }
}
