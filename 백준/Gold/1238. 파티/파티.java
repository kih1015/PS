import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static class Node {
        int index;
        int weight;

        public Node(int index, int weight) {
            this.index = index;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        List<List<Node>> adj = new ArrayList<>();
        List<List<Node>> reverseAdj = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
            reverseAdj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj.get(u).add(new Node(v, w));
            reverseAdj.get(v).add(new Node(u, w));
        }

        int[] distToHome = dijkstra(n, x, adj);
        int[] distToParty = dijkstra(n, x, reverseAdj);

        int maxTime = 0;
        for (int i = 1; i <= n; i++) {
            maxTime = Math.max(maxTime, distToHome[i] + distToParty[i]);
        }

        System.out.print(maxTime);
        br.close();
    }

    private static int[] dijkstra(int n, int start, List<List<Node>> graph) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (dist[curr.index] < curr.weight) {
                continue;
            }

            for (Node next : graph.get(curr.index)) {
                int nextWeight = dist[curr.index] + next.weight;

                if (nextWeight < dist[next.index]) {
                    dist[next.index] = nextWeight;
                    pq.offer(new Node(next.index, nextWeight));
                }
            }
        }
        return dist;
    }
}
