import java.util.*;

/*
Problem: Union Find Template (Path Compression and Union by Rank)
Pattern: 18. Union Find
Difficulty: Easy

Interview Prompt:
Implement a disjoint set union structure over n elements supporting
find(x), union(a, b), connected(a, b), the number of components, and the size of the set containing x.

Why this pattern fits:
Many graph questions only ask "are these two in the same group?" while edges keep arriving.
Union find answers that in near-constant time without ever traversing the graph.

Approach:
parent[x] points toward the representative of x's set. find follows the pointers and compresses the path.
union attaches the root with the smaller rank under the other, updating size and the component count.

Worked Example:
n = 6: union(0, 1), union(1, 2) succeed; union(0, 2) finds them already together.
0 and 2 are connected, 0 and 3 are not. Four components remain and the set of 0 has size 3.

Time Complexity: O(alpha(n)) amortized per operation, effectively constant
Space Complexity: O(n)
*/

public class P01_UnionFindTemplateUnionFind {
    static class UnionFind {
        private final int[] parent;
        private final int[] rank;
        private final int[] size;
        private int components;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            size = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int a, int b) {
            int rootA = find(a);
            int rootB = find(b);
            if (rootA == rootB) {
                return false;
            }
            if (rank[rootA] < rank[rootB]) {
                int temp = rootA;
                rootA = rootB;
                rootB = temp;
            }
            parent[rootB] = rootA;
            size[rootA] += size[rootB];
            if (rank[rootA] == rank[rootB]) {
                rank[rootA]++;
            }
            components--;
            return true;
        }

        public boolean connected(int a, int b) {
            return find(a) == find(b);
        }

        public int components() {
            return components;
        }

        public int sizeOf(int x) {
            return size[find(x)];
        }
    }

    public static void main(String[] args) {
        UnionFind sets = new UnionFind(6);
        System.out.println("union(0, 1) -> " + sets.union(0, 1));
        System.out.println("union(1, 2) -> " + sets.union(1, 2));
        System.out.println("union(0, 2) -> " + sets.union(0, 2));
        System.out.println("connected(0, 2) -> " + sets.connected(0, 2));
        System.out.println("connected(0, 3) -> " + sets.connected(0, 3));
        System.out.println("components() -> " + sets.components());
        System.out.println("sizeOf(0) -> " + sets.sizeOf(0));
    }
}
