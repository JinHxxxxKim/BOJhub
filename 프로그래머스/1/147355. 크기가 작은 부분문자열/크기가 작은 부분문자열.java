class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        // 슬라이딩 윈도우
        int startIdx = 0;
        int endIdx = startIdx + p.length();
        
        char[] num = t.toCharArray();
        char[] testNum = new char[p.length()];
        
        while(startIdx+p.length()<=t.length()){
            for(int i=0;i<p.length();++i){
                testNum[i] = num[startIdx+i];
            }
            
            if(Long.parseLong(p)>=Long.parseLong(new String(testNum))){
                ++answer;
            }
            startIdx++;
        }
        
        return answer;
    }
}