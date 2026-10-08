import java.util.*;

/*
Problem: Generate Binary Numbers from 1 to N
Pattern: 11. Queue / BFS
Difficulty: Easy

Interview Prompt:
Given n, return the binary representations of the numbers 1 to n as strings, in increasing order,
without converting each number individually.

Why this pattern fits:
Binary strings form a tree: the children of s are s + "0" and s + "1".
Visiting that tree in level order yields the numbers in increasing order, and a queue produces level order.

Approach:
Start with "1" in the queue. Repeat n times: pop the front, record it, and push front + "0" and front + "1".

Worked Example:
n = 5 -> "1" produces "10" and "11"; "10" produces "100" and "101". Answer = [1, 10, 11, 100, 101].

Time Complexity: O(n) queue operations
Space Complexity: O(n)
*/

public class P08_GenerateBinaryNumbersQueue {
    public static List<String> generate(int n) {
        List<String> result = new ArrayList<>();
        Deque<String> queue = new ArrayDeque<>();
        queue.add("1");
        for (int i = 0; i < n; i++) {
            String current = queue.poll();
            result.add(current);
            queue.add(current + "0");
            queue.add(current + "1");
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("n = 5 -> " + generate(5));
        System.out.println("n = 1 -> " + generate(1));
    }
}
