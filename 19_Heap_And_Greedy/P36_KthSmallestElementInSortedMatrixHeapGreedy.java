import java.util.*;

/*
Problem: Kth Smallest Element in a Sorted Matrix (Heap)
Pattern: 19. Heap & Greedy
Difficulty: Medium

Interview Prompt:
Every row and every column of an n x n matrix is sorted in ascending order.
Return the k-th smallest element of the matrix.

Why this pattern fits:
The rows are n sorted lists. Merging them in order with a heap produces the elements from smallest to largest,
and the k-th one produced is the answer - no need to sort all n^2 values.

Approach:
Push the first element of each row (only the first min(n, k) rows are needed) with its coordinates.
Pop k - 1 times, each time pushing the next element of the same row. The top is then the answer.

Worked Example:
matrix = [[1, 5, 9], [10, 11, 13], [12, 13, 15]], k = 8 -> in order: 1, 5, 9, 10, 11, 12, 13, 13. Answer = 13.

Time Complexity: O(k log n)
Space Complexity: O(n)
*/

public class P36_KthSmallestElementInSortedMatrixHeapGreedy {
    public static int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int r = 0; r < Math.min(n, k); r++) {
            heap.add(new int[]{matrix[r][0], r, 0});
        }
        for (int i = 1; i < k; i++) {
            int[] smallest = heap.poll();
            int r = smallest[1];
            int c = smallest[2];
            if (c + 1 < n) {
                heap.add(new int[]{matrix[r][c + 1], r, c + 1});
            }
        }
        return heap.peek()[0];
    }

    public static void main(String[] args) {
        System.out.println("3x3, k = 8 -> " + kthSmallest(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8));
        System.out.println("[[-5]], k = 1 -> " + kthSmallest(new int[][]{{-5}}, 1));
    }
}
