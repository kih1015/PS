import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private enum Direction {
        LEFT(-1, 0),
        RIGHT(1, 0),
        UP(0, -1),
        DOWN(0, 1),
        LEFTDOWN(-1, 1),
        RIGHTUP(1, -1),
        ;

        private final int dx;
        private final int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }
    }

    public static void main(String[] args) throws IOException {
        // [1단계] 테스트 케이스 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] paper = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // [2단계] 테트로미노 19가지 방법 작성
        Direction[][] tetromino = {
                // 1자 블럭
                {Direction.RIGHT, Direction.RIGHT, Direction.RIGHT},
                {Direction.DOWN, Direction.DOWN, Direction.DOWN},
                // 정사각형 블럭
                {Direction.RIGHT, Direction.DOWN, Direction.LEFT},
                // ㄴ자 블럭
                {Direction.DOWN, Direction.DOWN, Direction.RIGHT},
                {Direction.DOWN, Direction.DOWN, Direction.LEFT},
                {Direction.UP, Direction.RIGHT, Direction.RIGHT},
                {Direction.UP, Direction.LEFT, Direction.LEFT},
                {Direction.RIGHT, Direction.DOWN, Direction.DOWN},
                {Direction.LEFT, Direction.DOWN, Direction.DOWN},
                {Direction.DOWN, Direction.LEFT, Direction.LEFT},
                {Direction.DOWN, Direction.RIGHT, Direction.RIGHT},
                // 번개모양 블럭
                {Direction.DOWN, Direction.RIGHT, Direction.DOWN},
                {Direction.DOWN, Direction.LEFT, Direction.DOWN},
                {Direction.RIGHT, Direction.UP, Direction.RIGHT},
                {Direction.LEFT, Direction.UP, Direction.LEFT},
                // 볼록모양 블럭
                {Direction.RIGHT, Direction.RIGHT, Direction.LEFTDOWN},
                {Direction.UP, Direction.UP, Direction.LEFTDOWN},
                {Direction.LEFT, Direction.LEFT, Direction.RIGHTUP},
                {Direction.DOWN, Direction.DOWN, Direction.RIGHTUP},
        };

        // [3단계] 최댓값 계산
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (Direction[] directions : tetromino) {
                    int currentRow = i;
                    int currentCol = j;
                    int sum = 0;
                    sum += paper[currentRow][currentCol];

                    for (Direction direction : directions) {
                        currentRow += direction.dy;
                        currentCol += direction.dx;
                        boolean isBounded = currentRow >= 0 && currentRow < n && currentCol >= 0 && currentCol < m;

                        if (!isBounded) {
                            sum = Integer.MIN_VALUE;
                            break;
                        }
                        sum += paper[currentRow][currentCol];
                    }

                    max = Math.max(max, sum);
                }
            }
        }

        // [4단계] 최댓값 출력
        System.out.println(max);
        br.close();
    }
}
