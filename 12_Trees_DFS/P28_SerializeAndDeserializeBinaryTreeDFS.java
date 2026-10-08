import java.util.*;

/*
Problem: Serialize and Deserialize Binary Tree
Pattern: 12. Trees & Recursion - DFS
Difficulty: Hard

Interview Prompt:
Design an algorithm to convert a binary tree into a string (serialize) and to rebuild the exact same tree
    from that string (deserialize). There is no restriction on the format, as long as
    deserialize(serialize(tree)) reproduces the original structure and values.

Why this pattern fits:
A pre-order traversal alone does not determine a tree, but a pre-order traversal that also records
    every null child does. Reading that sequence back in the same pre-order rebuilds the tree
    with one recursive function that mirrors the one that wrote it.

Approach:
Serialize: pre-order DFS. Write the node value, then the left subtree, then the right subtree.
    Write a marker such as "#" for every null child. Separate tokens with commas.
    Deserialize: read tokens from a queue. A "#" returns null.
    Otherwise create a node and build its left subtree and then its right subtree from the following tokens.

Worked Example:
Example:
            1
           / \
          2   3
             / \
            4   5
    Serialized: "1,2,#,#,3,4,#,#,5,#,#"
    Reading it back: 1, then its left child 2 (with two nulls), then its right child 3 with children 4 and 5.

Time Complexity: O(n) for both operations
Space Complexity: O(n)
*/

public class P28_SerializeAndDeserializeBinaryTreeDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        write(root, sb);
        return sb.toString();
    }

    private static void write(TreeNode node, StringBuilder sb) {
        if (sb.length() > 0) {
            sb.append(',');
        }
        if (node == null) {
            sb.append('#');
            return;
        }
        sb.append(node.val);
        write(node.left, sb);
        write(node.right, sb);
    }

    public static TreeNode deserialize(String data) {
        Deque<String> tokens = new ArrayDeque<>(Arrays.asList(data.split(",")));
        return read(tokens);
    }

    private static TreeNode read(Deque<String> tokens) {
        String token = tokens.poll();
        if (token.equals("#")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(token));
        node.left = read(tokens);
        node.right = read(tokens);
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(-5);

        String encoded = serialize(root);
        System.out.println("Serialized     : " + encoded);

        TreeNode rebuilt = deserialize(encoded);
        String reEncoded = serialize(rebuilt);
        System.out.println("Re-serialized  : " + reEncoded);
        System.out.println("Round trip ok? : " + encoded.equals(reEncoded));

        System.out.println("Empty tree     : " + serialize(deserialize(serialize(null))));
    }
}
