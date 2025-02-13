import java.io.*;
import java.util.*;

// 중간값 구하기
// 짝수개라면 중간에 있는 두 수 중에서 작은 수
// 홀수개라면 중간에 있는 수
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static PriorityQueue<Integer> pqSmall;
    static PriorityQueue<Integer> pqBig;
    static int N;

    public static void main(String[] args) throws Exception{
        N = Integer.parseInt(br.readLine().trim());
        pqSmall = new PriorityQueue<>(Collections.reverseOrder());
        pqBig = new PriorityQueue<>();
        int midNum = 0;
        for (int cnt = 1; cnt < N + 1; ++cnt) {
            // 현재 수
            int currNum = Integer.parseInt(br.readLine().trim());

            // 초기조건
            if(cnt == 1){
                midNum = currNum;
                sb.append(midNum).append("\n");
                continue;
            }

            // 짝수 홀수 판단
            boolean isOdd = cnt % 2 != 0;
            if(midNum >= currNum){
                pqSmall.offer(currNum);
            }else{
                pqBig.offer(currNum);
            }
            if (isOdd) {
                // 홀수번째: bigPQ의 개수가 smallPQ의 개수와 동일해야함
                if (pqSmall.size() > pqBig.size()) {
                    pqBig.offer(midNum);
                    midNum = pqSmall.poll();
                } else if (pqSmall.size() < pqBig.size()) {
                    pqSmall.offer(midNum);
                    midNum = pqBig.poll();
                }
            } else {
                // 짝수번째: bigPQ의 개수가 하나 더 많아야함
                if(pqSmall.size() >= pqBig.size()){
                    pqBig.offer(midNum);
                    midNum = pqSmall.poll();
                }
            }
//            System.out.println("pqSmall = " + pqSmall);
//            System.out.println("midNum = " + midNum);
//            System.out.println("pqBig = " + pqBig);
            sb.append(midNum).append("\n");
        }
        System.out.println(sb);
    }
}
/**
 * 7
 * 1
 * 5
 * 2
 * 10
 * -99
 * 7
 * 5
 */

/**
 * 1
 * 1
 * 2
 * 2
 * 2
 * 2
 * 5
 */