import java.util.*;

/*
Problem: Clone Graph using BFS
Pattern: 17. Graph BFS
Difficulty: Medium

Interview Prompt:
Given a reference to a node in a connected undirected graph, return a deep copy of the graph.
    Each node contains a value and a list of its neighbors.
    The copy must consist entirely of new node objects with the same structure.

    The DFS version is in folder 16. Solve it here with BFS.

Why this pattern fits:
Copying a graph means visiting every node once and recreating every edge.
    BFS provides the visiting order, and a map from original node to its copy serves two purposes:
    it is the visited set, and it lets edges be wired between the correct copies.

Approach:
Create the copy of the starting node and put the original in a queue.
    For each original taken from the queue, look at its neighbors.
    If a neighbor has no copy yet, create one, store it in the map, and enqueue the neighbor.
    In every case, add the neighbor's copy to the current copy's neighbor list.

Worked Example:
Example:
    1 -- 2
    |    |
    4 -- 3
    The clone has four new nodes with the same adjacency: 1: [2, 4], 2: [1, 3], 3: [2, 4], 4: [1, 3].

Time Complexity: O(V + E)
Space Complexity: O(V)
*/

public class CloneGraphBFSGraph {
    static class Node {
        int val;
        List<Node> neighbors;
        Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }
    }

    public static Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Map<Node, Node> copies = new HashMap<>();
        copies.put(node, new Node(node.val));

        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node original = queue.poll();
            Node copy = copies.get(original);

            for (Node neighbor : original.neighbors) {
                if (!copies.containsKey(neighbor)) {
                    copies.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
                copy.neighbors.add(copies.get(neighbor));
            }
        }
        return copies.get(node);
    }

    private static String describe(Node start) {
        Map<Integer, List<Integer>> adjacency = new TreeMap<>();
        Set<Node> seen = new HashSet<>();
        Deque<Node> stack = new ArrayDeque<>();
        stack.push(start);
        seen.add(start);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            List<Integer> values = new ArrayList<>();
            for (Node neighbor : node.neighbors) {
                values.add(neighbor.val);
                if (seen.add(neighbor)) {
                    stack.push(neighbor);
                }
            }
            adjacency.put(node.val, values);
        }
        return adjacency.toString();
    }

    public static void main(String[] args) {
        Node one = new Node(1);
        Node two = new Node(2);
        Node three = new Node(3);
        Node four = new Node(4);
        one.neighbors.addAll(Arrays.asList(two, four));
        two.neighbors.addAll(Arrays.asList(one, three));
        three.neighbors.addAll(Arrays.asList(two, four));
        four.neighbors.addAll(Arrays.asList(one, three));

        Node clone = cloneGraph(one);
        System.out.println("Original adjacency: " + describe(one));
        System.out.println("Clone adjacency   : " + describe(clone));
        System.out.println("Clone is a different object? " + (clone != one));
        System.out.println("Clone's neighbor is a different object? " + (clone.neighbors.get(0) != two));
    }
}
