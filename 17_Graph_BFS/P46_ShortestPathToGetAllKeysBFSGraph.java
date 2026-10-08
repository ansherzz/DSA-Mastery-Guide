import java.util.*;

/*
Problem: Shortest Path to Get All Keys
Pattern: 17. Graph BFS
Difficulty: Hard

Interview Prompt:
You are given an m x n grid where '.' is an empty cell, '#' is a wall, '@' is the starting point,
    lowercase letters represent keys, and uppercase letters represent locks.
    You start at the starting point and one move consists of walking one space in one of the four cardinal directions.
    You cannot walk outside the grid or into a wall. If you walk over a key, you pick it up,
    and you cannot walk over a lock unless you have its corresponding key.
    There are at most 6 keys, using the first letters of the alphabet.
    Return the lowest number of moves to acquire all keys, or -1 if it is impossible.

Why this pattern fits:
Each move costs 1, so BFS gives the minimum. But a cell does not describe the situation by itself:
    standing on the same cell with different keys opens different doors.
    The BFS state is (row, column, set of keys held), with the key set stored as a bitmask.

Approach:
Find the start and count the keys to build the target mask with all key bits set.
    Run a level-order BFS over states with visited[row][col][mask].
    For a move into a cell: walls are blocked; a lock is blocked unless its bit is in the mask;
    a key sets its bit in the new mask.
    The first time a state's mask equals the target mask, the current number of moves is the answer.

Worked Example:
Example:
    grid = ["@.a..",
            "###.#",
            "b.A.B"]
    Walk right to 'a' (2 moves), then right, down, down to row 2 (3 moves), then left through lock 'A' to 'b' (3 moves).
    Answer = 8.

Time Complexity: O(m * n * 2^k), where k is the number of keys
Space Complexity: O(m * n * 2^k)
*/

public class P46_ShortestPathToGetAllKeysBFSGraph {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public static int shortestPathAllKeys(String[] grid) {
        int rows = grid.length;
        int cols = grid[0].length();
        int startRow = 0;
        int startCol = 0;
        int allKeys = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                char cell = grid[r].charAt(c);
                if (cell == '@') {
                    startRow = r;
                    startCol = c;
                } else if (cell >= 'a' && cell <= 'f') {
                    allKeys |= 1 << (cell - 'a');
                }
            }
        }

        boolean[][][] visited = new boolean[rows][cols][allKeys + 1];
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startRow, startCol, 0});
        visited[startRow][startCol][0] = true;

        for (int moves = 0; !queue.isEmpty(); moves++) {
            for (int size = queue.size(); size > 0; size--) {
                int[] state = queue.poll();
                if (state[2] == allKeys) {
                    return moves;
                }

                for (int[] direction : DIRECTIONS) {
                    int nr = state[0] + direction[0];
                    int nc = state[1] + direction[1];
                    if (nr < 0 || nc < 0 || nr >= rows || nc >= cols) {
                        continue;
                    }

                    char cell = grid[nr].charAt(nc);
                    int keys = state[2];
                    if (cell == '#') {
                        continue;
                    }
                    if (cell >= 'A' && cell <= 'F' && (keys & (1 << (cell - 'A'))) == 0) {
                        continue;
                    }
                    if (cell >= 'a' && cell <= 'f') {
                        keys |= 1 << (cell - 'a');
                    }

                    if (!visited[nr][nc][keys]) {
                        visited[nr][nc][keys] = true;
                        queue.offer(new int[]{nr, nc, keys});
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(shortestPathAllKeys(new String[]{"@.a..", "###.#", "b.A.B"}));
        System.out.println(shortestPathAllKeys(new String[]{"@..aA", "..B#.", "....b"}));
        System.out.println(shortestPathAllKeys(new String[]{"@Aa"}));
    }
}
