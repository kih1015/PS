import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] n, m, v 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        // [2단계] 인접리스트 초기화
        List<List<Integer>> adjList = new ArrayList<>(n + 1);

        for (int i = 0; i < n + 1; i++) {
            adjList.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st2.nextToken());
            int y = Integer.parseInt(st2.nextToken());
            adjList.get(x).add(y);
            adjList.get(y).add(x);
        }
        for (int i = 1; i <= n; i++) {
            Collections.sort(adjList.get(i));
        }

        // [3단계] dfs
        StringBuilder sb = new StringBuilder();
        List<String> visitedOrder = new ArrayList<>();

        dfs(adjList, v, new boolean[n + 1], visitedOrder);
        sb.append(String.join(" ", visitedOrder)).append("\n");

        // [4단계] bfs
        sb.append(String.join(" ", bfs(adjList, v))).append("\n");

        // [5단계] 출력
        System.out.print(sb);
        br.close();
    }

    private static void dfs(List<List<Integer>> adjList, int vertex, boolean[] visited, List<String> visitedOrder) {
        visited[vertex] = true;
        visitedOrder.add(Integer.toString(vertex));

        for (int neighbor : adjList.get(vertex)) {
            if (!visited[neighbor]) {
                dfs(adjList, neighbor, visited, visitedOrder);
            }
        }
    }

    private static List<String> bfs(List<List<Integer>> adjList, int vertex) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[adjList.size() + 1];

        queue.add(vertex);
        visited[vertex] = true;

        List<String> visitedOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            visitedOrder.add(Integer.toString(curr));

            for (int neighbor : adjList.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return visitedOrder;
    }
}
