
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * @author 김진형
 * 
 * 1. 
 */
public class Solution {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		int TC = 10;
		for (int testCase = 1; testCase <= TC; ++testCase) {
			br.readLine();

			String keyword = br.readLine().trim();
			String src = br.readLine().trim();
			String replaceStr = src.replaceAll(keyword, "");
			
			int beforeLen = src.length();
			int afterLen = replaceStr.length();
			int ans = (beforeLen - afterLen) / keyword.length();
			sb.append(String.format("#%d %d\n", testCase, ans));
		}
		System.out.println(sb);
	}
}
