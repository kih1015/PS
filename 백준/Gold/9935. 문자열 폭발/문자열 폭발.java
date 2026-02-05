import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bomb = br.readLine();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            // 폭탄 마지막 문자와 같은지 확인
            int bombLen = bomb.length();
            if (stack.peek() != bomb.charAt(bombLen - 1)) {
                continue;
            }

            // 폭탄 문자열의 길이보다 스택의 길이가 더 큰지 확인
            int stackSize = stack.size();
            if (stackSize < bombLen) {
                continue;
            }

            boolean isMatched = true;
            for (int j = 0; j < bombLen; j++) {
                if (stack.get(stackSize - bombLen + j) != bomb.charAt(j)) {
                    isMatched = false;
                    break;
                }
            }

            // 폭탄 문자열이 스택의 마지막 부분과 일치하는지 확인
            if (!isMatched) {
                continue;
            }

            // 폭탄 문자열 전부 삭제
            for (int j = 0; j < bomb.length(); j++) {
                stack.pop();
            }
        }

        if (stack.empty()) {
            System.out.print("FRULA");
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (char ch : stack) {
            sb.append(ch);
        }

        System.out.println(sb);
        br.close();
    }
}
