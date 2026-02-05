import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static boolean[][] board;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        board = new boolean[n][n];
        count = 0;

        nQueen(0);
        System.out.print(count);

        br.close();
    }

    private static void nQueen(int row) {
        if (row == board.length) {
            count++;
        }

        for (int i = 0; i < board.length; i++) {
            boolean isPossible = true;

            // 세로줄 검사
            for (int j = 0; j < row; j++) {
                if (board[j][i]) {
                    isPossible = false;
                    break;
                }
            }

            // 왼쪽 대각선 검사
            int r = row;
            int c = i;
            while (r >= 0 && r < board.length && c >= 0 && c < board.length) {
                if (board[r][c]) {
                    isPossible = false;
                    break;
                }

                r--;
                c--;
            }

            // 오른쪽 대각선 검사
            r = row;
            c = i;
            while (r >= 0 && r < board.length && c >= 0 && c < board.length) {
                if (board[r][c]) {
                    isPossible = false;
                    break;
                }

                r--;
                c++;
            }

            if (isPossible) {
                board[row][i] = true;
                nQueen(row + 1);
                board[row][i] = false;
            }
        }
    }
}
