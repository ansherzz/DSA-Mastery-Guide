import java.util.*;

/*
Problem: Count of Smaller Numbers After Self (BST with Counts)
Pattern: 13. Binary Search Tree
Difficulty: Hard

Interview Prompt:
Given an array nums, return an array where counts[i] is the number of elements to the right of nums[i]
that are strictly smaller than nums[i].

Why this pattern fits:
Processing from the right, "how many already-inserted values are smaller than x" is a rank query.
A BST that stores left-subtree sizes answers it during the insertion itself.

Approach:
Each node stores its value, a duplicate count, and the number of nodes in its left subtree.
While inserting x: going left increments the node's left count; going right adds (left count + duplicates) to x's answer.
A self-balancing tree would guarantee O(log n); this plain BST is O(n) per insert in the worst case.

Worked Example:
nums = [5, 2, 6, 1] -> insert 1, 6, 2, 5 from the right; the answers are [2, 1, 1, 0].

Time Complexity: O(n log n) on average, O(n^2) worst case
Space Complexity: O(n)
*/

public class P48_CountOfSmallerNumbersAfterSelfBST {
    private static class Node {
        int val;
        int duplicates = 1;
        int leftCount = 0;
        Node left, right;
        Node(int val) { this.val = val; }
    }

    public static List<Integer> countSmaller(int[] nums) {
        Integer[] counts = new Integer[nums.length];
        Node root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            int smaller = 0;
            if (root == null) {
                root = new Node(nums[i]);
            } else {
                Node node = root;
                while (true) {
                    if (nums[i] == node.val) {
                        smaller += node.leftCount;
                        node.duplicates++;
                        break;
                    }
                    if (nums[i] < node.val) {
                        node.leftCount++;
                        if (node.left == null) {
                            node.left = new Node(nums[i]);
                            break;
                        }
                        node = node.left;
                    } else {
                        smaller += node.leftCount + node.duplicates;
                        if (node.right == null) {
                            node.right = new Node(nums[i]);
                            break;
                        }
                        node = node.right;
                    }
                }
            }
            counts[i] = smaller;
        }
        return Arrays.asList(counts);
    }

    public static void main(String[] args) {
        System.out.println("[5, 2, 6, 1] -> " + countSmaller(new int[]{5, 2, 6, 1}));
        System.out.println("[-1, -1] -> " + countSmaller(new int[]{-1, -1}));
    }
}
