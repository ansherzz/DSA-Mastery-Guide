import java.util.*;

/*
Problem: N-ary Tree Level Order Traversal
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Given the root of an n-ary tree, where each node can have any number of children,
    return the level order traversal of its nodes' values, one list per level from top to bottom.

Why this pattern fits:
Level order is the definition of BFS. The only change from the binary tree version is that
    a node contributes a list of children instead of a left and a right child.

Approach:
Start the queue with the root. At the beginning of each round, the queue holds exactly one level.
    Record its size, poll that many nodes into a list, and offer all of their children.
    Each round produces one level of the answer.

Worked Example:
Example:
              1
           /  |  \
          3   2   4
         / \
        5   6
    Levels: [1], [3, 2, 4], [5, 6].

Time Complexity: O(n)
Space Complexity: O(w), where w is the maximum width of the tree
*/

public class P35_NAryTreeLevelOrderTraversalBFS {
    static class Node {
        int val;
        List<Node> children = new ArrayList<>();
        Node(int val) { this.val = val; }

        Node add(Node... nodes) {
            children.addAll(Arrays.asList(nodes));
            return this;
        }
    }

    public static List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> levels = new ArrayList<>();
        if (root == null) {
            return levels;
        }

        Deque<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int size = queue.size(); size > 0; size--) {
                Node node = queue.poll();
                level.add(node.val);
                for (Node child : node.children) {
                    queue.offer(child);
                }
            }
            levels.add(level);
        }
        return levels;
    }

    public static void main(String[] args) {
        Node three = new Node(3).add(new Node(5), new Node(6));
        Node root = new Node(1).add(three, new Node(2), new Node(4));

        System.out.println(levelOrder(root));
        System.out.println(levelOrder(null));
    }
}
