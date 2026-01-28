import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] N: 연산 개수
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // [2단계] 캠퍼스 만들기 && 도연이 찾기
        char[][] campus = new char[n][m];
        int x = 0, y = 0;

        for (int i = 0; i < n; i++) {
            String row = br.readLine();

            for (int j = 0; j < m; j++) {
                campus[i][j] = row.charAt(j);

                if (campus[i][j] == 'I') {
                    x = i;
                    y = j;
                }
            }
        }

        // [3단계] bfs
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});

        boolean[][] visited = new boolean[n][m];
        visited[x][y] = true;

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        int count = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + dx[i];
                int nextY = current[1] + dy[i];
                boolean isBound = nextX >= 0 && nextX < n && nextY >= 0 && nextY < m;

                if (isBound && !visited[nextX][nextY]) {
                    if (campus[nextX][nextY] == 'X') {
                        continue;
                    }

                    if (campus[nextX][nextY] == 'P') {
                        count++;
                    }

                    visited[nextX][nextY] = true;
                    queue.offer(new int[]{nextX, nextY});
                }
            }
        }
        
        // [4단계] 결과 출력
        if (count == 0) {
            System.out.println("TT");
            return;
        }
        
        System.out.print(count);
        br.close();
    }
}
