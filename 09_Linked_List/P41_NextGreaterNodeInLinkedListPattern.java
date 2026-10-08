import java.util.*;

/*
Problem: Next Greater Node In Linked List
Pattern: 09. Linked List
Difficulty: Medium

Interview Prompt:
For each node, find the value of the first node after it that is strictly larger.
Return the answers as an array, using 0 where no such node exists.

Why this pattern fits:
"Next greater element" is a monotonic-stack problem. The list only supplies the values in order,
so the stack holds indices of nodes still waiting for a larger value.

Approach:
Read the values into a list. Scan left to right; while the current value is greater than the value
at the index on top of the stack, pop it and record the current value as its answer. Push the current index.

Worked Example:
[2, 7, 4, 3, 5] -> 2 sees 7; 4 and 3 see 5; 7 and 5 see nothing. Answer = [7, 0, 5, 5, 0].

Time Complexity: O(n)
Space Complexity: O(n)
*/

public class P41_NextGreaterNodeInLinkedListPattern {
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

    public static int[] nextLargerNodes(ListNode head) {
        List<Integer> values = new ArrayList<>();
        for (ListNode node = head; node != null; node = node.next) {
            values.add(node.val);
        }
        int[] answer = new int[values.size()];
        Deque<Integer> waiting = new ArrayDeque<>();
        for (int i = 0; i < values.size(); i++) {
            while (!waiting.isEmpty() && values.get(waiting.peek()) < values.get(i)) {
                answer[waiting.pop()] = values.get(i);
            }
            waiting.push(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println("[2, 1, 5] -> " + Arrays.toString(nextLargerNodes(build(2, 1, 5))));
        System.out.println("[2, 7, 4, 3, 5] -> " + Arrays.toString(nextLargerNodes(build(2, 7, 4, 3, 5))));
    }
}
