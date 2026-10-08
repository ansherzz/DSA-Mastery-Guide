import java.util.*;

/*
Problem: Clone Graph
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
Given a reference to a node in a connected undirected graph, return a deep copy of the graph.

    Each node contains an integer value and a list of neighbors.

Why this pattern fits:
Graph cloning is DFS plus memoization.
    Without a visited map, cycles would cause infinite recursion and duplicate nodes.

Approach:
Use a hashmap from original node to cloned node.
    DFS each node, create its clone once, and recursively clone its neighbors.

Worked Example:
Example:
    A square graph 1-2-3-4-1 should be cloned into a structurally identical graph with new nodes.

Time Complexity: O(V + E)
Space Complexity: O(V)
*/

public class P09_CloneGraphDFSGraph {
    static class Node {
        int val;
        List<Node> neighbors;
        Node() {
            val = 0;
            neighbors = new ArrayList<>();
        }
        Node(int val) {
            this.val = val;
            neighbors = new ArrayList<>();
        }
    }

    public static Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        return dfs(node, new HashMap<>());
    }

    private static Node dfs(Node node, Map<Node, Node> copies) {
        if (copies.containsKey(node)) {
            return copies.get(node);
        }

        Node clone = new Node(node.val);
        copies.put(node, clone);

        for (Node neighbor : node.neighbors) {
            clone.neighbors.add(dfs(neighbor, copies));
        }

        return clone;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.neighbors.add(n2);
        n1.neighbors.add(n4);
        n2.neighbors.add(n1);
        n2.neighbors.add(n3);
        n3.neighbors.add(n2);
        n3.neighbors.add(n4);
        n4.neighbors.add(n1);
        n4.neighbors.add(n3);

        Node clone = cloneGraph(n1);
        System.out.println("Original node value: " + n1.val);
        System.out.println("Cloned node value  : " + clone.val);
        System.out.println("Original == clone? : " + (n1 == clone));
    }
}
