import java.util.*;

/*
Problem: Find the Index of the Large Integer
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
All integers in a hidden array are equal except one that is larger.
You can only call reader.compareSub(l, r, x, y), which compares sum(arr[l..r]) with sum(arr[x..y]),
and reader.length(). Find the index of the larger integer using few calls.

Why this pattern fits:
The array cannot be read directly, but comparing two equally long ranges reveals which one contains the larger value.
That halves the search space with a single call.

Approach:
Keep a range [low, high]. Split it into two halves of equal length (leave the last element out when the length is odd).
If the left half is heavier, keep it; if the right half is heavier, keep it; if they tie, the left-out element is the answer.

Worked Example:
arr = [7, 7, 7, 7, 10, 7, 7, 7] -> compare [0..3] with [4..7]: the right is heavier.
Compare [4..5] with [6..7]: left is heavier. Compare [4] with [5]: left is heavier. Answer = 4.

Time Complexity: O(log n) API calls
Space Complexity: O(1)
*/

public class P29_FindTheIndexOfTheLargeIntegerAdvanced {
    static class ArrayReader {
        private final int[] arr;

        ArrayReader(int[] arr) {
            this.arr = arr;
        }

        public int compareSub(int l, int r, int x, int y) {
            long first = 0;
            long second = 0;
            for (int i = l; i <= r; i++) {
                first += arr[i];
            }
            for (int i = x; i <= y; i++) {
                second += arr[i];
            }
            return Long.compare(first, second);
        }

        public int length() {
            return arr.length;
        }
    }

    public static int getIndex(ArrayReader reader) {
        int low = 0;
        int high = reader.length() - 1;
        while (low < high) {
            int half = (high - low + 1) / 2;
            int result = reader.compareSub(low, low + half - 1, low + half, low + 2 * half - 1);
            if (result > 0) {
                high = low + half - 1;
            } else if (result < 0) {
                int newLow = low + half;
                high = low + 2 * half - 1;
                low = newLow;
            } else {
                return high;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("[7, 7, 7, 7, 10, 7, 7, 7] -> " + getIndex(new ArrayReader(new int[]{7, 7, 7, 7, 10, 7, 7, 7})));
        System.out.println("[6, 6, 12] -> " + getIndex(new ArrayReader(new int[]{6, 6, 12})));
    }
}
