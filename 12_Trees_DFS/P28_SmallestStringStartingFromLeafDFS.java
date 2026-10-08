import java.util.*;

/*
Problem: Smallest String Starting From Leaf
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Each node holds a value 0..25 representing 'a'..'z'.
Return the lexicographically smallest string that starts at a leaf and ends at the root.

Why this pattern fits:
The string is read leaf-to-root, but the tree is explored root-to-leaf.
Keeping the current path in a builder (with backtracking) lets every leaf produce its full string.

Approach:
DFS appending each node's letter. At a leaf, reverse a copy of the path and keep it if it is smaller.
Remove the letter when returning. Comparing whole strings at the leaves avoids the pitfalls of greedy choices.

Worked Example:
[0, 1, 2, 3, 4, 3, 4] -> leaf strings "dba", "eba", "dca", "eca". Answer = "dba".

Time Complexity: O(n * h)
Space Complexity: O(h)
*/

public class P28_SmallestStringStartingFromLeafDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    // Builds a tree from level-order values, using null for a missing child.
    private static TreeNode build(Integer... values) {
        if (values.length == 0 || values[0] == null) {
            return null;
        }
        TreeNode root = new TreeNode(values[0]);
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int i = 1;
        while (i < values.length) {
            TreeNode node = queue.poll();
            if (values[i] != null) {
                node.left = new TreeNode(values[i]);
                queue.add(node.left);
            }
            i++;
            if (i < values.length && values[i] != null) {
                node.right = new TreeNode(values[i]);
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    private static String best;

    private static void dfs(TreeNode node, StringBuilder path) {
        if (node == null) {
            return;
        }
        path.append((char) ('a' + node.val));
        if (node.left == null && node.right == null) {
            String candidate = new StringBuilder(path).reverse().toString();
            if (best == null || candidate.compareTo(best) < 0) {
                best = candidate;
            }
        }
        dfs(node.left, path);
        dfs(node.right, path);
        path.deleteCharAt(path.length() - 1);
    }

    public static String smallestFromLeaf(TreeNode root) {
        best = null;
        dfs(root, new StringBuilder());
        return best;
    }

    public static void main(String[] args) {
        System.out.println("[0, 1, 2, 3, 4, 3, 4] -> " + smallestFromLeaf(build(0, 1, 2, 3, 4, 3, 4)));
        System.out.println("[25, 1, 3, 1, 3, 0, 2] -> " + smallestFromLeaf(build(25, 1, 3, 1, 3, 0, 2)));
        System.out.println("[2, 2, 1, null, 1, 0, null, 0] -> " + smallestFromLeaf(build(2, 2, 1, null, 1, 0, null, 0)));
    }
}
