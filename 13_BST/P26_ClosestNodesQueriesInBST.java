import java.util.*;

/*
Problem: Closest Nodes Queries in a Binary Search Tree
Pattern: 13. Binary Search Tree
Difficulty: Medium

Interview Prompt:
For each query value, return [largest tree value <= query, smallest tree value >= query], using -1 when missing.
The tree may be very unbalanced.

Why this pattern fits:
Searching the tree directly costs O(h) per query, which is O(n) on a skewed tree.
The inorder traversal gives a sorted array where binary search guarantees O(log n) regardless of shape.

Approach:
Inorder traversal into a list. For each query find the first index with value >= query.
The ceiling is that element; the floor is that element if equal, otherwise the previous one.

Worked Example:
Keys 1, 2, 4, 6, 9, 13, 14, 15: query 5 -> [4, 6]; query 16 -> [15, -1].

Time Complexity: O(n + q log n)
Space Complexity: O(n)
*/

public class P26_ClosestNodesQueriesInBST {
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

    private static void inorder(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
        inorder(node.left, values);
        values.add(node.val);
        inorder(node.right, values);
    }

    public static List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        List<Integer> values = new ArrayList<>();
        inorder(root, values);
        List<List<Integer>> answer = new ArrayList<>();
        for (int query : queries) {
            int low = 0;
            int high = values.size();
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (values.get(mid) < query) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            int ceiling = low < values.size() ? values.get(low) : -1;
            int floor = ceiling == query ? query : (low > 0 ? values.get(low - 1) : -1);
            answer.add(Arrays.asList(floor, ceiling));
        }
        return answer;
    }

    public static void main(String[] args) {
        TreeNode root = build(6, 2, 13, 1, 4, 9, 15, null, null, null, null, null, null, 14);
        System.out.println("queries [2, 5, 16] -> " + closestNodes(root, Arrays.asList(2, 5, 16)));
        System.out.println("queries [3] -> " + closestNodes(build(4, null, 9), Arrays.asList(3)));
    }
}
