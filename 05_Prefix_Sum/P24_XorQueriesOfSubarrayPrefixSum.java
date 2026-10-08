import java.util.*;

/*
Problem: XOR Queries of a Subarray
Pattern: 05. Prefix Sum
Difficulty: Medium

Interview Prompt:
Given an array arr of positive integers and a list of queries where queries[i] = [left, right],
    compute for each query the XOR of the elements from index left to index right inclusive.
    Return an array with the answer to every query.

Why this pattern fits:
The prefix idea works for any operation that can be undone. Addition is undone by subtraction,
    and XOR is undone by XOR itself, because x ^ x = 0.
    So a prefix-XOR array answers every range query in O(1).

Approach:
Build prefix where prefix[i] is the XOR of the first i elements.
    The XOR of the range [left, right] is prefix[right + 1] ^ prefix[left],
    because the shared leading part cancels out.

Worked Example:
Example:
    arr = [1, 3, 4, 8]
    prefix = [0, 1, 2, 6, 14]
    Query [0, 1] -> prefix[2] ^ prefix[0] = 2
    Query [1, 2] -> prefix[3] ^ prefix[1] = 6 ^ 1 = 7
    Query [0, 3] -> 14
    Query [3, 3] -> 14 ^ 6 = 8

Time Complexity: O(n + q)
Space Complexity: O(n)
*/

public class P24_XorQueriesOfSubarrayPrefixSum {
    public static int[] xorQueries(int[] arr, int[][] queries) {
        int[] prefix = new int[arr.length + 1];
        for (int i = 0; i < arr.length; i++) {
            prefix[i + 1] = prefix[i] ^ arr[i];
        }

        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            answer[i] = prefix[queries[i][1] + 1] ^ prefix[queries[i][0]];
        }
        return answer;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 4, 8};
        int[][] queries = {{0, 1}, {1, 2}, {0, 3}, {3, 3}};

        System.out.println("Array   : " + Arrays.toString(arr));
        System.out.println("Queries : " + Arrays.deepToString(queries));
        System.out.println("Answers : " + Arrays.toString(xorQueries(arr, queries)));
    }
}
