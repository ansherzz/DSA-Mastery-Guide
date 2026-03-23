import java.util.*;

/*
Problem: Accounts Merge
Pattern: 18. Union Find
Difficulty: Medium

Interview Prompt:
Each account is a list where the first item is a person's name and the remaining items are email addresses.
    Accounts belonging to the same person may share at least one common email.
    Merge accounts that belong together and return the grouped emails in sorted order.

    Union Find groups account indices that share emails.

Why this pattern fits:
Shared emails define connectivity between accounts.
    Once connected components are formed, all emails in the same component belong to the same merged account.

Approach:
Map each email to the first account index that contains it.
    Union the current account with that earlier account whenever an email repeats.
    Then gather emails by component root, sort them, and prefix the shared name.

Worked Example:
Example:
    Accounts for "John" sharing johnsmith@mail.com should be merged into one account.

Time Complexity: Roughly O(N alpha(N) + E log E)
Space Complexity: O(E)
*/

public class AccountsMergeUnionFind {
    static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        void union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) return;

            if (rank[rootA] < rank[rootB]) {
                parent[rootA] = rootB;
            } else if (rank[rootA] > rank[rootB]) {
                parent[rootB] = rootA;
            } else {
                parent[rootB] = rootA;
                rank[rootA]++;
            }
        }
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        DSU dsu = new DSU(accounts.size());
        Map<String, Integer> emailOwner = new HashMap<>();

        for (int i = 0; i < accounts.size(); i++) {
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String email = accounts.get(i).get(j);
                if (emailOwner.containsKey(email)) {
                    dsu.union(i, emailOwner.get(email));
                } else {
                    emailOwner.put(email, i);
                }
            }
        }

        Map<Integer, TreeSet<String>> groupedEmails = new HashMap<>();
        for (Map.Entry<String, Integer> entry : emailOwner.entrySet()) {
            int root = dsu.find(entry.getValue());
            groupedEmails.computeIfAbsent(root, ignored -> new TreeSet<>()).add(entry.getKey());
        }

        List<List<String>> answer = new ArrayList<>();
        for (Map.Entry<Integer, TreeSet<String>> entry : groupedEmails.entrySet()) {
            List<String> merged = new ArrayList<>();
            merged.add(accounts.get(entry.getKey()).get(0));
            merged.addAll(entry.getValue());
            answer.add(merged);
        }

        return answer;
    }

    public static void main(String[] args) {
        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));

        System.out.println(accountsMerge(accounts));
    }
}
