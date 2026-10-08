import java.util.*;

/*
Problem: Recover a Tree From Preorder Traversal
Pattern: 12. Trees & Recursion - DFS
Difficulty: Hard

Interview Prompt:
A preorder traversal was written as text: each node is D dashes (its depth) followed by its value.
A node with one child always has it as the left child. Rebuild the tree and return its root.

Why this pattern fits:
In preorder, the next node is either a child of the previous node or a child of one of its ancestors.
The depth says which one: pop the path stack until it has exactly `depth` nodes, then attach to the top.

Approach:
Scan the string reading the dash count and then the number. Pop while the stack is larger than the depth.
Attach the new node as the left child of the top if that is free, otherwise as the right child. Push it.

Worked Example:
"1-2--3--4-5--6--7" -> 1 has children 2 and 5; 2 has 3 and 4; 5 has 6 and 7.

Time Complexity: O(n)
Space Complexity: O(h)
*/

public class P46_RecoverATreeFromPreorderTraversalDFS {
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

    // Prints a tree in level order, using null for a missing child.
    private static String format(TreeNode root) {
        List<String> out = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                out.add("null");
                continue;
            }
            out.add(String.valueOf(node.val));
            queue.add(node.left);
            queue.add(node.right);
        }
        while (!out.isEmpty() && out.get(out.size() - 1).equals("null")) {
            out.remove(out.size() - 1);
        }
        return out.toString();
    }

    public static TreeNode recoverFromPreorder(String traversal) {
        Deque<TreeNode> path = new ArrayDeque<>();
        int i = 0;
        while (i < traversal.length()) {
            int depth = 0;
            while (traversal.charAt(i) == '-') {
                depth++;
                i++;
            }
            int value = 0;
            while (i < traversal.length() && Character.isDigit(traversal.charAt(i))) {
                value = value * 10 + (traversal.charAt(i++) - '0');
            }
            TreeNode node = new TreeNode(value);
            while (path.size() > depth) {
                path.pop();
            }
            if (!path.isEmpty()) {
                if (path.peek().left == null) {
                    path.peek().left = node;
                } else {
                    path.peek().right = node;
                }
            }
            path.push(node);
        }
        return path.peekLast();
    }

    public static void main(String[] args) {
        for (String traversal : new String[]{"1-2--3--4-5--6--7", "1-2--3---4-5--6---7", "1-401--349---90--88"}) {
            System.out.println(traversal + " -> " + format(recoverFromPreorder(traversal)));
        }
    }
}
