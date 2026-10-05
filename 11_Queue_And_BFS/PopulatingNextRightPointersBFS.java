import java.util.*;

/*
Problem: Populating Next Right Pointers in Each Node II
Pattern: 11. Queue / BFS
Difficulty: Medium

Interview Prompt:
Each node of a binary tree has an extra pointer called next.
    Populate every next pointer so that it points to the node immediately to its right on the same level.
    If there is no such node, next must be null. The tree is not necessarily perfect or complete.

Why this pattern fits:
"The node to my right on the same level" is information that only a level-by-level view has.
    During BFS, the nodes of one level come out of the queue in left-to-right order,
    so each polled node's right neighbor is simply the next node in the queue within the same round.

Approach:
Run a level-order traversal.
    Within each round, link every node except the last one to queue.peek(),
    which is the next node on the same level. Leave the last node's next as null.

Worked Example:
Example:
            1
           / \
          2   3
         / \   \
        4   5   7
    After linking: 1 -> null, 2 -> 3 -> null, 4 -> 5 -> 7 -> null

Time Complexity: O(n)
Space Complexity: O(width of the tree)
*/

public class PopulatingNextRightPointersBFS {
    static class Node {
        int val;
        Node left, right, next;
        Node(int val) { this.val = val; }
    }

    public static Node connect(Node root) {
        if (root == null) {
            return null;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (i < size - 1) {
                    node.next = queue.peek();
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return root;
    }

    private static void printLevels(Node root) {
        Node levelStart = root;
        while (levelStart != null) {
            StringBuilder line = new StringBuilder();
            Node nextLevelStart = null;

            for (Node node = levelStart; node != null; node = node.next) {
                line.append(node.val).append(" -> ");
                if (nextLevelStart == null) {
                    nextLevelStart = node.left != null ? node.left : node.right;
                }
            }
            System.out.println(line + "null");
            levelStart = nextLevelStart;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(7);

        printLevels(connect(root));
    }
}
