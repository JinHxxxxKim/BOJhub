import java.io.*;
import java.util.*;

/**
 * 자주 나오는 단어일수록 앞에 배치한다.
 * 해당 단어의 길이가 길수록 앞에 배치한다.
 * 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치한다
 */
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;

    static int N, M;
    static List<Word> words;

    static class Word implements Comparable<Word> {
        String val;
        int freq;

        public Word(String val, int freq) {
            this.val = val;
            this.freq = freq;
        }

        @Override
        public int compareTo(Word word) {
            // 자주 나오는 단어일수록 앞에 배치한다.
            if (this.freq == word.freq) {
                // 해당 단어의 길이가 길수록 앞에 배치한다.
                if (this.val.length() == word.val.length()) {
                    return this.val.compareTo(word.val);
                } else {
                    // 알파벳 사전 순으로 앞에 있는 단어일수록 앞에 배치한다
                    return word.val.length() - this.val.length();
                }
            } else {
                return word.freq - this.freq;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        words = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int idx = 0; idx < N; ++idx) {
            String word = br.readLine().trim();
            if (map.containsKey(word)) {
                map.put(word, map.get(word) + 1);
            } else {
                map.put(word, 1);
            }
        }

        for (String key : map.keySet()) {
            if (key.length() < M) {
                continue;
            }
            words.add(new Word(key, map.get(key)));
        }

        Collections.sort(words);

        for (Word word : words) {
            sb.append(word.val).append("\n");
        }
        System.out.println(sb);
    }
}
