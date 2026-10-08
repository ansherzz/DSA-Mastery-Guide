import java.util.*;

/*
Problem: Number of Nodes in the Sub-Tree With the Same Label
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
A tree rooted at node 0 has a lowercase label on every node.
For each node, return how many nodes in its subtree (including itself) carry the same label as it.

Why this pattern fits:
The answer for a node aggregates information from its whole subtree,
so it is computed bottom-up: children report their letter counts and the parent adds them together.

Approach:
dfs(node, parent) returns an int[26]. Add the arrays of all children, add one for the node's own label,
and store counts[label] as the node's answer.

Worked Example:
labels = "abaedcd": node 0 ('a') has another 'a' at node 2 in its subtree, so its answer is 2.

Time Complexity: O(26 * n)
Space Complexity: O(26 * height)
*/

public class P37_NumberOfNodesInTheSubTreeWithTheSameLabelDFSGraph {
    private static int[] dfs(List<List<Integer>> graph, int node, int parent, String labels, int[] answer) {
        int[] counts = new int[26];
        for (int neighbor : graph.get(node)) {
            if (neighbor == parent) {
                continue;
            }
            int[] child = dfs(graph, neighbor, node, labels, answer);
            for (int i = 0; i < 26; i++) {
                counts[i] += child[i];
            }
        }
        int label = labels.charAt(node) - 'a';
        counts[label]++;
        answer[node] = counts[label];
        return counts;
    }

    public static int[] countSubTrees(int n, int[][] edges, String labels) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        int[] answer = new int[n];
        dfs(graph, 0, -1, labels, answer);
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("abaedcd -> " + Arrays.toString(countSubTrees(7,
                new int[][]{{0, 1}, {0, 2}, {1, 4}, {1, 5}, {2, 3}, {2, 6}}, "abaedcd")));
        System.out.println("bbbb -> " + Arrays.toString(countSubTrees(4, new int[][]{{0, 1}, {1, 2}, {0, 3}}, "bbbb")));
    }
}
