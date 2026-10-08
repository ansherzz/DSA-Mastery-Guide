import java.util.*;

/*
Problem: Search in a Sorted Array of Unknown Size
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
You are given access to a sorted array of distinct integers only through an interface ArrayReader.
    reader.get(i) returns the value at index i, or 2^31 - 1 if i is out of bounds.
    You do not know the length of the array.

    Given a target, return its index, or -1 if it is not present. The solution must run in O(log n) time.

Why this pattern fits:
Binary search needs a right boundary, and here you do not have one.
    The fix is to discover a boundary by doubling: grow the range exponentially until it must contain the target.
    After that, ordinary binary search applies.

Approach:
Start with right = 1 and double it while reader.get(right) is still smaller than the target.
    Out-of-bounds reads return a huge value, so the doubling stops naturally.
    Then binary search between right / 2 and right.

Worked Example:
Example:
    Hidden array = [-1, 0, 3, 5, 9, 12], target = 9
    right doubles 1 -> 2 -> 4. reader.get(4) = 9 is not smaller than 9, so stop.
    Binary search in [2, 4] finds 9 at index 4.

Time Complexity: O(log p) where p is the index of the target
Space Complexity: O(1)
*/

public class P38_SearchInSortedArrayOfUnknownSizeBinarySearch {
    static class ArrayReader {
        private final int[] data;

        ArrayReader(int[] data) {
            this.data = data;
        }

        public int get(int index) {
            return index < data.length ? data[index] : Integer.MAX_VALUE;
        }
    }

    public static int search(ArrayReader reader, int target) {
        int right = 1;
        while (reader.get(right) < target) {
            right *= 2;
        }

        int left = right / 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = reader.get(mid);
            if (value == target) {
                return mid;
            }
            if (value < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private static void runDemo(int[] data, int target) {
        System.out.println("Hidden : " + Arrays.toString(data));
        System.out.println("Target : " + target);
        System.out.println("Index  : " + search(new ArrayReader(data), target));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{-1, 0, 3, 5, 9, 12}, 9);
        runDemo(new int[]{-1, 0, 3, 5, 9, 12}, 2);
        runDemo(new int[]{-1, 0, 3, 5, 9, 12}, -1);
    }
}
