import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 수빈 위치, 동생 위치
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{n, 0});

        int[] distance = new int[100001];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[n] = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentNode = current[0];
            int currentDistance = current[1];

            if (distance[currentNode] < currentDistance) {
                continue;
            }

            if (currentNode + 1 <= 100000) {
                if (distance[currentNode + 1] > currentDistance + 1) {
                    distance[currentNode + 1] = currentDistance + 1;
                    pq.offer(new int[]{currentNode + 1, currentDistance + 1});
                }
            }

            if (currentNode * 2 <= 100000) {
                if (distance[currentNode * 2] > currentDistance) {
                    distance[currentNode * 2] = currentDistance;
                    pq.offer(new int[]{currentNode * 2, currentDistance});
                }
            }

            if (currentNode - 1 >= 0) {
                if (distance[currentNode - 1] > currentDistance + 1) {
                    distance[currentNode - 1] = currentDistance + 1;
                    pq.offer(new int[]{currentNode - 1, currentDistance + 1});
                }
            }
        }

        System.out.println(distance[k]);
        br.close();
    }
}
