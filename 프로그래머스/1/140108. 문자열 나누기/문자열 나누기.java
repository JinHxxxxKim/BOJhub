class Solution {
    public int solution(String s) {
        // a: 97
        // z: 122
        int answer = 0;
        
        int index = 0;
        
        int currCharCnt = 0;
        int otherCnt = 0;
        
        while(index<s.length()){
            ++answer;
            char currChar = s.charAt(index);
            ++currCharCnt;
            ++index;
            while(index<s.length()&&currCharCnt!=otherCnt){
                if(currChar!=s.charAt(index)){
                    ++otherCnt;
                }else{
                    ++currCharCnt;
                }
                ++index;
            }
        }
        
        return answer;
    }
}