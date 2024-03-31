import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final StringBuilder sb = new StringBuilder();
	
	static String text;
	static String pattern;
	static int[] pArray;
	static int cnt;
	static int textLen;
	static int patternLen;
	static List<Integer> pos;
	
	public static void main(String[] args) throws Exception{
//		for(int i = 0; i<10000; ++i) {
//			System.out.print('A');
//		}
//		System.out.println();
//		for(int i = 0; i<5000; ++i) {
//			System.out.print('A');
//		}
		text = br.readLine();
		pattern = br.readLine();
		textLen = text.length();
		patternLen = pattern.length();
		pArray = new int[patternLen];
		
		setP();
		cnt = 0;
		pos = new ArrayList<>();
		kmp();
		System.out.println(cnt);
		System.out.println(sb);
	}

	private static void kmp() {
		int patternIdx = 0;
		for (int textIdx = 0; textIdx < textLen; ++textIdx) {
			// 비교 시작
			// 서로 확인하는 문자가 일치
			if(text.charAt(textIdx) == pattern.charAt(patternIdx)) {
				// 각각 인덱스 증가
				++patternIdx;
				// 패턴이 끝났다 => 일치하는 문자열을 찾았다
				if (patternIdx == patternLen) {
					++cnt;
					sb.append(textIdx - (patternLen - 1) + 1).append(" ");
					// 패턴 인덱스 초기화
					patternIdx = pArray[patternIdx - 1]; ///???????????????
//					textIdx = textIdx - (patternLen - 1) + 1;
//					--textIdx;
				}
			}
			
			// 서로 확인하는 문자가 불일치
			else if(text.charAt(textIdx) != pattern.charAt(patternIdx)) {
				if(patternIdx == 0) {
					// PASS
				}else {
					// patternIdx - 1 까지는 일치한다.
					// patternIdx 수정
					patternIdx = pArray[patternIdx - 1];
					--textIdx;
				}
			}
		}
	}

	// 부분일치 테이블 만들기
	private static void setP() {
		int prefixIdx = 0;
		for (int suffixIdx = 0; suffixIdx < patternLen; ++suffixIdx) {
			if(suffixIdx == 0) {
				pArray[suffixIdx] = 0;
				continue;
			}
			
			// 불일치 발생
			if(pattern.charAt(prefixIdx) != pattern.charAt(suffixIdx)) {
				// 만약 접두사 포인터가 0
				if(prefixIdx == 0) {
					pArray[suffixIdx] = 0;
				}else {
					// prefixIdx - 1까지는 일치한다는 말
					// prefixIdx 수정
					prefixIdx = pArray[prefixIdx - 1];
					// 다시 확인을 위해 접미사 포인터도 변경
					--suffixIdx;
				}
			}
			// 일치
			else if(pattern.charAt(prefixIdx) == pattern.charAt(suffixIdx)) {
				pArray[suffixIdx] = prefixIdx + 1;
				++prefixIdx;
			}
		}
	}

}
