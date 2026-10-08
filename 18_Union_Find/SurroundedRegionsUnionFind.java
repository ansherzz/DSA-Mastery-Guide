import java.util.*;

/*
Problem: Surrounded Regions
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
Given an m x n board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
    A region is captured by flipping all of its 'O' cells into 'X'.
    A region of 'O' cells that touches the border of the board cannot be captured.

Why this pattern fits:
An 'O' survives exactly when it is connected to the border.
    A virtual node representing "the border" turns that into a single connectivity query per cell:
    is this cell in the same set as the virtual node?

Approach:
Create one node per cell plus one extra border node.
    For every 'O' cell: if it lies on the border, union it with the border node.
    Union it with its 'O' neighbors to the right and below (the other two directions are covered by symmetry).
    Finally flip every 'O' whose set differs from the border node's set.

Worked Example:
Example:
    X X X X        X X X X
    X O O X   ->   X X X X
    X X O X        X X X X
    X O X X        X O X X
    The three inner 'O' cells are surrounded. The 'O' in the bottom row touches the border, so it stays.

Time Complexity: O(m * n * alpha(m * n)), effectively linear
Space Complexity: O(m * n)
*/

public class SurroundedRegionsUnionFind {
    static class DSU {
        int[] parent;
        int[] size;
        int components;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            }

            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }

            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            components--;
            return true;
        }
    }

    public static void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        int border = rows * cols;
        DSU dsu = new DSU(rows * cols + 1);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] != 'O') {
                    continue;
                }

                int id = r * cols + c;
                if (r == 0 || c == 0 || r == rows - 1 || c == cols - 1) {
                    dsu.union(id, border);
                }
                if (r + 1 < rows && board[r + 1][c] == 'O') {
                    dsu.union(id, id + cols);
                }
                if (c + 1 < cols && board[r][c + 1] == 'O') {
                    dsu.union(id, id + 1);
                }
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O' && dsu.find(r * cols + c) != dsu.find(border)) {
                    board[r][c] = 'X';
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {
            "XXXX".toCharArray(),
            "XOOX".toCharArray(),
            "XXOX".toCharArray(),
            "XOXX".toCharArray()};

        solve(board);
        for (char[] row : board) {
            System.out.println(new String(row));
        }
    }
}
