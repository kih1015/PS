import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());

        // 진실을 아는 사람이 없는 경우
        if (l == 0) {
            System.out.print(m);
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] knowsTruth = new boolean[n + 1];
        for (int i = 0; i < l; i++) {
            int person = Integer.parseInt(st.nextToken());
            knowsTruth[person] = true;
            queue.offer(person);
        }
        
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        List<int[]> parties = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int[] attendance = new int[count];
            for (int j = 0; j < count; j++) {
                attendance[j] = Integer.parseInt(st.nextToken());
            }
            parties.add(attendance);

            // 파티 참석자들끼리 서로 연결
            for (int j = 0; j < count; j++) {
                for (int k = j + 1; k < count; k++) {
                    adj.get(attendance[j]).add(attendance[k]);
                    adj.get(attendance[k]).add(attendance[j]);
                }
            }
        }

        // BFS: 진실 전파 시작
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int next : adj.get(curr)) {
                if (!knowsTruth[next]) {
                    knowsTruth[next] = true;
                    queue.offer(next);
                }
            }
        }

        // 결과 계산
        int result = 0;
        for (int[] attendance : parties) {
            boolean canLie = true;
            for (int person : attendance) {
                if (knowsTruth[person]) {
                    canLie = false;
                    break;
                }
            }
            if (canLie) result++;
        }

        System.out.print(result);
    }
}
