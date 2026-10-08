import java.util.*;

/*
Problem: Amount of Time for Binary Tree to Be Infected
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
At minute 0 an infection starts at the node with value `start` (values are unique).
Each minute, every uninfected node adjacent to an infected node becomes infected.
Return the number of minutes until the whole tree is infected.

Why this pattern fits:
Infection spreads to children and to the parent, but tree nodes have no parent pointer.
Building an adjacency map turns the tree into a plain graph where BFS levels are minutes.

Approach:
Traverse the tree recording value -> neighbors (parent and children).
BFS from `start`, counting the levels after the first one.

Worked Example:
[1, 5, 3, null, 4, 10, 6, 9, 2], start = 3 -> minute 1: 1, 10, 6; minute 2: 5; minute 3: 4; minute 4: 9, 2.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P41_AmountOfTimeForBinaryTreeToBeInfectedBFS {
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

    private static void link(Map<Integer, List<Integer>> graph, TreeNode node, TreeNode parent) {
        if (node == null) {
            return;
        }
        graph.computeIfAbsent(node.val, k -> new ArrayList<>());
        if (parent != null) {
            graph.get(node.val).add(parent.val);
            graph.get(parent.val).add(node.val);
        }
        link(graph, node.left, node);
        link(graph, node.right, node);
    }

    public static int amountOfTime(TreeNode root, int start) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        link(graph, root, null);
        Set<Integer> infected = new HashSet<>();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        infected.add(start);
        int minutes = -1;
        while (!queue.isEmpty()) {
            minutes++;
            for (int size = queue.size(); size > 0; size--) {
                for (int neighbor : graph.get(queue.poll())) {
                    if (infected.add(neighbor)) {
                        queue.add(neighbor);
                    }
                }
            }
        }
        return minutes;
    }

    public static void main(String[] args) {
        System.out.println("start = 3 -> " + amountOfTime(build(1, 5, 3, null, 4, 10, 6, 9, 2), 3));
        System.out.println("single node -> " + amountOfTime(build(1), 1));
    }
}
