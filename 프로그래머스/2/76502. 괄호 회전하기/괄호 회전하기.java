import java.util.*;

class Solution {
    
    static int N;
    
    public int solution(String s) {
        int answer = 0;
        N = s.length();
        char[] charArray = s.toCharArray();
        
        for(int cnt = 0; cnt < N; ++cnt) {
            if(isValid(charArray)){
                // System.out.println(new String(charArray));
                ++answer;
            }
            charArray = move(charArray);
        }
        
        return answer;
    }
    
    // 문자열 이동
    public char[] move(char[] srcArray) {
        char[] tgtArray = new char[N];
        for(int idx = 0; idx < N; ++idx){
            if(idx + 1 < N) {
                tgtArray[idx] = srcArray[idx + 1];    
            }else{
                tgtArray[idx] = srcArray[0];    
            }
        }
        return tgtArray;
    }
    
    // 올바른 괄호 문자열 검증
    public boolean isValid(char[] array) {
        Stack<Character> stk = new Stack<>();
        // init
        for(int idx = 0; idx < N; ++idx) {
            // 여는 괄호일 경우
            if(array[idx] == '{' || array[idx] == '[' || array[idx] == '('){
                stk.push(array[idx]);
                continue;
            }
            
            // 닫는 괄호일 경우 -> Stack이 비어있으면 올바르지 않은 괄호문자열
            if(stk.isEmpty()) return false;
            char currChar = stk.peek();
            switch(currChar){
                case '{':
                    if(array[idx] != '}') return false;
                    else stk.pop();
                    break;
                case '[':
                    if(array[idx] != ']') return false;
                    else stk.pop();
                    break;
                case '(':
                    if(array[idx] != ')') return false;
                    else stk.pop();
                    break;    
            }
        }
        if(!stk.isEmpty()) return false;
        return true;
    }
}