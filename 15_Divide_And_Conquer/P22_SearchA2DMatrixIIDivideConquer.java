import java.util.*;

/*
Problem: Search a 2D Matrix II
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Write an efficient algorithm that searches for a value target in an m x n integer matrix.
    Integers in each row are sorted in ascending order from left to right,
    and integers in each column are sorted in ascending order from top to bottom.

Why this pattern fits:
Comparing the target with one well-chosen element eliminates a whole rectangular block at once.
    What remains is a set of smaller rectangles with the same sorted structure, which are solved recursively.

Approach:
Search a rectangle given by its top-left and bottom-right corners.
    Take the middle column and walk down it until reaching the first element greater than the target (row r).
    If the target was seen on the way, return true.
    Everything above row r and left of the middle column is smaller than the target,
    and everything from row r down and right of the middle column is larger.
    So only two rectangles can still contain the target: bottom-left (rows r.., columns left of mid)
    and top-right (rows ..r-1, columns right of mid). Recurse into both.
    Note: the staircase walk from the top-right corner solves the same problem in O(m + n) without recursion.

Worked Example:
Example:
    matrix = [[1, 4, 7, 11, 15], [2, 5, 8, 12, 19], [3, 6, 9, 16, 22], [10, 13, 14, 17, 24], [18, 21, 23, 26, 30]]
    target = 5: middle column is [7, 8, 9, 14, 23]. The first element above 5 is at row 0,
    so search the bottom-left block (all rows, columns 0..1), where 5 is found.

Time Complexity: O(n^1.585) in the worst case for an n x n matrix
Space Complexity: O(log n) recursion depth
*/

public class P22_SearchA2DMatrixIIDivideConquer {
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        return search(matrix, target, 0, 0, matrix.length - 1, matrix[0].length - 1);
    }

    private static boolean search(int[][] matrix, int target, int top, int left, int bottom, int right) {
        if (top > bottom || left > right) {
            return false;
        }
        if (target < matrix[top][left] || target > matrix[bottom][right]) {
            return false;
        }

        int mid = left + (right - left) / 2;
        int row = top;
        while (row <= bottom && matrix[row][mid] <= target) {
            if (matrix[row][mid] == target) {
                return true;
            }
            row++;
        }

        return search(matrix, target, row, left, bottom, mid - 1)
            || search(matrix, target, top, mid + 1, row - 1, right);
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}};

        System.out.println(searchMatrix(matrix, 5));
        System.out.println(searchMatrix(matrix, 20));
        System.out.println(searchMatrix(matrix, 30));
    }
}
