import java.util.*;

/*
Problem: Detonate the Maximum Bombs
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
You are given a list of bombs. bombs[i] = [x, y, r] is a bomb at position (x, y) whose blast range is a circle of radius r.
    You may choose to detonate a single bomb. When a bomb is detonated, it detonates all bombs
    that lie within its range, and those bombs detonate the bombs within their ranges, and so on.
    Return the maximum number of bombs that can be detonated by detonating only one bomb.

Why this pattern fits:
The chain reaction is reachability in a directed graph: an edge i -> j exists when bomb j is inside bomb i's range.
    The edges are directed because a big bomb can reach a small one without the reverse being true,
    so union-find does not apply, and a traversal from each bomb is needed.

Approach:
Build the directed graph by checking every ordered pair with squared distances (long arithmetic, no square roots).
    Run a BFS from each bomb and count how many bombs it reaches.
    Return the largest count.

Worked Example:
Example:
    bombs = [[2, 1, 3], [6, 1, 4]]
    The distance between them is 4. Bomb 1 (radius 4) reaches bomb 0, but bomb 0 (radius 3) does not reach bomb 1.
    Detonating bomb 1 detonates both. Answer = 2.

Time Complexity: O(n^3) in the worst case: n traversals over up to n^2 edges
Space Complexity: O(n^2)
*/

public class DetonateTheMaximumBombsBFSGraph {
    public static int maximumDetonation(int[][] bombs) {
        int n = bombs.length;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                long dx = bombs[i][0] - bombs[j][0];
                long dy = bombs[i][1] - bombs[j][1];
                long radius = bombs[i][2];
                if (dx * dx + dy * dy <= radius * radius) {
                    graph.get(i).add(j);
                }
            }
        }

        int best = 0;
        for (int start = 0; start < n; start++) {
            boolean[] visited = new boolean[n];
            Deque<Integer> queue = new ArrayDeque<>();
            queue.offer(start);
            visited[start] = true;
            int count = 0;

            while (!queue.isEmpty()) {
                int bomb = queue.poll();
                count++;
                for (int next : graph.get(bomb)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            best = Math.max(best, count);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(maximumDetonation(new int[][]{{2, 1, 3}, {6, 1, 4}}));
        System.out.println(maximumDetonation(new int[][]{{1, 1, 5}, {10, 10, 5}}));
        System.out.println(maximumDetonation(
            new int[][]{{1, 2, 3}, {2, 3, 1}, {3, 4, 2}, {4, 5, 3}, {5, 6, 4}}));
    }
}
