import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String s = br.readLine();

        // [2단계] pn 문자열 만들기
        StringBuilder sb = new StringBuilder();
        sb.append('I');
        for (int i = 0; i < n; i++) {
            sb.append("OI");
        }
        String pn = sb.toString();
        int pnSize = sb.length();

        // [3단계] 슬라이딩 윈도우
        int count = 0;
        for (int i = 0; i < m - (pnSize - 1); i++) {
            String subString = s.substring(i, i + pnSize);
            if (subString.equals(pn)) {
                count++;
            }
        }

        // [4단계] 출력
        System.out.println(count);
    }
}
