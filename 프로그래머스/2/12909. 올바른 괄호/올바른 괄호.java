import java.util.*;

class Solution {
    boolean solution(String s) {
        
        Stack<Character> stk = new Stack<>();
        
        for(int idx = 0; idx < s.length(); ++idx){
            Character currChar = s.charAt(idx);
            if(stk.isEmpty()){
                stk.push(currChar);
            }
            // else if(stk.isEmpty() && currChar == ')'){
            //     return false;   
            // }
            else{
                if(currChar == '('){
                    stk.push(currChar);
                }else if(currChar == ')'){
                    if(stk.peek() != '('){
                        return false;
                    }else{
                        stk.pop();
                    }
                }
            }
        }            
        if(stk.isEmpty()){
            return true;    
        }
        return false;
    }
}