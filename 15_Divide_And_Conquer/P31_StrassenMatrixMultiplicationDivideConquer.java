import java.util.*;

/*
Problem: Strassen Matrix Multiplication
Pattern: 15. Divide & Conquer
Difficulty: Medium

Interview Prompt:
Multiply two n x n matrices (n a power of two) using Strassen's algorithm.

Why this pattern fits:
Block multiplication needs eight half-size products, which is still O(n^3).
Strassen's identities obtain the four result blocks from only seven products, giving O(n^2.81) -
the same "save one multiplication" trick as Karatsuba.

Approach:
Split A into a11..a22 and B into b11..b22. Compute M1..M7 recursively, then
C11 = M1 + M4 - M5 + M7, C12 = M3 + M5, C21 = M2 + M4, C22 = M1 - M2 + M3 + M6.

Worked Example:
[[1, 2], [3, 4]] * [[5, 6], [7, 8]] = [[19, 22], [43, 50]].

Time Complexity: O(n^log2(7)), about O(n^2.81)
Space Complexity: O(n^2)
*/

public class P31_StrassenMatrixMultiplicationDivideConquer {
    private static int[][] add(int[][] a, int[][] b, int sign) {
        int n = a.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = a[i][j] + sign * b[i][j];
            }
        }
        return result;
    }

    private static int[][] block(int[][] m, int row, int col, int size) {
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            System.arraycopy(m[row + i], col, result[i], 0, size);
        }
        return result;
    }

    public static int[][] multiply(int[][] a, int[][] b) {
        int n = a.length;
        if (n == 1) {
            return new int[][]{{a[0][0] * b[0][0]}};
        }
        int h = n / 2;
        int[][] a11 = block(a, 0, 0, h), a12 = block(a, 0, h, h), a21 = block(a, h, 0, h), a22 = block(a, h, h, h);
        int[][] b11 = block(b, 0, 0, h), b12 = block(b, 0, h, h), b21 = block(b, h, 0, h), b22 = block(b, h, h, h);
        int[][] m1 = multiply(add(a11, a22, 1), add(b11, b22, 1));
        int[][] m2 = multiply(add(a21, a22, 1), b11);
        int[][] m3 = multiply(a11, add(b12, b22, -1));
        int[][] m4 = multiply(a22, add(b21, b11, -1));
        int[][] m5 = multiply(add(a11, a12, 1), b22);
        int[][] m6 = multiply(add(a21, a11, -1), add(b11, b12, 1));
        int[][] m7 = multiply(add(a12, a22, -1), add(b21, b22, 1));
        int[][] c11 = add(add(m1, m4, 1), add(m7, m5, -1), 1);
        int[][] c12 = add(m3, m5, 1);
        int[][] c21 = add(m2, m4, 1);
        int[][] c22 = add(add(m1, m2, -1), add(m3, m6, 1), 1);
        int[][] result = new int[n][n];
        for (int i = 0; i < h; i++) {
            System.arraycopy(c11[i], 0, result[i], 0, h);
            System.arraycopy(c12[i], 0, result[i], h, h);
            System.arraycopy(c21[i], 0, result[i + h], 0, h);
            System.arraycopy(c22[i], 0, result[i + h], h, h);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("2x2 product -> " + Arrays.deepToString(
                multiply(new int[][]{{1, 2}, {3, 4}}, new int[][]{{5, 6}, {7, 8}})));
        int[][] a = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        int[][] twiceIdentity = {{2, 0, 0, 0}, {0, 2, 0, 0}, {0, 0, 2, 0}, {0, 0, 0, 2}};
        System.out.println("4x4 times 2I -> " + Arrays.deepToString(multiply(a, twiceIdentity)));
    }
}
