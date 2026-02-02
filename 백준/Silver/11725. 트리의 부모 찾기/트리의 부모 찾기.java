import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 테스트 케이스 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // 인접 리스트 초기화
        List<List<Integer>> adj = new ArrayList<>(n + 1);
        for (int i = 0; i < n + 1; i++) {
            adj.add(new ArrayList<>());
        }

        // 인접 리스트 구성
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            adj.get(first).add(second);
            adj.get(second).add(first);
        }

        // bfs
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] parents = new int[n + 1];

        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : adj.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);

                    parents[next] = current;
                }
            }
        }

        // 부모 노드 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < n + 1; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}
