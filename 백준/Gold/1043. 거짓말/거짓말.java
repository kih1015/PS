import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int l = Integer.parseInt(st.nextToken());
        Set<Integer> truePeople = new HashSet<>();
        for (int i = 0; i < l; i++) {
            truePeople.add(Integer.parseInt(st.nextToken()));
        }

        boolean[][] adj = new boolean[n + 1][n + 1];
        List<Set<Integer>> partyPeople = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            int[] attendance = new int[count];
            Set<Integer> set = new HashSet<>();

            // 파티 참여자 기록
            for (int j = 0; j < count; j++) {
                attendance[j] = Integer.parseInt(st.nextToken());
                set.add(attendance[j]);
            }
            partyPeople.add(set);

            // 인접행렬 구성
            for (int j = 0; j < count; j++) {
                for (int k = j + 1; k < count; k++) {
                    adj[attendance[j]][attendance[k]] = true;
                    adj[attendance[k]][attendance[j]] = true;
                }
            }
        }

        // 플로이드-워셜
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (adj[i][k] && adj[k][j]) {
                        adj[i][j] = true;
                    }
                }
            }
        }

        Set<Integer> newTruePeople = new HashSet<>(truePeople);
        for (int truePerson : truePeople) {
            for (int i = 1; i <= n; i++) {
                if (adj[truePerson][i]) {
                    newTruePeople.add(i);
                }
            }
        }

        int count = 0;
        for (Set<Integer> attendance : partyPeople) {
            boolean flag = true;
            for (int truePerson : newTruePeople) {
                if (attendance.contains(truePerson)) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }

        System.out.print(count);
        br.close();
    }
}
