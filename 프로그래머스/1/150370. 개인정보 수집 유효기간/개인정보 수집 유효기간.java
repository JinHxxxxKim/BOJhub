import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 현재 날짜 저장
        StringTokenizer st = new StringTokenizer(today, ".");
        int currYear = Integer.parseInt(st.nextToken());
        int currMonth = Integer.parseInt(st.nextToken());
        int currDate = Integer.parseInt(st.nextToken());
        int currDays = currYear*(12*28)+currMonth*28+currDate;
        // 약관 정보 저장
        Map<Character, Integer> termMap = new HashMap<>();
        for(String term:terms){
            st = new StringTokenizer(term);
            termMap.put(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
        }
    
        ArrayList<Integer> answerList = new ArrayList<>();
        int cnt = 0;
        // 개인정보 조회
        for(String privacy:privacies){
            ++cnt;
            st = new StringTokenizer(privacy);
            String rawDate = st.nextToken();
            String rawTerm = st.nextToken();
            
            st = new StringTokenizer(rawDate, ".");
            int pYear = Integer.parseInt(st.nextToken());
            int pMonth = Integer.parseInt(st.nextToken());
            int pDate = Integer.parseInt(st.nextToken());
            
            int plusMonth = termMap.get(rawTerm.charAt(0));
            
            int totalDay = pYear*(12*28)+(pMonth+plusMonth)*28+pDate-1;
            System.out.println("totalDay: " + totalDay);
            System.out.println("currDays: " + currDays);
            System.out.println("=======================");
            if(totalDay<currDays)
                answerList.add(cnt);
        }
        
        int[] answer = new int[answerList.size()];
        for(int i=0;i<answer.length;++i){
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}