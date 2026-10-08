import java.util.*;

/*
Problem: Find Positive Integer Solution for a Given Equation
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
You are given a hidden function f(x, y) that is strictly increasing in both x and y, and a target z.
Return all positive integer pairs (x, y) with 1 <= x, y <= 1000 and f(x, y) == z.

Why this pattern fits:
For a fixed x, f(x, y) increases with y, so the y that gives z (if any) can be bisected
even though the formula of f is unknown.

Approach:
For x from 1 to 1000, binary search y in [1, 1000] comparing f(x, y) with z.

Worked Example:
f(x, y) = x + y, z = 5 -> [[1, 4], [2, 3], [3, 2], [4, 1]].

Time Complexity: O(1000 * log 1000)
Space Complexity: O(1) extra
*/

public class P34_FindPositiveIntegerSolutionForAGivenEquationBinarySearch {
    interface CustomFunction {
        int f(int x, int y);
    }

    public static List<List<Integer>> findSolution(CustomFunction function, int z) {
        List<List<Integer>> result = new ArrayList<>();
        for (int x = 1; x <= 1000; x++) {
            int low = 1;
            int high = 1000;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                int value = function.f(x, mid);
                if (value == z) {
                    result.add(Arrays.asList(x, mid));
                    break;
                }
                if (value < z) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("x + y = 5 -> " + findSolution((x, y) -> x + y, 5));
        System.out.println("x * y = 5 -> " + findSolution((x, y) -> x * y, 5));
    }
}
