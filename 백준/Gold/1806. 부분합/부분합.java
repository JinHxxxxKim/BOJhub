import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, S;
    static int[] array;

    public static void main(String[] args) throws Exception {
        // INPUT
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        array = new int[N];
        st = new StringTokenizer(br.readLine().trim());
        for(int idx = 0; idx < N; ++idx){
            array[idx] = Integer.parseInt(st.nextToken());
        }

        // SOLUTION: 투포인터
        int l = 0; // 포함
        int r = 0; // 미포함
        int tempSum = 0;
        int ans = Integer.MAX_VALUE;
        while(l < N){
            // 1. 부분합 크기 검증
            // 작을 경우: r 더하기
            if(tempSum < S){
                if (r < N) {
                    // r 더하고 r 증가
                    tempSum += array[r];
                    ++r;
                }else{
                    // r이 범위를 초과하여도 S미만 일 경우 이후는 탐색은 무의미
                    break;
                }
            }
            // 클 경우: l 빼기
            else {
                ans = Math.min(ans, (r - l));
                // l 빼고 l 증가
                tempSum -= array[l];
                ++l;
            }
//            System.out.println("l = " + l +" | "+"r = " + r);
//            System.out.println("tempSum = " + tempSum);
        }
        if (ans == Integer.MAX_VALUE) {
            System.out.print(0);
        } else {
            System.out.print(ans);
        }
    }
}