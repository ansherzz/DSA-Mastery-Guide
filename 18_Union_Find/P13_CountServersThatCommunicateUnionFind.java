import java.util.*;

/*
Problem: Count Servers that Communicate
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
grid[i][j] = 1 means there is a server at that cell. Two servers communicate if they share a row or a column.
Return the number of servers that can communicate with at least one other server.

Why this pattern fits:
Comparing every pair of servers is quadratic. Treating each row and each column as a node
turns a server into an edge between its row and its column, so sharing either one lands servers in the same set.

Approach:
Nodes 0..rows-1 are rows and rows..rows+cols-1 are columns. For each server union(row, rows + col).
Count servers per root; every server whose root holds at least two servers is counted.

Worked Example:
grid = [[1, 0], [1, 1]] -> all three servers are linked through column 0 and row 1. Answer = 3.

Time Complexity: O(rows * cols * alpha)
Space Complexity: O(rows + cols)
*/

public class P13_CountServersThatCommunicateUnionFind {
    static class UnionFind {
        final int[] parent;
        final int[] size;
        int components;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
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

    public static int countServers(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        UnionFind sets = new UnionFind(rows + cols);
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    sets.union(r, rows + c);
                }
            }
        }
        int[] serversInGroup = new int[rows + cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    serversInGroup[sets.find(r)]++;
                }
            }
        }
        int count = 0;
        for (int servers : serversInGroup) {
            if (servers >= 2) {
                count += servers;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("[[1, 0], [0, 1]] -> " + countServers(new int[][]{{1, 0}, {0, 1}}));
        System.out.println("[[1, 0], [1, 1]] -> " + countServers(new int[][]{{1, 0}, {1, 1}}));
        System.out.println("4x4 grid -> " + countServers(new int[][]{{1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}}));
    }
}
