import java.util.*;

/*
Problem: Snakes and Ladders
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
You are given an n x n board for the Snakes and Ladders game.
    Starting from square 1, each move rolls a die and advances 1 to 6 squares.
    If a square contains a snake or ladder, you must move to its destination.
    Return the minimum number of moves to reach square n^2, or -1 if impossible.

    This is BFS on board positions.

Why this pattern fits:
Each board square is a state.
    A die roll generates up to 6 next states, so BFS finds the fewest rolls needed.

Approach:
Use BFS from square 1.
    For each current square, try moves current+1 through current+6.
    Convert square numbers to board coordinates and apply snakes or ladders when present.

Worked Example:
Example:
    On the standard sample board, the minimum number of moves is 4.

Time Complexity: O(n^2)
Space Complexity: O(n^2)
*/

public class SnakesAndLaddersBFSGraph {
    private static int[] toPosition(int square, int n) {
        int rowFromBottom = (square - 1) / n;
        int col = (square - 1) % n;
        int row = n - 1 - rowFromBottom;

        if (rowFromBottom % 2 == 1) {
            col = n - 1 - col;
        }

        return new int[]{row, col};
    }

    public static int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n * n + 1];
        queue.offer(1);
        visited[1] = true;

        int moves = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int square = queue.poll();
                if (square == n * n) {
                    return moves;
                }

                for (int next = square + 1; next <= Math.min(square + 6, n * n); next++) {
                    int[] pos = toPosition(next, n);
                    int destination = board[pos[0]][pos[1]] == -1 ? next : board[pos[0]][pos[1]];
                    if (!visited[destination]) {
                        visited[destination] = true;
                        queue.offer(destination);
                    }
                }
            }
            moves++;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[][] board = {
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,35,-1,-1,13,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,15,-1,-1,-1,-1}
        };
        System.out.println(snakesAndLadders(board));
    }
}
