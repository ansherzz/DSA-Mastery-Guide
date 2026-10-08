import java.util.*;

/*
Problem: Search Suggestions System
Pattern: 08. Advanced Search
Difficulty: Medium

Interview Prompt:
You are given an array of product names and a searchWord.
    After each character of searchWord is typed, suggest at most three product names that have the typed text
    as a prefix. If more than three products match, return the three lexicographically smallest.

    Return the list of suggestion lists, one for each typed character.

Why this pattern fits:
In a sorted list of strings, all strings sharing a prefix sit next to each other.
    The first one is found with a lower-bound binary search on the prefix,
    and the next two candidates are simply the entries that follow it.

Approach:
Sort the products.
    For each prefix of searchWord, binary search for the first product that is >= the prefix.
    Starting from that position, take up to three products as long as they start with the prefix.
    Because prefixes only get longer, the search can resume from the previous starting position.

Worked Example:
Example:
    products = ["mobile", "mouse", "moneypot", "monitor", "mousepad"], searchWord = "mouse"
    Sorted: mobile, moneypot, monitor, mouse, mousepad
    "m"  and "mo"                -> [mobile, moneypot, monitor]
    "mou", "mous", and "mouse"   -> [mouse, mousepad]

Time Complexity: O(n log n * L) to sort, plus O(L * log n * L) for the searches, where L is the word length
Space Complexity: O(n) for the sorted copy
*/

public class P25_SearchSuggestionsSystemAdvanced {
    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        String[] sorted = products.clone();
        Arrays.sort(sorted);

        List<List<String>> result = new ArrayList<>();
        int start = 0;

        for (int length = 1; length <= searchWord.length(); length++) {
            String prefix = searchWord.substring(0, length);

            int left = start;
            int right = sorted.length;
            while (left < right) {
                int mid = left + (right - left) / 2;
                if (sorted[mid].compareTo(prefix) < 0) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            start = left;

            List<String> suggestions = new ArrayList<>();
            for (int i = start; i < Math.min(start + 3, sorted.length); i++) {
                if (!sorted[i].startsWith(prefix)) {
                    break;
                }
                suggestions.add(sorted[i]);
            }
            result.add(suggestions);
        }
        return result;
    }

    private static void runDemo(String[] products, String searchWord) {
        System.out.println("Products : " + Arrays.toString(products));
        System.out.println("Search   : " + searchWord);
        for (List<String> suggestions : suggestedProducts(products, searchWord)) {
            System.out.println("  " + suggestions);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        runDemo(new String[]{"mobile", "mouse", "moneypot", "monitor", "mousepad"}, "mouse");
        runDemo(new String[]{"havana"}, "tatiana");
    }
}
