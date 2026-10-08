import java.util.*;

/*
Problem: The Skyline Problem
Pattern: 15. Divide & Conquer
Difficulty: Hard

Interview Prompt:
A city's skyline is the outer contour formed by all its buildings when viewed from a distance.
    Each building is given as [left, right, height], and all buildings stand on flat ground at height 0.

    Return the skyline as a list of key points [x, height], sorted by x.
    Each key point is the left end of a horizontal segment of the skyline.
    The last point always has height 0, and no two consecutive points may have the same height.

Why this pattern fits:
The skyline of one building is trivial: it rises at the left edge and drops to 0 at the right edge.
    Two skylines can be merged in linear time, exactly like merging two sorted lists,
    which gives the same recurrence as merge sort.

Approach:
Recursively compute the skyline of the left half and the right half of the buildings.
    Merge them by walking both lists in order of x while remembering the current height of each skyline.
    At each x, the merged height is the larger of the two current heights.
    Emit a key point only when the merged height changes.

Worked Example:
Example:
    buildings = [[2, 9, 10], [3, 7, 15], [5, 12, 12], [15, 20, 10], [19, 24, 8]]
    skyline   = [[2, 10], [3, 15], [7, 12], [12, 0], [15, 10], [20, 8], [24, 0]]

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P28_TheSkylineProblemDivideConquer {
    public static List<int[]> getSkyline(int[][] buildings) {
        if (buildings.length == 0) {
            return new ArrayList<>();
        }
        return solve(buildings, 0, buildings.length - 1);
    }

    private static List<int[]> solve(int[][] buildings, int left, int right) {
        if (left == right) {
            List<int[]> single = new ArrayList<>();
            single.add(new int[]{buildings[left][0], buildings[left][2]});
            single.add(new int[]{buildings[left][1], 0});
            return single;
        }

        int mid = left + (right - left) / 2;
        return merge(solve(buildings, left, mid), solve(buildings, mid + 1, right));
    }

    private static List<int[]> merge(List<int[]> first, List<int[]> second) {
        List<int[]> merged = new ArrayList<>();
        int i = 0;
        int j = 0;
        int firstHeight = 0;
        int secondHeight = 0;

        while (i < first.size() || j < second.size()) {
            int x;
            if (j == second.size() || (i < first.size() && first.get(i)[0] < second.get(j)[0])) {
                x = first.get(i)[0];
                firstHeight = first.get(i++)[1];
            } else if (i == first.size() || second.get(j)[0] < first.get(i)[0]) {
                x = second.get(j)[0];
                secondHeight = second.get(j++)[1];
            } else {
                x = first.get(i)[0];
                firstHeight = first.get(i++)[1];
                secondHeight = second.get(j++)[1];
            }

            int height = Math.max(firstHeight, secondHeight);
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] != height) {
                merged.add(new int[]{x, height});
            }
        }
        return merged;
    }

    private static void runDemo(int[][] buildings) {
        StringBuilder sb = new StringBuilder();
        for (int[] point : getSkyline(buildings)) {
            sb.append(Arrays.toString(point)).append(' ');
        }
        System.out.println("Buildings: " + Arrays.deepToString(buildings));
        System.out.println("Skyline  : " + sb.toString().trim());
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[][]{{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}});
        runDemo(new int[][]{{0, 2, 3}, {2, 5, 3}});
    }
}
