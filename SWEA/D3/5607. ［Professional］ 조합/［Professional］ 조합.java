import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    
    static final long P = 1_234_567_891;
    
    static int N, R;
    static long[] fact;
    
    public static void main(String[] args) throws Exception{
		int TC = Integer.parseInt(br.readLine().trim());
		for (int testCase = 1; testCase <= TC; ++testCase) {
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			fact = new long[N + 1];
			
			// 1. N factorial 구하기
			setFactorial();
			
			long fermaResult = getFerma(N, R);
			long ans = ((fact[N] % P) * (fermaResult % P)) % P;
			sb.append(String.format("#%d %d\n", testCase, ans));
		}
		System.out.println(sb);
    }

	private static long getFerma(int n, int r) {
		long targetNum = fact[r] % P * fact[n - r] % P;
		long num1 = devideMult(targetNum % P, P - 2);
		return num1 % P;
    }

	private static long devideMult(long num, long r) {
		if (r == 1) {
			return num % P;
		}
		
		long temp = devideMult(num, r / 2);
		
		if (r % 2 == 1) {
			return (temp % P * temp % P * num) % P;
		} else {
			return (temp % P * temp % P) % P;
        }
    }

    private static void setFactorial() {
        fact[0] = 1L;
        for (int cnt = 1; cnt <= N; ++cnt) {
            fact[cnt] = ((long)(fact[cnt - 1]%P) * (long)(cnt%P)) % P;
        }
    }
}