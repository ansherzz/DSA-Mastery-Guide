import java.util.*;

/*
Problem: Sparse Search
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
Given a sorted array of strings interspersed with empty strings, return the index of a target string, or -1.

Why this pattern fits:
The non-empty strings are sorted, so binary search still applies.
The obstacle is that mid may be uninformative; the fix is to move mid to the closest informative position.

Approach:
Standard binary search, but if words[mid] is empty, expand left and right from mid until a non-empty
string inside [low, high] is found. If there is none, the target is absent.

Worked Example:
words = ["at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""], target = "ball" -> index 4.

Time Complexity: O(log n) typically, O(n) in the worst case of many empty strings
Space Complexity: O(1)
*/

public class P15_SparseSearchAdvanced {
    public static int sparseSearch(String[] words, String target) {
        int low = 0;
        int high = words.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (words[mid].isEmpty()) {
                int left = mid - 1;
                int right = mid + 1;
                while (true) {
                    if (left < low && right > high) {
                        return -1;
                    }
                    if (right <= high && !words[right].isEmpty()) {
                        mid = right;
                        break;
                    }
                    if (left >= low && !words[left].isEmpty()) {
                        mid = left;
                        break;
                    }
                    left--;
                    right++;
                }
            }
            int comparison = words[mid].compareTo(target);
            if (comparison == 0) {
                return mid;
            }
            if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] words = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        for (String target : new String[]{"ball", "dad", "zebra"}) {
            System.out.println(target + " -> " + sparseSearch(words, target));
        }
    }
}
