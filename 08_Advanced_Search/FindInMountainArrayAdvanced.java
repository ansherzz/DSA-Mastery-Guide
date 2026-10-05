import java.util.*;

/*
Problem: Find in Mountain Array
Pattern: 08. Advanced Search
Difficulty: Hard

Interview Prompt:
A mountain array strictly increases to a single peak and then strictly decreases.
    You can access it only through the interface MountainArray with get(index) and length().
    Given a target, return the smallest index where the mountain array equals the target, or -1 if it is absent.

    You may make at most 100 calls to get for arrays of up to 10,000 elements, so a linear scan is not allowed.

Why this pattern fits:
The array as a whole is not sorted, but it is made of two sorted halves joined at the peak.
    Three binary searches solve it: one to locate the peak, one on the ascending side,
    and one on the descending side with the comparison flipped.

Approach:
1. Find the peak index by comparing get(mid) with get(mid + 1).
    2. Binary search the ascending part [0, peak]. If the target is found, return it because it is the smaller index.
    3. Otherwise binary search the descending part [peak + 1, n - 1] with reversed comparisons.

Worked Example:
Example:
    array = [1, 2, 3, 4, 5, 3, 1], target = 3
    The peak is at index 4. The ascending side contains 3 at index 2, so the answer is 2
    even though 3 also appears at index 5.

Time Complexity: O(log n)
Space Complexity: O(1)
*/

public class FindInMountainArrayAdvanced {
    static class MountainArray {
        private final int[] data;

        MountainArray(int[] data) {
            this.data = data;
        }

        public int get(int index) {
            return data[index];
        }

        public int length() {
            return data.length;
        }
    }

    public static int findInMountainArray(int target, MountainArray mountain) {
        int n = mountain.length();

        int left = 0;
        int right = n - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mountain.get(mid) < mountain.get(mid + 1)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int peak = left;

        int index = search(mountain, target, 0, peak, true);
        if (index != -1) {
            return index;
        }
        return search(mountain, target, peak + 1, n - 1, false);
    }

    private static int search(MountainArray mountain, int target, int left, int right, boolean ascending) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int value = mountain.get(mid);
            if (value == target) {
                return mid;
            }
            if ((value < target) == ascending) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private static void runDemo(int[] data, int target) {
        System.out.println("Mountain: " + Arrays.toString(data));
        System.out.println("Target  : " + target);
        System.out.println("Index   : " + findInMountainArray(target, new MountainArray(data)));
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new int[]{1, 2, 3, 4, 5, 3, 1}, 3);
        runDemo(new int[]{0, 1, 2, 4, 2, 1}, 3);
        runDemo(new int[]{0, 5, 3, 1}, 1);
    }
}
