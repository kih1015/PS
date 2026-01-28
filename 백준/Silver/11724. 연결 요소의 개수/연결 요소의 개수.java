import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 정점의 개수(n)과 간선의 개수(m) 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        // [2단계] 인접 리스트 만들기
        List<List<Integer>> list = new ArrayList<>();
        
        for (int i = 0; i < n + 1; i++) {
            list.add(new ArrayList<>());
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            list.get(x).add(y);
            list.get(y).add(x);
        }
        
        // [3단계] 연결 요소의 개수 구하기
        boolean[] visited = new boolean[n + 1];
        int count = 0;
        
        for (int i = 1; i <= n; i++) {
            if (visited[i]) {
                continue;
            }
            
            dfs(list, visited, i);
            count++;
        }
        
        // [4단계] 출력
        System.out.println(count);
        br.close();
    }
    
    private static void dfs(List<List<Integer>> list, boolean[] visited, int current) {
        visited[current] = true;
        
        for (int next : list.get(current)) {
            if (!visited[next]) {
                dfs(list, visited, next);
            }
        }
    }
}
