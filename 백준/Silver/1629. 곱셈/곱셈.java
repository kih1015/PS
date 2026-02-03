import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 집 갯수 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());

        // 출력
        System.out.println(pow(a, b, c));
    }

    private static long pow(long a, long b, long c) {
        if (b == 1) {
            return a % c;
        }

        long result = pow(a, b / 2, c);
        long temp = (result * result) % c;

        if (b % 2 != 0) {
            return (temp * (a % c)) % c;
        }
        return temp;
    }
}
