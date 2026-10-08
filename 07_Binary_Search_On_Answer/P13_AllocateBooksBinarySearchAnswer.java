import java.util.*;

/*
Problem: Allocate Books (Minimize Maximum Pages)
Pattern: 07. Binary Search on Answer
Difficulty: Medium

Interview Prompt:
pages[i] is the page count of the i-th book. Allocate all books to m students so that each student gets
a contiguous block of at least one book. Minimize the maximum number of pages assigned to any student.
Return -1 if there are fewer books than students.

Why this pattern fits:
"Minimize the maximum over contiguous groups" is the signature of binary search on the answer:
a larger limit can only need fewer students.

Approach:
Search the limit in [largest book, total pages].
For a limit, scan the books and start a new student whenever adding a book would exceed the limit.
The limit is feasible when the number of students used is at most m.

Worked Example:
pages = [12, 34, 67, 90], m = 2 -> [12, 34, 67] and [90] gives a maximum of 113, the best possible.

Time Complexity: O(n log(sum of pages))
Space Complexity: O(1)
*/

public class P13_AllocateBooksBinarySearchAnswer {
    private static int studentsNeeded(int[] pages, int limit) {
        int students = 1;
        int current = 0;
        for (int p : pages) {
            if (current + p > limit) {
                students++;
                current = 0;
            }
            current += p;
        }
        return students;
    }

    public static int allocate(int[] pages, int m) {
        if (pages.length < m) {
            return -1;
        }
        int low = 0;
        int high = 0;
        for (int p : pages) {
            low = Math.max(low, p);
            high += p;
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (studentsNeeded(pages, mid) <= m) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("[12, 34, 67, 90], m = 2 -> " + allocate(new int[]{12, 34, 67, 90}, 2));
        System.out.println("[25, 46, 28, 49, 24], m = 4 -> " + allocate(new int[]{25, 46, 28, 49, 24}, 4));
        System.out.println("[10, 20], m = 3 -> " + allocate(new int[]{10, 20}, 3));
    }
}
