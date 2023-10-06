import java.util.*;

class Solution {
    public int[] solution(String s) {
        char[] array = s.toCharArray();
        int[] answer = new int[array.length];
        // System.out.println(Arrays.toString(array));
        int[] alpha = new int[26];
        Arrays.fill(alpha, -1);
        
        for(int i=0;i<array.length;++i){
            char currChar = array[i];
            // System.out.println((int)'a');
            if(alpha[currChar-97] == -1){
                // 나온적이 없음
                answer[i] = -1;    
            }else{
                // 나온적이 있음
                answer[i] = i-alpha[currChar-97];
            }
            alpha[currChar-97] = i;
        }
        
        return answer;
    }
}