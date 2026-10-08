import java.util.*;

/*
Problem: Sliding Puzzle
Pattern: 11. Queue / BFS
Difficulty: Hard

Interview Prompt:
A 2 x 3 board holds five tiles labeled 1 to 5 and one empty square represented by 0.
    A move swaps the 0 with a 4-directionally adjacent tile.
    The puzzle is solved when the board is [[1, 2, 3], [4, 5, 0]].

    Given a starting board, return the least number of moves needed to solve it, or -1 if it cannot be solved.

Why this pattern fits:
Each arrangement of the board is a state, and a move connects two states.
    Finding the fewest moves is a shortest path over the state graph, and every move has the same cost,
    so BFS over states gives the answer.

Approach:
Encode the board as a 6-character string, read row by row.
    Precompute, for every position of the 0, which positions it can swap with.
    Run BFS from the start string. For each state, generate neighbors by swapping the 0 with each allowed position.
    Use a set to avoid revisiting states. Return the level at which "123450" is reached.

Worked Example:
Example:
    board = [[1, 2, 3], [4, 0, 5]]  -> state "123405"
    Swapping the 0 with the 5 gives "123450". Answer = 1.

    board = [[1, 2, 3], [5, 4, 0]] can never be solved, so the answer is -1.

Time Complexity: O(6!) states in the worst case, each with at most 3 neighbors
Space Complexity: O(6!)
*/

public class P46_SlidingPuzzleBFS {
    private static final int[][] NEIGHBORS = {
        {1, 3}, {0, 2, 4}, {1, 5},
        {0, 4}, {1, 3, 5}, {2, 4}
    };

    public static int slidingPuzzle(int[][] board) {
        StringBuilder startBuilder = new StringBuilder();
        for (int[] row : board) {
            for (int tile : row) {
                startBuilder.append(tile);
            }
        }

        String target = "123450";
        String start = startBuilder.toString();
        if (start.equals(target)) {
            return 0;
        }

        Set<String> seen = new HashSet<>();
        Queue<String> queue = new ArrayDeque<>();
        queue.offer(start);
        seen.add(start);
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            moves++;

            for (int i = 0; i < size; i++) {
                String state = queue.poll();
                int zero = state.indexOf('0');

                for (int swapWith : NEIGHBORS[zero]) {
                    char[] chars = state.toCharArray();
                    chars[zero] = chars[swapWith];
                    chars[swapWith] = '0';
                    String next = new String(chars);

                    if (next.equals(target)) {
                        return moves;
                    }
                    if (seen.add(next)) {
                        queue.offer(next);
                    }
                }
            }
        }
        return -1;
    }

    private static void runDemo(int[][] board) {
        System.out.println("Board : " + Arrays.deepToString(board));
        System.out.println("Moves : " + slidingPuzzle(board));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{1, 2, 3}, {4, 0, 5}});
        runDemo(new int[][]{{1, 2, 3}, {5, 4, 0}});
        runDemo(new int[][]{{4, 1, 2}, {5, 0, 3}});
    }
}
