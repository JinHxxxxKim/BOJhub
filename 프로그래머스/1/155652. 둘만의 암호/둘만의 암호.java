import java.util.*;

class Solution {
    public String solution(String s, String skip, int index) {
        char[] answer = new char[s.length()];
        
        // a : 97
        // z : 122
        char[] cStr = s.toCharArray();
        boolean[] isSkip = new boolean[26];
        for(int i=0;i<skip.length();++i){
            isSkip[(int)(skip.charAt(i)-97)] = true;
        }
        
        for(int i=0;i<cStr.length;++i){
            int currChar = (int)(cStr[i]-97);
            
            // 실제 이동한 인덱스 수
            int plus = 1;
            // 반복문 탈출 조건 검사 변수
            int cnt = 0;
            
            while(cnt<index){
                //System.out.println((char)((currChar+plus)%26+97));
                if(isSkip[(currChar+plus)%26]){
                    //System.out.println("skip: "+(char)((currChar+plus)%26+97));
                    ++plus;
                }else{
                    //System.out.println("no skip: "+(char)((currChar+plus)%26+97));
                    ++cnt;
                    ++plus;
                }
            }
            //System.out.println("plus: "+plus);
            answer[i] = (char)((currChar+plus-1)%26+97);
            //System.out.println(answer[i]);
        }
        //System.out.println(answer);
        return new String(answer);
    }
}