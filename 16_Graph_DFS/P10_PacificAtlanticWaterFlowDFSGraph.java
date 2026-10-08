import java.util.*;

/*
Problem: Pacific Atlantic Water Flow
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
You are given a matrix of heights. Water can flow from a cell to a neighboring cell of equal or lower height.
    The Pacific touches the top and left edges. The Atlantic touches the bottom and right edges.
    Return all coordinates from which water can reach both oceans.

    The trick is to reverse the flow direction and DFS from the oceans inward.

Why this pattern fits:
Instead of asking 'where can this cell flow?', ask 'which cells can reach this ocean if water were allowed
    to move uphill or flat in reverse?' Doing that from both oceans gives the intersection.

Approach:
Run DFS from all Pacific-border cells and mark reachable cells.
    Run DFS from all Atlantic-border cells and mark reachable cells.
    Cells marked in both sets belong to the answer.

Worked Example:
Example:
    For the standard 5x5 sample grid, positions like [0,4], [1,3], and [3,0] can reach both oceans.

Time Complexity: O(rows * cols)
Space Complexity: O(rows * cols)
*/

public class P10_PacificAtlanticWaterFlowDFSGraph {
    private static final int[][] DIRECTIONS = {{1,0}, {-1,0}, {0,1}, {0,-1}};

    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length;
        int cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols];
        boolean[][] atlantic = new boolean[rows][cols];

        for (int r = 0; r < rows; r++) {
            dfs(heights, r, 0, pacific);
            dfs(heights, r, cols - 1, atlantic);
        }
        for (int c = 0; c < cols; c++) {
            dfs(heights, 0, c, pacific);
            dfs(heights, rows - 1, c, atlantic);
        }

        List<List<Integer>> answer = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    answer.add(Arrays.asList(r, c));
                }
            }
        }

        return answer;
    }

    private static void dfs(int[][] heights, int r, int c, boolean[][] visited) {
        if (visited[r][c]) {
            return;
        }

        visited[r][c] = true;
        for (int[] dir : DIRECTIONS) {
            int nr = r + dir[0];
            int nc = c + dir[1];
            if (nr < 0 || nr >= heights.length || nc < 0 || nc >= heights[0].length) {
                continue;
            }
            if (heights[nr][nc] < heights[r][c]) {
                continue;
            }
            dfs(heights, nr, nc, visited);
        }
    }

    public static void main(String[] args) {
        int[][] heights = {
            {1,2,2,3,5},
            {3,2,3,4,4},
            {2,4,5,3,1},
            {6,7,1,4,5},
            {5,1,1,2,4}
        };
        System.out.println(pacificAtlantic(heights));
    }
}
