import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    public static void main(String[] args) throws Exception {
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // 먼저 N개 삽입
        st = new StringTokenizer(br.readLine());
        for (int cnt = 0; cnt < N; cnt++) {
            int num = Integer.parseInt(st.nextToken());
            pq.offer(num);
        }

        // 뽑고 넣고 반복 크기 N개 유지
        //  > 가장 작은 애 빼고 삽입
        for (int cnt = 1; cnt < N; cnt++) {
            st = new StringTokenizer(br.readLine());
            for (int tempCnt = 0; tempCnt < N; tempCnt++) {
                int num = Integer.parseInt(st.nextToken());
                
                pq.offer(num);
                pq.poll();
            }
        }

        System.out.println(pq.poll());
    }
}