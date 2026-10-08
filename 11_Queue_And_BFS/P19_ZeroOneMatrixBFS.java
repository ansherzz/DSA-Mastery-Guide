import java.util.*;

/*
Problem: 01 Matrix
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Given a binary matrix, return a matrix where each cell contains the distance to the nearest 0.
    Distances are measured using up, down, left, and right moves.

    The best way is multi-source BFS starting from every 0.

Why this pattern fits:
If you start BFS from every 1 separately, the work explodes.
    Starting from all zero cells at once lets the minimum distance propagate outward in layers.

Approach:
Push every zero cell into the queue first and mark all one cells as unprocessed.
    Then run BFS to fill distances layer by layer.

Worked Example:
Example:
    mat = [[0,0,0],[0,1,0],[1,1,1]]
    The answer is [[0,0,0],[0,1,0],[1,2,1]].

Time Complexity: O(rows * cols)
Space Complexity: O(rows * cols)
*/

public class P19_ZeroOneMatrixBFS {
    private static final int[][] DIRECTIONS = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;
        Queue<int[]> queue = new ArrayDeque<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 0) {
                    queue.offer(new int[]{r, c});
                } else {
                    mat[r][c] = -1;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] dir : DIRECTIONS) {
                int nr = current[0] + dir[0];
                int nc = current[1] + dir[1];

                if (nr < 0 || nr >= rows || nc < 0 || nc >= cols || mat[nr][nc] != -1) {
                    continue;
                }

                mat[nr][nc] = mat[current[0]][current[1]] + 1;
                queue.offer(new int[]{nr, nc});
            }
        }

        return mat;
    }

    private static void runDemo(int[][] mat) {
        int[][] answer = updateMatrix(mat);
        for (int[] row : answer) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{0,0,0},{0,1,0},{1,1,1}});
    }
}
