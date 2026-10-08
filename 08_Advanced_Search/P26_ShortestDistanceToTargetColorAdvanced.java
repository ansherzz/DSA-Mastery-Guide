import java.util.*;

/*
Problem: Shortest Distance to Target Color
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
colors[i] is 1, 2, or 3. For each query [i, c], return the shortest distance from index i
to any index with color c, or -1 if that color does not appear.

Why this pattern fits:
The positions of one color form a sorted list. The nearest position to i is next to where i would be inserted.

Approach:
Build one index list per color. For a query, binary search the first position >= i;
the answer is the smaller distance to that position or to the one before it.

Worked Example:
colors = [1, 1, 2, 1, 3, 2, 2, 3, 3], query [1, 3] -> the nearest 3 is at index 4, distance 3.

Time Complexity: O(n + q log n)
Space Complexity: O(n)
*/

public class P26_ShortestDistanceToTargetColorAdvanced {
    public static List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        List<List<Integer>> positions = new ArrayList<>();
        for (int c = 0; c <= 3; c++) {
            positions.add(new ArrayList<>());
        }
        for (int i = 0; i < colors.length; i++) {
            positions.get(colors[i]).add(i);
        }
        List<Integer> answer = new ArrayList<>();
        for (int[] query : queries) {
            List<Integer> list = positions.get(query[1]);
            if (list.isEmpty()) {
                answer.add(-1);
                continue;
            }
            int low = 0;
            int high = list.size();
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (list.get(mid) < query[0]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            int best = Integer.MAX_VALUE;
            if (low < list.size()) {
                best = list.get(low) - query[0];
            }
            if (low > 0) {
                best = Math.min(best, query[0] - list.get(low - 1));
            }
            answer.add(best);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("[1, 1, 2, 1, 3, 2, 2, 3, 3] -> "
                + shortestDistanceColor(new int[]{1, 1, 2, 1, 3, 2, 2, 3, 3}, new int[][]{{1, 3}, {2, 2}, {6, 1}}));
        System.out.println("[1, 2] -> " + shortestDistanceColor(new int[]{1, 2}, new int[][]{{0, 3}}));
    }
}
