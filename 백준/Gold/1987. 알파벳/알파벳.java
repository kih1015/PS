import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static char[][] board;
    private static int r;
    private static int c;

    private static boolean[] visited;
    private static int max = 0;

    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new char[r + 1][c + 1];
        for (int i = 1; i <= r; i++) {
            String row = br.readLine();
            for (int j = 1; j <= c; j++) {
                board[i][j] = row.charAt(j - 1);
            }
        }

        visited = new boolean[26];
        visited[board[1][1] - 'A'] = true;

        dfs(1, 1, 1);

        System.out.print(max);
        br.close();
    }

    private static void dfs(int currR, int currC, int depth) {
        max = Math.max(max, depth);

        for (int i = 0; i < dx.length; i++) {
            int nextR = currR + dx[i];
            int nextC = currC + dy[i];

            boolean isBound = nextR >= 1 && nextR <= r && nextC >= 1 && nextC <= c;
            if (!isBound) {
                continue;
            }

            int charIndex = board[nextR][nextC] - 'A';
            if (visited[charIndex]) {
                continue;
            }

            visited[charIndex] = true;
            dfs(nextR, nextC, depth + 1);
            visited[charIndex] = false;
        }
    }
}
