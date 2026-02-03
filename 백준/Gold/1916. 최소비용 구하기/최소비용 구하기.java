import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 1; i <= m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int destination = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj.get(start).add(new int[] {destination, weight});
        }

        // 출발 / 도착노드 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int destination = Integer.parseInt(st.nextToken());

        // 우선순위 큐 초기화
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[] {start, 0});

        // 거리 배열 초기화
        int[] distance = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        // 다익스트라
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            if (distance[currentNode] < currentDistance) {
                continue;
            }

            for (int[] next : adj.get(currentNode)) {
                int nextNode = next[0];
                int nextDistance = currentDistance + next[1];

                if (nextDistance < distance[nextNode]) {
                    distance[nextNode] = nextDistance;
                    pq.offer(new int[]{nextNode, nextDistance});
                }
            }
        }

        System.out.println(distance[destination]);
        br.close();
    }
}
