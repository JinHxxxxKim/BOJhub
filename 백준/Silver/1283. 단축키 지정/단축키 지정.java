import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static List<String>[] list;
    static List<String>[] upperList;
    static boolean[] isSelected;

    public static void main(String[] args) throws Exception{
        // INPUT
        N = Integer.parseInt(br.readLine().trim());
        isSelected = new boolean['Z' - 'A' + 1];
        list = new ArrayList[N];
        upperList = new ArrayList[N];
        for (int idx = 0; idx < N; ++idx) {
            list[idx] = new ArrayList<>();
            upperList[idx] = new ArrayList<>();
        }
        for (int cnt = 0; cnt < N; ++cnt) {
            st = new StringTokenizer(br.readLine().trim());
            while (st.hasMoreElements()) {
                String str = st.nextToken();
                list[cnt].add(str);
                upperList[cnt].add(str.toUpperCase());
            }
        }

        // SOLUTION
        for (int idx = 0; idx < N; ++idx) {
            // 모든 단어의 첫글자가 단축키 지정이 되어있는지 확인
            boolean canSelect = false;
            int selectedWordCnt = -1;
            for (int wordCnt = 0; wordCnt < list[idx].size(); ++wordCnt) {
                char firstChar = upperList[idx].get(wordCnt).charAt(0);
                if (!isSelected[firstChar - 'A']) {
                    canSelect = true;
                    selectedWordCnt = wordCnt;
                    isSelected[firstChar - 'A'] = true;
                    break;
                }
            }
            // 단축키 지정이 가능할 경우
            if (canSelect) {
                for (int wordCnt = 0; wordCnt < list[idx].size(); ++wordCnt) {
                    if (selectedWordCnt == wordCnt) {
                        for (int charCnt = 0; charCnt < list[idx].get(wordCnt).length(); ++charCnt) {
                            if (charCnt == 0) {
                                sb.append("[");
                                sb.append(list[idx].get(wordCnt).charAt(charCnt));
                                sb.append("]");
                            } else {
                                sb.append(list[idx].get(wordCnt).charAt(charCnt));
                            }
                        }
                        sb.append(" ");
                    } else {
                        sb.append(list[idx].get(wordCnt)).append(" ");
                    }
                }
            }
            // 단축키 지정이 불가능할 경우
            else {
                boolean find = false;
                for (int wordCnt = 0; wordCnt < list[idx].size(); ++wordCnt) {
                    String word = list[idx].get(wordCnt);
                    String upperWord = upperList[idx].get(wordCnt);
                    if (find) {
                        sb.append(word).append(" ");
                        continue;
                    }
                    for (int charCnt = 0; charCnt < word.length(); ++charCnt) {
                        // 지정 안된 키 찾음
                        if (!isSelected[upperWord.charAt(charCnt) - 'A'] && !find) {
                            isSelected[upperWord.charAt(charCnt) - 'A'] = true;
                            sb.append("[");
                            sb.append(word.charAt(charCnt));
                               sb.append("]");
                            find = true;
                        } else {
                            sb.append(word.charAt(charCnt));
                        }
                    }
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
