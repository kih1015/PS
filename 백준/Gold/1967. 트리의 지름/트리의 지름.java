import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static class Node {
        int index;
        int cost;

        Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<List<Node>> tree = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            tree.get(parent).add(new Node(child, cost));
            tree.get(child).add(new Node(parent, cost));
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[1] = 0;
        
        dfs(tree, 1, dist);

        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 1; i <= n; i++) {
            if (dist[i] > max) {
                max = dist[i];
                maxIndex = i;
            }
        }

        Arrays.fill(dist, -1);
        dist[maxIndex] = 0;
        
        dfs(tree, maxIndex, dist);

        int diameter = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            diameter = Math.max(diameter, dist[i]);
        }

        System.out.println(diameter);
        br.close();
    }

    private static void dfs(List<List<Node>> tree, int curr, int[] dist) {
        for (Node next : tree.get(curr)) {
            if (dist[next.index] == -1) {
                dist[next.index] = dist[curr] + next.cost;
                dfs(tree, next.index, dist);
            }
        }
    }
}
