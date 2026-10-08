import java.util.*;

/*
Problem: Find All People With Secret
Pattern: 18. Union Find
Difficulty: Hard

Interview Prompt:
Person 0 knows a secret and shares it with firstPerson at time 0. meetings[i] = [x, y, time]:
if either knows the secret at that time, both do, and it spreads instantly through all meetings at the same time.
Return everyone who knows the secret at the end.

Why this pattern fits:
Meetings at the same time form a small graph whose connected components share the secret if any member knows it.
But those connections are temporary: people who met without learning the secret must not stay linked afterwards.

Approach:
Sort meetings by time. For each group of equal time: union all pairs, then for every participant
that is not connected to person 0, reset it to a singleton. At the end, report everyone connected to 0.

Worked Example:
n = 6, firstPerson = 1, meetings (1,2,5), (2,3,8), (1,5,10) -> the secret reaches 2, then 3, then 5.

Time Complexity: O(m log m + m * alpha(n))
Space Complexity: O(n)
*/

public class P42_FindAllPeopleWithSecretUnionFind {
    static class UnionFind {
        final int[] parent;
        final int[] size;
        int components;

        UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            while (parent[x] != x) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            }
            if (size[rootA] < size[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            components--;
            return true;
        }
    }

    public static List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        UnionFind sets = new UnionFind(n);
        sets.union(0, firstPerson);
        int i = 0;
        while (i < meetings.length) {
            int j = i;
            while (j < meetings.length && meetings[j][2] == meetings[i][2]) {
                sets.union(meetings[j][0], meetings[j][1]);
                j++;
            }
            for (int k = i; k < j; k++) {
                for (int person : new int[]{meetings[k][0], meetings[k][1]}) {
                    if (sets.find(person) != sets.find(0)) {
                        sets.parent[person] = person;
                        sets.size[person] = 1;
                    }
                }
            }
            i = j;
        }
        List<Integer> result = new ArrayList<>();
        for (int person = 0; person < n; person++) {
            if (sets.find(person) == sets.find(0)) {
                result.add(person);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("n = 6 -> " + findAllPeople(6, new int[][]{{1, 2, 5}, {2, 3, 8}, {1, 5, 10}}, 1));
        System.out.println("n = 4 -> " + findAllPeople(4, new int[][]{{3, 1, 3}, {1, 2, 2}, {0, 3, 3}}, 3));
        System.out.println("n = 5 -> " + findAllPeople(5, new int[][]{{3, 4, 2}, {1, 2, 1}, {2, 3, 1}}, 1));
    }
}
