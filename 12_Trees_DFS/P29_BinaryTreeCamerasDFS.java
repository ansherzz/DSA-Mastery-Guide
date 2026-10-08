import java.util.*;

/*
Problem: Binary Tree Cameras
Pattern: 12. Trees & Recursion - DFS
Difficulty: Hard

Interview Prompt:
You install cameras on the nodes of a binary tree.
    Each camera monitors its own node, its parent, and its immediate children.
    Return the minimum number of cameras needed to monitor every node of the tree.

Why this pattern fits:
The decision at a node depends on what its children need, so information must flow upward: post-order DFS.
    The greedy insight is that a leaf should never hold a camera; placing it on the leaf's parent
    covers strictly more. Working from the bottom up, cameras are placed only when a child forces it.

Approach:
Each DFS call returns one of three states for its node:
    NEEDS_COVER  - the node is not monitored yet and relies on its parent
    HAS_CAMERA   - the node holds a camera
    COVERED      - the node is monitored by a child's camera (null nodes also count as covered)
    Rules: if any child needs cover, place a camera here. Else if any child has a camera, this node is covered.
    Otherwise this node needs cover. If the root itself ends up needing cover, add one more camera.

Worked Example:
Example:
            0
           /
          0      <- camera here
         / \
        0   0
    One camera on the second node monitors its parent and both of its children. Answer = 1.

Time Complexity: O(n)
Space Complexity: O(h) recursion stack
*/

public class P29_BinaryTreeCamerasDFS {
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    private static final int NEEDS_COVER = 0;
    private static final int HAS_CAMERA = 1;
    private static final int COVERED = 2;

    private static int cameras;

    public static int minCameraCover(TreeNode root) {
        cameras = 0;
        if (visit(root) == NEEDS_COVER) {
            cameras++;
        }
        return cameras;
    }

    private static int visit(TreeNode node) {
        if (node == null) {
            return COVERED;
        }

        int left = visit(node.left);
        int right = visit(node.right);

        if (left == NEEDS_COVER || right == NEEDS_COVER) {
            cameras++;
            return HAS_CAMERA;
        }
        if (left == HAS_CAMERA || right == HAS_CAMERA) {
            return COVERED;
        }
        return NEEDS_COVER;
    }

    public static void main(String[] args) {
        TreeNode first = new TreeNode(0);
        first.left = new TreeNode(0);
        first.left.left = new TreeNode(0);
        first.left.right = new TreeNode(0);
        System.out.println("Cameras: " + minCameraCover(first));

        TreeNode chain = new TreeNode(0);
        chain.left = new TreeNode(0);
        chain.left.left = new TreeNode(0);
        chain.left.left.left = new TreeNode(0);
        chain.left.left.left.right = new TreeNode(0);
        System.out.println("Cameras: " + minCameraCover(chain));

        System.out.println("Cameras: " + minCameraCover(new TreeNode(0)));
    }
}
