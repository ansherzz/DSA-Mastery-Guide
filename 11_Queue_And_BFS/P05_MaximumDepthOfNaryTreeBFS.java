import java.util.*;

/*
Problem: Maximum Depth of N-ary Tree
Pattern: 11. Queue / BFS
Difficulty: Easy

Interview Prompt:
Given the root of an N-ary tree, return its maximum depth
(the number of nodes on the longest path from the root down to a leaf).

Why this pattern fits:
BFS visits nodes level by level, so the number of rounds the queue survives is the depth.
With many children per node, the only change is looping over a child list.

Approach:
Start with the root in a queue. For each round, remove exactly the nodes currently in the queue
and add all their children. Count the rounds.

Worked Example:
Root 1 has children 3, 2, 4 and node 3 has children 5, 6 -> three levels. Answer = 3.

Time Complexity: O(n)
Space Complexity: O(width of the tree)
*/

public class P05_MaximumDepthOfNaryTreeBFS {
    static class Node {
        int val;
        List<Node> children = new ArrayList<>();
        Node(int val) { this.val = val; }
    }

    public static int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            depth++;
            for (int size = queue.size(); size > 0; size--) {
                queue.addAll(queue.poll().children);
            }
        }
        return depth;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        Node three = new Node(3);
        root.children.addAll(Arrays.asList(three, new Node(2), new Node(4)));
        three.children.addAll(Arrays.asList(new Node(5), new Node(6)));
        System.out.println("three levels -> " + maxDepth(root));
        System.out.println("single node -> " + maxDepth(new Node(7)));
        System.out.println("empty tree -> " + maxDepth(null));
    }
}
