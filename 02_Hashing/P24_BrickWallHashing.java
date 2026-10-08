import java.util.*;

/*
Problem: Brick Wall
Pattern: 02. Hashing
Difficulty: Medium

Interview Prompt:
A wall is built from rows of bricks. Every row has the same total width, but bricks may have different widths.
    You want to draw a vertical line from the top to the bottom that crosses the fewest bricks.
    A line passing along the edge between two bricks does not cross either of them.
    The line may not be drawn along the left or right border of the wall.

    Return the minimum number of bricks the line must cross.

Why this pattern fits:
Crossing few bricks is the same as passing through many brick edges.
    An edge is identified by its distance from the left border, so count how many rows share each edge position.

Approach:
For each row, compute the running width after every brick except the last one.
    Each running width is an edge position; count it in a hash map.
    The best line goes through the most common edge position, so the answer is rows - maxEdgeCount.

Worked Example:
Example:
    wall = [[1,2,2,1], [3,1,2], [1,3,2], [2,4], [3,1,2], [1,3,1,1]]
    Edge position 4 appears in 4 of the 6 rows, which is the most.
    The line at position 4 crosses 6 - 4 = 2 bricks.

Time Complexity: O(total number of bricks)
Space Complexity: O(number of distinct edge positions)
*/

public class P24_BrickWallHashing {
    public static int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> edgeCount = new HashMap<>();
        int mostEdges = 0;

        for (List<Integer> row : wall) {
            int position = 0;
            for (int i = 0; i < row.size() - 1; i++) {
                position += row.get(i);
                int count = edgeCount.merge(position, 1, Integer::sum);
                mostEdges = Math.max(mostEdges, count);
            }
        }
        return wall.size() - mostEdges;
    }

    public static void main(String[] args) {
        List<List<Integer>> wall = Arrays.asList(
            Arrays.asList(1, 2, 2, 1),
            Arrays.asList(3, 1, 2),
            Arrays.asList(1, 3, 2),
            Arrays.asList(2, 4),
            Arrays.asList(3, 1, 2),
            Arrays.asList(1, 3, 1, 1));
        System.out.println("Wall   : " + wall);
        System.out.println("Crossed: " + leastBricks(wall));
        System.out.println();

        List<List<Integer>> solid = Arrays.asList(
            Arrays.asList(1), Arrays.asList(1), Arrays.asList(1));
        System.out.println("Wall   : " + solid);
        System.out.println("Crossed: " + leastBricks(solid));
    }
}
