import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // [1단계] 계산식 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String expression = br.readLine();

        // [2단계] 빼기 연산 기준 토큰 분리
        StringTokenizer st = new StringTokenizer(expression, "-");
        List<String> tokens = new ArrayList<>();

        while (st.hasMoreTokens()) {
            tokens.add(st.nextToken());
        }

        // [3단계] 토큰 덧셈 계산해서 다시 담기
        List<Integer> finalTokens = new ArrayList<>();

        for (int i = 0; i < tokens.size(); i++) {
            StringTokenizer st2 = new StringTokenizer(tokens.get(i), "+");
            List<Integer> plusTokens = new ArrayList<>();

            while (st2.hasMoreTokens()) {
                plusTokens.add(Integer.parseInt(st2.nextToken()));
            }

            int sum = 0;
            for (int value : plusTokens) {
                sum += value;
            }

            finalTokens.add(sum);
        }

        // [4단계] 최종 값 계산하기
        int finalSum = finalTokens.get(0);

        for (int i = 1; i < finalTokens.size(); i++) {
            finalSum -= finalTokens.get(i);
        }

        // [5단계] 최종 값 출력
        System.out.println(finalSum);
        br.close();
    }
}
