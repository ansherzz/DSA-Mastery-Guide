import java.util.*;

/*
Problem: Find the Minimum and Maximum Number of Nodes Between Critical Points
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
A critical point is a node that is a strict local maximum or local minimum (it needs both neighbors).
Return [minDistance, maxDistance] between any two distinct critical points, or [-1, -1] if there are fewer than two.

Why this pattern fits:
A linked list cannot be indexed, so everything must be gathered in one forward pass
by carrying just enough state: the previous value, the position, and the first and last critical positions seen.

Approach:
Walk with prev / current / next. When current is critical: the minimum distance candidate is index - lastCritical;
the maximum distance is always index - firstCritical. Update lastCritical and set firstCritical once.

Worked Example:
[5, 3, 1, 2, 5, 1, 2] -> critical points at indices 2, 4, 5. Min distance 1, max distance 3.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P33_MinAndMaxNodesBetweenCriticalPointsPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    private static ListNode build(int... values) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int value : values) {
            tail.next = new ListNode(value);
            tail = tail.next;
        }
        return dummy.next;
    }

    private static String format(ListNode head) {
        StringBuilder text = new StringBuilder("[");
        for (ListNode node = head; node != null; node = node.next) {
            text.append(node.val).append(node.next != null ? ", " : "");
        }
        return text.append("]").toString();
    }

    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int last = -1;
        int minDistance = Integer.MAX_VALUE;
        int index = 1;
        ListNode prev = head;
        ListNode current = head.next;
        while (current != null && current.next != null) {
            boolean isMax = current.val > prev.val && current.val > current.next.val;
            boolean isMin = current.val < prev.val && current.val < current.next.val;
            if (isMax || isMin) {
                if (first == -1) {
                    first = index;
                } else {
                    minDistance = Math.min(minDistance, index - last);
                }
                last = index;
            }
            prev = current;
            current = current.next;
            index++;
        }
        if (first == last) {
            return new int[]{-1, -1};
        }
        return new int[]{minDistance, last - first};
    }

    public static void main(String[] args) {
        System.out.println("[5, 3, 1, 2, 5, 1, 2] -> " + Arrays.toString(nodesBetweenCriticalPoints(build(5, 3, 1, 2, 5, 1, 2))));
        System.out.println("[1, 3, 2, 2, 3, 2, 2, 2, 7] -> "
                + Arrays.toString(nodesBetweenCriticalPoints(build(1, 3, 2, 2, 3, 2, 2, 2, 7))));
        System.out.println("[3, 1] -> " + Arrays.toString(nodesBetweenCriticalPoints(build(3, 1))));
    }
}
