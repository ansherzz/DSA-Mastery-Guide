import java.util.*;

/*
Problem: Unique Binary Search Trees
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
Given an integer n, return the number of structurally unique binary search trees
    that store exactly the values 1 through n.

Why this pattern fits:
The BST property splits the problem cleanly. If value r is the root, the r - 1 smaller values must form
    the left subtree and the n - r larger values must form the right subtree.
    The count depends only on how many values each side has, not on which values they are.

Approach:
Let count[k] be the number of unique BSTs with k nodes. count[0] = 1 for the empty tree.
    For each size k, try every possible root position: a root with `left` nodes on its left has k - 1 - left on its right,
    contributing count[left] * count[k - 1 - left] trees. Sum over all choices.
    These are the Catalan numbers.

Worked Example:
Example:
    n = 3
    Root 1: 0 nodes left, 2 nodes right -> 1 * 2 = 2 trees
    Root 2: 1 node left, 1 node right  -> 1 * 1 = 1 tree
    Root 3: 2 nodes left, 0 nodes right -> 2 * 1 = 2 trees
    Total = 5.

Time Complexity: O(n^2)
Space Complexity: O(n)
*/

public class UniqueBinarySearchTreesBST {
    public static int numTrees(int n) {
        int[] count = new int[n + 1];
        count[0] = 1;

        for (int size = 1; size <= n; size++) {
            for (int left = 0; left < size; left++) {
                count[size] += count[left] * count[size - 1 - left];
            }
        }
        return count[n];
    }

    public static void main(String[] args) {
        for (int n : new int[]{1, 2, 3, 4, 5, 10}) {
            System.out.println("n = " + n + " -> " + numTrees(n) + " unique BSTs");
        }
    }
}
