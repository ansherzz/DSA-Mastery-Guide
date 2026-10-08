import java.util.*;

/*
Problem: The K Weakest Rows in a Matrix
Pattern: 06. Binary Search - Classic Variations
Difficulty: Easy

Interview Prompt:
Each row of a binary matrix has all its 1's (soldiers) before its 0's (civilians).
A row is weaker than another if it has fewer soldiers, or the same number and a smaller index.
Return the indices of the k weakest rows from weakest to strongest.

Why this pattern fits:
Each row is sorted (ones then zeros), so the soldier count is the position of the first 0,
found by binary search instead of a linear scan.

Approach:
For each row find the first index holding 0. Sort row indices by (count, index) and take the first k.

Worked Example:
Rows have 2, 4, 1, 2, 5 soldiers -> weakest order is row 2, row 0, row 3. Answer for k = 3: [2, 0, 3].

Time Complexity: O(m log n + m log m)
Space Complexity: O(m)
*/

public class P10_TheKWeakestRowsInAMatrixBinarySearch {
    private static int countSoldiers(int[] row) {
        int low = 0;
        int high = row.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (row[mid] == 1) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    public static int[] kWeakestRows(int[][] mat, int k) {
        int[] strength = new int[mat.length];
        Integer[] order = new Integer[mat.length];
        for (int i = 0; i < mat.length; i++) {
            strength[i] = countSoldiers(mat[i]);
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> strength[a] != strength[b] ? strength[a] - strength[b] : a - b);
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = order[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] mat = {{1, 1, 0, 0, 0}, {1, 1, 1, 1, 0}, {1, 0, 0, 0, 0}, {1, 1, 0, 0, 0}, {1, 1, 1, 1, 1}};
        System.out.println("k = 3 -> " + Arrays.toString(kWeakestRows(mat, 3)));
    }
}
