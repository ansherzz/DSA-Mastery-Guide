import java.util.*;

/*
Problem: Most Beautiful Item for Each Query
Pattern: 06. Binary Search - Classic Variations
Difficulty: Medium

Interview Prompt:
You are given a 2D array items where items[i] = [price, beauty].
    You are also given an array queries. For each queries[j], determine the maximum beauty of an item
    whose price is less than or equal to queries[j]. If no such item exists, the answer is 0.
    Return an array with the answer to every query.

Why this pattern fits:
"Best value among all items with price <= q" is a prefix question on the price-sorted items.
    Precomputing the running best turns each query into an upper-bound search on price.

Approach:
Sort the items by price. Replace each beauty with the maximum beauty among items up to that position.
    For each query, binary search for the last item with price <= query.
    The running maximum stored there is the answer, or 0 if no item is affordable.

Worked Example:
Example:
    items = [[1, 2], [3, 2], [2, 4], [5, 6], [3, 5]], queries = [1, 2, 3, 4, 5, 6]
    Sorted by price with running best beauty: (1, 2), (2, 4), (3, 4), (3, 5), (5, 6).
    Answers: [2, 4, 5, 5, 6, 6].

Time Complexity: O((n + q) log n)
Space Complexity: O(1) besides the sort and the result
*/

public class P28_MostBeautifulItemForEachQueryBinarySearch {
    public static int[] maximumBeauty(int[][] items, int[] queries) {
        Arrays.sort(items, (a, b) -> Integer.compare(a[0], b[0]));
        for (int i = 1; i < items.length; i++) {
            items[i][1] = Math.max(items[i][1], items[i - 1][1]);
        }

        int[] answer = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int left = 0;
            int right = items.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (items[mid][0] <= queries[q]) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            answer[q] = left == 0 ? 0 : items[left - 1][1];
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maximumBeauty(
            new int[][]{{1, 2}, {3, 2}, {2, 4}, {5, 6}, {3, 5}}, new int[]{1, 2, 3, 4, 5, 6})));
        System.out.println(Arrays.toString(maximumBeauty(
            new int[][]{{10, 1000}}, new int[]{5})));
    }
}
