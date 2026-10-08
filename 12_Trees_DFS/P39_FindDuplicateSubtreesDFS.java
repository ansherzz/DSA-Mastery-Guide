import java.util.*;

/*
Problem: Find Duplicate Subtrees
Pattern: 12. Trees & Recursion - DFS
Difficulty: Medium

Interview Prompt:
Return the roots of all duplicate subtrees (same structure and same values).
For each kind of duplicate, return any one root.

Why this pattern fits:
Two subtrees are identical exactly when their serializations are identical.
Building the encoding in postorder reuses the children's encodings, and a hash map counts repeats.

Approach:
key(node) = value + "," + key(left) + "," + key(right), with "#" for null.
Count each key; when a count reaches 2, add that node to the answer.

Worked Example:
[1, 2, 3, 4, null, 2, 4, null, null, 4] -> the leaf 4 and the subtree 2 -> 4 each appear more than once.

Time Complexity: O(n^2) for string building in the worst case
Space Complexity: O(n^2)
*/

public class P39_FindDuplicateSubtreesDFS {
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

    private static String encode(TreeNode node, Map<String, Integer> count, List<TreeNode> duplicates) {
        if (node == null) {
            return "#";
        }
        String key = node.val + "," + encode(node.left, count, duplicates) + "," + encode(node.right, count, duplicates);
        if (count.merge(key, 1, Integer::sum) == 2) {
            duplicates.add(node);
        }
        return key;
    }

    public static List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> duplicates = new ArrayList<>();
        encode(root, new HashMap<>(), duplicates);
        return duplicates;
    }

    private static String describe(List<TreeNode> roots) {
        List<String> text = new ArrayList<>();
        for (TreeNode root : roots) {
            text.add(format(root));
        }
        return text.toString();
    }

    public static void main(String[] args) {
        System.out.println("[1, 2, 3, 4, null, 2, 4, null, null, 4] -> "
                + describe(findDuplicateSubtrees(build(1, 2, 3, 4, null, 2, 4, null, null, 4))));
        System.out.println("[2, 1, 3] -> " + describe(findDuplicateSubtrees(build(2, 1, 3))));
    }
}
