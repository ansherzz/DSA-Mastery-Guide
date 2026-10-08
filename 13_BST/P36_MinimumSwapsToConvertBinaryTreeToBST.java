import java.util.*;

/*
Problem: Minimum Swaps to Convert a Binary Tree into a BST
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
A complete binary tree with distinct values is stored as an array (children of i are 2i + 1 and 2i + 2).
Return the minimum number of value swaps needed to turn it into a BST with the same shape.

Why this pattern fits:
A tree is a BST exactly when its inorder traversal is sorted. Since the shape is fixed,
the task is "minimum swaps to sort the inorder sequence", which is n minus the number of permutation cycles.

Approach:
Compute the inorder sequence of the array tree. Sort the indices by value to get each element's target position.
Walk the permutation cycles: a cycle of length L needs L - 1 swaps.

Worked Example:
[5, 6, 7, 8, 9, 10, 11] -> inorder is 8, 6, 9, 5, 10, 7, 11: one 2-cycle and one 3-cycle need 1 + 2 = 3 swaps.

Time Complexity: O(n log n)
Space Complexity: O(n)
*/

public class P36_MinimumSwapsToConvertBinaryTreeToBST {
    private static void inorder(int[] tree, int index, List<Integer> values) {
        if (index >= tree.length) {
            return;
        }
        inorder(tree, 2 * index + 1, values);
        values.add(tree[index]);
        inorder(tree, 2 * index + 2, values);
    }

    public static int minSwaps(int[] tree) {
        List<Integer> values = new ArrayList<>();
        inorder(tree, 0, values);
        int n = values.size();
        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> values.get(a) - values.get(b));
        boolean[] visited = new boolean[n];
        int swaps = 0;
        for (int i = 0; i < n; i++) {
            int length = 0;
            for (int j = i; !visited[j]; j = order[j]) {
                visited[j] = true;
                length++;
            }
            swaps += Math.max(0, length - 1);
        }
        return swaps;
    }

    public static void main(String[] args) {
        System.out.println("[5, 6, 7, 8, 9, 10, 11] -> " + minSwaps(new int[]{5, 6, 7, 8, 9, 10, 11}));
        System.out.println("[1, 2, 3] -> " + minSwaps(new int[]{1, 2, 3}));
    }
}
