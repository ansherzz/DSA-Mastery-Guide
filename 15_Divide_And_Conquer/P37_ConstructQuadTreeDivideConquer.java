import java.util.*;

/*
Problem: Construct Quad Tree
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Given an n x n matrix grid of 0s and 1s, where n is a power of two, represent the grid with a quad tree.
    Each node of a quad tree has two attributes:
    - val: true if the node represents a region of 1s, false for a region of 0s.
    - isLeaf: true if the node has no children.
    If a region contains only one value, it is a leaf. Otherwise it is an internal node with four children
    covering the top-left, top-right, bottom-left, and bottom-right quadrants. Return the root of the quad tree.

Why this pattern fits:
The data structure is itself defined by division: a region is either uniform or it is four sub-regions.
    Building it bottom-up means solving the four quadrants first and then deciding whether they can be merged.

Approach:
build(row, col, size) returns the node for the square whose top-left corner is (row, col).
    A square of size 1 is a leaf holding the cell's value.
    Otherwise build the four quadrants of half the size.
    If all four are leaves with the same value, merge them into a single leaf.
    If not, return an internal node pointing to the four children.
    Merging after recursion avoids rescanning each region to test whether it is uniform.

Worked Example:
Example:
    grid = [[1, 1, 0, 0],
            [1, 1, 0, 0],
            [0, 0, 1, 0],
            [0, 0, 0, 0]]
    Top-left is a leaf of 1s, top-right and bottom-left are leaves of 0s,
    and bottom-right is an internal node with leaves 1, 0, 0, 0.

Time Complexity: O(n^2)
Space Complexity: O(log n) recursion depth besides the tree
*/

public class P37_ConstructQuadTreeDivideConquer {
    static class Node {
        boolean val;
        boolean isLeaf;
        Node topLeft, topRight, bottomLeft, bottomRight;

        Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
        }
    }

    public static Node construct(int[][] grid) {
        return build(grid, 0, 0, grid.length);
    }

    private static Node build(int[][] grid, int row, int col, int size) {
        if (size == 1) {
            return new Node(grid[row][col] == 1, true);
        }

        int half = size / 2;
        Node topLeft = build(grid, row, col, half);
        Node topRight = build(grid, row, col + half, half);
        Node bottomLeft = build(grid, row + half, col, half);
        Node bottomRight = build(grid, row + half, col + half, half);

        boolean allLeaves = topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf;
        if (allLeaves && topLeft.val == topRight.val && topLeft.val == bottomLeft.val
            && topLeft.val == bottomRight.val) {
            return new Node(topLeft.val, true);
        }

        Node node = new Node(false, false);
        node.topLeft = topLeft;
        node.topRight = topRight;
        node.bottomLeft = bottomLeft;
        node.bottomRight = bottomRight;
        return node;
    }

    private static String describe(Node node) {
        if (node.isLeaf) {
            return node.val ? "1" : "0";
        }
        return "(" + describe(node.topLeft) + " " + describe(node.topRight) + " "
            + describe(node.bottomLeft) + " " + describe(node.bottomRight) + ")";
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 0, 0}, {1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 0}};
        System.out.println(describe(construct(grid)));
        System.out.println(describe(construct(new int[][]{{0, 1}, {1, 0}})));
        System.out.println(describe(construct(new int[][]{{1, 1}, {1, 1}})));
    }
}
