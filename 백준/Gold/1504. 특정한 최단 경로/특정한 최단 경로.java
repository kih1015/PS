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
        int cost;

        Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        List<List<Node>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            Node firstNode = new Node(first, distance);
            Node secondNode = new Node(second, distance);

            adj.get(first).add(secondNode);
            adj.get(second).add(firstNode);
        }

        st = new StringTokenizer(br.readLine());

        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] startPaths = getShortestPaths(adj, 1);
        int[] v1Paths = getShortestPaths(adj, v1);
        int[] v2Paths = getShortestPaths(adj, v2);

        boolean notExists1 = startPaths[v1] == Integer.MAX_VALUE
                || v1Paths[v2] == Integer.MAX_VALUE
                || v2Paths[n] == Integer.MAX_VALUE;
        boolean notExists2 = startPaths[v2] == Integer.MAX_VALUE
                || v2Paths[v1] == Integer.MAX_VALUE
                || v1Paths[n] == Integer.MAX_VALUE;
        
        if (notExists1 && notExists2) {
            System.out.print(-1);
            return;
        }

        int correctOrderLength = startPaths[v1] + v1Paths[v2] + v2Paths[n];
        int reverseOrderLength = startPaths[v2] + v2Paths[v1] + v1Paths[n];

        int finalLength = Math.min(correctOrderLength, reverseOrderLength);
        System.out.print(finalLength);
    }

    private static int[] getShortestPaths(List<List<Node>> adj, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Node(start, 0));

        int[] dist = new int[adj.size()];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.cost > dist[current.index]) {
                continue;
            }

            for (Node next : adj.get(current.index)) {
                int nextCost = current.cost + next.cost;
                if (nextCost < dist[next.index]) {
                    dist[next.index] = nextCost;
                    pq.offer(new Node(next.index, nextCost));
                }
            }
        }

        return dist;
    }
}
