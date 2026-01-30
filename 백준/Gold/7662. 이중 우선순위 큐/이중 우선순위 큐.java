import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static class DoublePriorityQueue {
        private Queue<Integer> minHeap;
        private Queue<Integer> maxHeap;
        private Map<Integer, Integer> counter;
        private int size;

        public DoublePriorityQueue() {
            minHeap = new PriorityQueue<>();
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            counter = new HashMap<>();
            size = 0;
        }

        public void offer(int num) {
            minHeap.offer(num);
            maxHeap.offer(num);
            counter.put(num, counter.getOrDefault(num, 0) + 1);
            size++;
        }

        public int pollMax() {
            cleanUp(maxHeap);
            int max = maxHeap.poll();
            counter.put(max, counter.get(max) - 1);
            size--;
            return max;
        }

        public int pollMin() {
            cleanUp(minHeap);
            int min = minHeap.poll();
            counter.put(min, counter.get(min) - 1);
            size--;
            return min;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int getMax() {
            cleanUp(maxHeap);
            return maxHeap.peek();
        }

        public int getMin() {
            cleanUp(minHeap);
            return minHeap.peek();
        }

        private void cleanUp(Queue<Integer> heap) {
            while (!heap.isEmpty() && counter.get(heap.peek()) == 0) {
                heap.poll();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        // [1단계] 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // [2단계] 이중 우선순위큐 연산
        while (t-- > 0) {
            int k = Integer.parseInt(br.readLine());
            DoublePriorityQueue doublePriorityQueue = new DoublePriorityQueue();

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String operator = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                if (operator.equals("I")) {
                    doublePriorityQueue.offer(value);
                } else if (operator.equals("D")) {
                    if (doublePriorityQueue.isEmpty()) {
                        continue;
                    }

                    if (value == -1) {
                        doublePriorityQueue.pollMin();
                    } else if (value == 1) {
                        doublePriorityQueue.pollMax();
                    }
                }
            }

            if (doublePriorityQueue.isEmpty()) {
                sb.append("EMPTY").append("\n");
                continue;
            }

            int max = doublePriorityQueue.getMax();
            sb.append(max).append(" ");

            int min = doublePriorityQueue.getMin();
            sb.append(min).append("\n");
        }

        // [3단계] 출력
        System.out.print(sb);
        br.close();
    }
}
