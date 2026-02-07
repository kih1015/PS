import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static class Node {
        int idx;
        int cost;

        Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<Node>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj.get(start).add(new Node(end, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> (a.cost - b.cost));
        pq.offer(new Node(start, 0));

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        int[] parent = new int[n + 1];
        parent[start] = -1;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (dist[curr.idx] < curr.cost) {
                continue;
            }

            for (Node next : adj.get(curr.idx)) {
                int nextCost = curr.cost + next.cost;
                if (nextCost < dist[next.idx]) {
                    dist[next.idx] = nextCost;
                    parent[next.idx] = curr.idx;
                    pq.offer(new Node(next.idx, nextCost));
                }
            }
        }

        Stack<Integer> stack = new Stack<>();
        for (int curr = end; curr != -1; curr = parent[curr]) {
            stack.push(curr);
        }

        System.out.println(dist[end]);
        System.out.println(stack.size());

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.print(sb);
        br.close();
    }
}
