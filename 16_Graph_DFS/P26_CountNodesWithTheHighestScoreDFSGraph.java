import java.util.*;

/*
Problem: Count Nodes With the Highest Score
Pattern: 16. Graph DFS
Difficulty: Medium

Interview Prompt:
A binary tree is given by a parents array (root has parent -1). The score of a node is the product of the sizes
of the non-empty subtrees that remain after removing it and its edges. Return how many nodes have the highest score.

Why this pattern fits:
Removing a node leaves its children's subtrees plus "everything else".
All of those sizes follow from subtree sizes, which a single postorder DFS computes.

Approach:
Build child lists. dfs(node) returns the subtree size; while computing it, multiply the sizes of the children
and, if positive, n - size (the part above the node). Track the best product and how many nodes reach it.

Worked Example:
parents = [-1, 2, 0, 2, 0] -> removing node 1, 3, or 4 leaves one piece of 4 nodes (score 4); three nodes share the top score.

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P26_CountNodesWithTheHighestScoreDFSGraph {
    private static long bestScore;
    private static int bestCount;

    private static int dfs(List<List<Integer>> children, int node, int n) {
        long score = 1;
        int size = 1;
        for (int child : children.get(node)) {
            int childSize = dfs(children, child, n);
            score *= childSize;
            size += childSize;
        }
        if (n - size > 0) {
            score *= n - size;
        }
        if (score > bestScore) {
            bestScore = score;
            bestCount = 1;
        } else if (score == bestScore) {
            bestCount++;
        }
        return size;
    }

    public static int countHighestScoreNodes(int[] parents) {
        int n = parents.length;
        List<List<Integer>> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }
        for (int i = 1; i < n; i++) {
            children.get(parents[i]).add(i);
        }
        bestScore = 0;
        bestCount = 0;
        dfs(children, 0, n);
        return bestCount;
    }

    public static void main(String[] args) {
        System.out.println("[-1, 2, 0, 2, 0] -> " + countHighestScoreNodes(new int[]{-1, 2, 0, 2, 0}));
        System.out.println("[-1, 2, 0] -> " + countHighestScoreNodes(new int[]{-1, 2, 0}));
    }
}
