import java.util.*;

/*
Problem: Palindrome Linked List
Pattern: 09. Linked List
Difficulty: Easy

Interview Prompt:
Given the head of a singly linked list, return true if the values read the same forward and backward.

    Follow-up: do it in O(n) time and O(1) extra space.

Why this pattern fits:
A singly linked list cannot be walked backward, so the usual two-ended palindrome check is not available.
    Combining two list fundamentals fixes that: find the middle, then reverse the second half in place.

Approach:
Use slow/fast pointers to find the start of the second half.
    Reverse the second half.
    Walk both halves together and compare values.
    Finally reverse the second half again so the caller's list is left unchanged.

Worked Example:
Example:
    1 -> 2 -> 2 -> 1
    The second half 2 -> 1 becomes 1 -> 2. Comparing 1, 2 with 1, 2 matches. Answer = true.

Time Complexity: O(n)
Space Complexity: O(1)
*/

public class P05_PalindromeLinkedListPattern {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode secondHalf = reverse(slow);
        ListNode first = head;
        ListNode second = secondHalf;
        boolean palindrome = true;

        while (second != null) {
            if (first.val != second.val) {
                palindrome = false;
                break;
            }
            first = first.next;
            second = second.next;
        }

        reverse(secondHalf);
        return palindrome;
    }

    private static ListNode reverse(ListNode head) {
        ListNode prev = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = prev;
            prev = head;
            head = next;
        }
        return prev;
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

    private static String stringify(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) {
                sb.append(" -> ");
            }
            head = head.next;
        }
        return sb.toString();
    }

    private static void runDemo(int... values) {
        ListNode head = build(values);
        System.out.println("List       : " + stringify(head));
        System.out.println("Palindrome?: " + isPalindrome(head));
        System.out.println("After check: " + stringify(head));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(1, 2, 2, 1);
        runDemo(1, 2, 3, 2, 1);
        runDemo(1, 2);
    }
}
