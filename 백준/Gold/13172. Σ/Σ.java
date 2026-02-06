import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final long X = 1000000007L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());

        long sum = 0;
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long b = Long.parseLong(st.nextToken());
            long a = Long.parseLong(st.nextToken());

            long inverseB = power(b, X - 2);
            sum = (sum + (a * inverseB) % X) % X;
        }

        System.out.print(sum);
        br.close();
    }

    private static long power(long b, long n) {
        if (n == 1) {
            return b;
        }

        long half = power(b, n / 2);

        if (n % 2 == 0) {
            return (half * half) % X;
        }

        return ((half * half) % X * b) % X;
    }
}
