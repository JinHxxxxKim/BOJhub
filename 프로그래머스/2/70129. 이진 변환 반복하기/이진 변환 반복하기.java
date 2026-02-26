import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int zeroCnt = 0;
        int convertCnt = 0;
        while(!s.equals("1")) {
            
            // "0" 제거
            String afterRemove = s.replace("0", "");
            zeroCnt += s.length() - afterRemove.length();
            
            // 이진수 변환
            s = convert(afterRemove.length());
            
            ++convertCnt;
        }
        
        answer[0] = convertCnt;
        answer[1] = zeroCnt;
        
        return answer;
    }
    
    public String convert(int number) {
        StringBuilder sb = new StringBuilder();
        while(number > 0) {
            sb.append(number % 2);
            number = number / 2;
        }
        return sb.reverse().toString();
    }
}