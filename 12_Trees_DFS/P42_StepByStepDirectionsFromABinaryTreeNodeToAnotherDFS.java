import java.util.*;

/*
Problem: Step-By-Step Directions From a Binary Tree Node to Another
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Node values are unique. Return the shortest path from the node with value startValue to the node with value
destValue as a string of 'L' (left child), 'R' (right child), and 'U' (parent).

Why this pattern fits:
The shortest path climbs from the start to the lowest common ancestor and then descends to the destination.
The common prefix of the two root paths is exactly the path to that ancestor.

Approach:
DFS to build the L/R path from the root to each target. Skip the shared prefix.
The answer is one 'U' per remaining step of the start path followed by the rest of the destination path.

Worked Example:
[5, 1, 2, 3, null, 6, 4], start 3, destination 6 -> root paths "LL" and "RL" share nothing: "UU" + "RL".

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P42_StepByStepDirectionsFromABinaryTreeNodeToAnotherDFS {
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

    private static boolean findPath(TreeNode node, int target, StringBuilder path) {
        if (node == null) {
            return false;
        }
        if (node.val == target) {
            return true;
        }
        path.append('L');
        if (findPath(node.left, target, path)) {
            return true;
        }
        path.setCharAt(path.length() - 1, 'R');
        if (findPath(node.right, target, path)) {
            return true;
        }
        path.deleteCharAt(path.length() - 1);
        return false;
    }

    public static String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder toStart = new StringBuilder();
        StringBuilder toDest = new StringBuilder();
        findPath(root, startValue, toStart);
        findPath(root, destValue, toDest);
        int common = 0;
        while (common < toStart.length() && common < toDest.length() && toStart.charAt(common) == toDest.charAt(common)) {
            common++;
        }
        return "U".repeat(toStart.length() - common) + toDest.substring(common);
    }

    public static void main(String[] args) {
        System.out.println("3 to 6 -> " + getDirections(build(5, 1, 2, 3, null, 6, 4), 3, 6));
        System.out.println("2 to 1 -> " + getDirections(build(2, 1), 2, 1));
    }
}
