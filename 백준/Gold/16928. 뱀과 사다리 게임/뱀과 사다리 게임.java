import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] N: 사다리의 수, M: 뱀의 수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // [2단계] 뱀, 사다리 맵 구성
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n + m; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st2.nextToken());
            int end = Integer.parseInt(st2.nextToken());

            map.put(start, end);
        }

        // [3단계] bfs
        int count = bfs(map);

        // [4단계] 결과 출력
        System.out.println(count);
        br.close();
    }

    private static int bfs(Map<Integer, Integer> map) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        boolean[] visited = new boolean[101];
        visited[1] = true;

        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                if (current == 100) {
                    return count;
                }

                for (int j = 1; j <= 6; j++) {
                    int next = current + j;

                    if (next > 100) {
                        break;
                    }

                    if (map.containsKey(next)) {
                        next = map.get(next);
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }

            count++;
        }
        
        return count;
    }
}
