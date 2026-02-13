import java.util.*;

// 중복순열
class Solution {
    
    private final char[] array = {'A', 'E', 'I', 'O', 'U'};
    private int ans = 0;
    private String target;
    private boolean flag;
    
    public int solution(String word) {
        target = word;
        permutation("");
        return ans - 1;
    }
    
    public void permutation(String currStr){
        ans++;
        if(target.equals(currStr)){
            flag = true;
            return;
        }
        
        if(currStr.length() == 5){
            return;
        }
        
        
        for(char nextChar : array){
            permutation(currStr + nextChar);
            if(flag) return;
        }
    }
}