import java.util.*;

/*
Problem: All Nodes Distance K in Binary Tree
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
Given the root of a binary tree, a target node in the tree, and an integer k,
    return the values of all nodes that are exactly k edges away from the target.
    The answer may be returned in any order.

Why this pattern fits:
Distance from a node in a tree can go downward to descendants or upward through the parent,
    but tree nodes only have child pointers. Recording each node's parent turns the tree into an undirected graph,
    and then "all nodes at distance k" is simply the kth layer of a BFS from the target.

Approach:
Traverse the tree once to fill a map from each node to its parent.
    Run BFS from the target, treating left child, right child, and parent as neighbors,
    with a visited set so the search never walks back.
    After k layers, the nodes in the queue are the answer.

Worked Example:
Example:
              3
             / \
            5   1
           / \ / \
          6  2 0  8
            / \
           7   4
    target = 5, k = 2
    Going down: 7 and 4. Going up through 3 and down the other side: 1.
    Answer = [7, 4, 1]

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P16_AllNodesDistanceKInBinaryTreeBFSGraph {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        recordParents(root, null, parent);

        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(target);
        visited.add(target);

        for (int distance = 0; distance < k && !queue.isEmpty(); distance++) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                for (TreeNode next : new TreeNode[]{node.left, node.right, parent.get(node)}) {
                    if (next != null && visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (TreeNode node : queue) {
            result.add(node.val);
        }
        return result;
    }

    private static void recordParents(TreeNode node, TreeNode from, Map<TreeNode, TreeNode> parent) {
        if (node == null) {
            return;
        }
        parent.put(node, from);
        recordParents(node.left, node, parent);
        recordParents(node.right, node, parent);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode five = new TreeNode(5);
        root.left = five;
        root.right = new TreeNode(1);
        five.left = new TreeNode(6);
        five.right = new TreeNode(2);
        five.right.left = new TreeNode(7);
        five.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        System.out.println("Distance 2 from node 5: " + distanceK(root, five, 2));
        System.out.println("Distance 3 from node 5: " + distanceK(root, five, 3));
        System.out.println("Distance 0 from node 5: " + distanceK(root, five, 0));
    }
}
