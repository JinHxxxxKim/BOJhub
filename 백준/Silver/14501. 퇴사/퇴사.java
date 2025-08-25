import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int N;
    static int[] dp;
    static Lecture[] lecs;
    
    public static void main(String args[]) throws Exception{
        N = Integer.parseInt(br.readLine().trim());
        dp = new int[N + 1];
        lecs = new Lecture[N];
        int ans = 0;
        
        for(int cnt = 0; cnt < N; ++cnt){
            st = new StringTokenizer(br.readLine().trim());
            lecs[cnt] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        for(int idx = N - 1; idx >= 0; --idx){
            Lecture currLec = lecs[idx];
            
            if(currLec.time - 1 + idx >= N){
                dp[idx] = dp[idx + 1];
            }else{
                dp[idx] = Math.max(currLec.pay + dp[currLec.time + idx], dp[idx + 1]);
            }
            ans = Math.max(ans, dp[idx]);   
        }
        // System.out.println(Arrays.toString(dp));
        System.out.println(ans);
    }
    
    static class Lecture{
        int time, pay;
        public Lecture(int time, int pay){
            this.time = time;
            this.pay = pay;
        }
    }
}