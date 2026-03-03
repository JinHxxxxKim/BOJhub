import java.util.*;

class Solution {
    static int N, K;
    static int[] answer;
    
    public String solution(String number, int k) {
        N = number.length();
        K = k;
        
        Stack<Integer> stk = new Stack<>();
        
        for(int idx = 0; idx < number.length(); ++idx) {
            int currNum = number.charAt(idx) - '0';
            if(stk.isEmpty()) {
                stk.push(currNum);
                continue;
            }
            
            // 현재 남은 수
            int remainNum = N - idx; // 2 - 1 = 1
            while(!stk.isEmpty() && stk.size() + remainNum > N - K) {
                if(stk.peek() < currNum) {
                    stk.pop();
                }else{
                    break;
                }
            }
            
            if(stk.size() < N - K){
                stk.push(currNum);        
            }
        }
        
        
        return getAnsString(stk);
    }
    
    
    public String getAnsString(Stack<Integer> ans) {
        StringBuilder sb = new StringBuilder();
        for(int elem : ans) {
            sb.append(String.valueOf(elem));
        }
        return sb.toString();
    }
}
// N = 10
// 0 1 2 3 4 5 6 7 8 9
// 4 1 7 7 2 5 2 8 4 1

// 0 1 2 3 4 5
// _ _ _ _ _ _
// 4 _ _ _ _ _  
// 4 1 _ _ _ _
// 7 _ _ _ _ _
// 7 7 _ _ _ _
// 7 7 2 _ _ _
// 7 7 5 _ _ _
// 7 7 5 2 _ _ 
// -----------
// 7 7 5 8 4 1